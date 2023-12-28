package cz.wildcraft.wildcore;

import cz.wildcraft.wildcore.commands.*;
import cz.wildcraft.wildcore.discord.ChatWebhook;
import cz.wildcraft.wildcore.discord.LiteBansPunismentLog;
import cz.wildcraft.wildcore.discord.RoleOnJoinServer;
import cz.wildcraft.wildcore.gdpr.GdprListener;
import cz.wildcraft.wildcore.gdpr.GdprListener2;
import cz.wildcraft.wildcore.gdpr.GdprTable;
import cz.wildcraft.wildcore.gender.GenderCommand;
import cz.wildcraft.wildcore.gender.GenderListener;
import cz.wildcraft.wildcore.gender.GenderTable;
import cz.wildcraft.wildcore.joinandleave.JoinLeaveListener;
import cz.wildcraft.wildcore.menusystem.MenuListener;
import cz.wildcraft.wildcore.menusystem.PlayerMenuUtility;
import cz.wildcraft.wildcore.placeholders.WildCorePlaceholder;
import cz.wildcraft.wildcore.staffchat.*;
import cz.wildcraft.wildcore.warps.commands.DeleteWarpCommand;
import cz.wildcraft.wildcore.warps.commands.SetPlayerWarpCommand;
import cz.wildcraft.wildcore.warps.commands.SetServerWarpCommand;
import cz.wildcraft.wildcore.warps.commands.WarpsCommand;
import cz.wildcraft.wildcore.warps.database.PlayerWarpTable;
import cz.wildcraft.wildcore.warps.database.ServerWarpTable;
import me.realized.tokenmanager.api.TokenManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.HashMap;

public final class WildCore extends JavaPlugin {

    private static WildCore plugin;

    private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();

    private static Economy econ = null;


    private Messages messages;

    private StaffChatController staffChatController;

    private StaffChatManager staffChatManager;

    private final ServerWarpTable serverWarpTable = new ServerWarpTable();

    private final PlayerWarpTable playerWarpTable = new PlayerWarpTable();

    private final GenderTable genderTable = new GenderTable();
    private final GdprTable gdprTable = new GdprTable();

    private JDA jda;

    private String botToken = getConfig().getString("bot-token");



    @Override
    public void onEnable() {
        plugin = this;
        loadConfig();
        if (!setupEconomy() ) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        try {
            this.serverWarpTable.initializeServerWarpsTable();
            this.playerWarpTable.initializeTable();
            this.genderTable.initializeTable();
            this.gdprTable.initializeTable();
        }catch (SQLException e) {
            e.printStackTrace();
        }
            controllers();
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) {
            getLogger().severe("Could not find PlaceholderAPI! This plugin is required.");
            Bukkit.getPluginManager().disablePlugin(this);
        } else {
            getLogger().fine("PlaceholderAPI successfully integrated!");
            new WildCorePlaceholder(this).register();
        }


        try {
            jda = JDABuilder.createDefault(botToken)
                    .setStatus(OnlineStatus.ONLINE)
                    .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                    .enableIntents(GatewayIntent.GUILD_MEMBERS)
                    .setActivity(Activity.playing("wildcraft.mc.hostify.cz"))
                    .build().awaitReady();
            jda.addEventListener(new RoleOnJoinServer());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        LiteBansPunismentLog.registerEvents();



        getServer().getPluginManager().registerEvents(new MenuListener(), this);
        getServer().getPluginManager().registerEvents(new JoinLeaveListener(), this);
        getServer().getPluginManager().registerEvents(new GenderListener(genderTable), this);
        getServer().getPluginManager().registerEvents(new GdprListener(gdprTable), this);
        getServer().getPluginManager().registerEvents(new GdprListener2(), this);
        getServer().getPluginManager().registerEvents(new ChatWebhook(), this);
        getServer().getPluginManager().registerEvents(new StaffChatListener(), this);
        getCommand("warps").setExecutor(new WarpsCommand(serverWarpTable));
        getCommand("setServerWarp").setExecutor(new SetServerWarpCommand(serverWarpTable));
        getCommand("setwarp").setExecutor(new SetPlayerWarpCommand());
        getCommand("delwarp").setExecutor(new DeleteWarpCommand());
        getCommand("wildcore").setExecutor(new WildCoreCommand());
        getCommand("gender").setExecutor(new GenderCommand());
        getCommand("staffchat").setExecutor(new StaffChatCommand());
        getCommand("staffchat").setTabCompleter(new StaffChatCompleter());
        getCommand("kredity").setTabCompleter(new KreditTabCompletion());
        getCommand("kredity").setExecutor(new KreditCommand());
        getCommand("discord").setExecutor(new DiscordCommand());
        getCommand("store").setExecutor(new StoreCommand());
        getCommand("priroda").setExecutor(new PrirodaCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static  WildCore getPlugin() {
        return plugin;
    }

    public void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
    }

    //Provide a player and return a menu system for that player
    //create one if they don't already have one
    public static PlayerMenuUtility getPlayerMenuUtility(Player p) {
        PlayerMenuUtility playerMenuUtility;
        if (!(playerMenuUtilityMap.containsKey(p))) { //See if the player has a playermenuutility "saved" for them

            //This player doesn't. Make one for them add add it to the hashmap
            playerMenuUtility = new PlayerMenuUtility(p);
            playerMenuUtilityMap.put(p, playerMenuUtility);

            return playerMenuUtility;
        } else {
            return playerMenuUtilityMap.get(p); //Return the object by using the provided player
        }
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static Economy getEconomy() {
        return econ;
    }

    public GenderTable getGenderTable() {
        return genderTable;
    }

    public void controllers() {
        this.messages = new Messages();
        this.staffChatController = new StaffChatController();
        this.staffChatManager = new StaffChatManager();
    }

    public Messages getMessages() {
        return this.messages;
    }

    public StaffChatController getStaffChatController() {
        return this.staffChatController;
    }

    public StaffChatManager getStaffChatManager() {
        return this.staffChatManager;
    }

    public JDA getJda() {
        return jda;
    }

    public TextChannel punishmentLogChannel () {
        return jda.getTextChannelById("1187723139086569564");
    }


}
