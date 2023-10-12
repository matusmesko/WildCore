package cz.wildcraft.wildcore;

import org.bukkit.plugin.java.JavaPlugin;

public final class WildCore extends JavaPlugin {

    private static WildCore plugin;

    @Override
    public void onEnable() {
        plugin = this;
        loadConfig();

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
}
