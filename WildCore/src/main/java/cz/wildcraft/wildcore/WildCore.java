package cz.wildcraft.wildcore;

import cz.wildcraft.wildcore.menusystem.MenuListener;
import cz.wildcraft.wildcore.menusystem.PlayerMenuUtility;
import cz.wildcraft.wildcore.warps.commands.DeleteWarpCommand;
import cz.wildcraft.wildcore.warps.commands.SetPlayerWarpCommand;
import cz.wildcraft.wildcore.warps.commands.SetServerWarpCommand;
import cz.wildcraft.wildcore.warps.commands.WarpsCommand;
import cz.wildcraft.wildcore.warps.database.PlayerWarpTable;
import cz.wildcraft.wildcore.warps.database.ServerWarpTable;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.HashMap;

public final class WildCore extends JavaPlugin {

    private static WildCore plugin;

    private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();

    private static Economy econ = null;

    private final ServerWarpTable serverWarpTable = new ServerWarpTable();

    private final PlayerWarpTable playerWarpTable = new PlayerWarpTable();

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
        }catch (SQLException e) {
            e.printStackTrace();
        }

        getServer().getPluginManager().registerEvents(new MenuListener(), this);
        getCommand("warps").setExecutor(new WarpsCommand(serverWarpTable));
        getCommand("setServerWarp").setExecutor(new SetServerWarpCommand(serverWarpTable));
        getCommand("setwarp").setExecutor(new SetPlayerWarpCommand());
        getCommand("delwarp").setExecutor(new DeleteWarpCommand());
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

}
