package cz.wildcraft.wildcore.staffchat;

import cz.wildcraft.wildcore.WildCore;
import cz.wildcraft.wildcore.utils.ColorAPI;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

public class Messages {

    private WildCore spigot = WildCore.getPlugin();
    public static String sendConfigMessage(String path) {
        return WildCore.getPlugin().getConfig().getString(path);
    }

    public void sendListMessagesSpigot(Player player, Player sender, String path, String message) {
        player.sendMessage(PlaceholderAPI.setPlaceholders(sender,ColorAPI.colorize(this.spigot.getConfig().getString(path).replace("%name%", sender.getName()).replace("%message%", message))));
    }

    public void sendListMessagesConsole(Player player, String path, String message) {
        player.sendMessage(ColorAPI.colorize(this.spigot.getConfig().getString(path).replace("%name%", "Console").replace("%message%", message)));
    }

    public void sendListMessagesSender(Player player, String sender, String path, String message) {
        player.sendMessage(ColorAPI.colorize(this.spigot.getConfig().getString(path).replace("%name%", sender).replace("%message%", message)));
    }
}
