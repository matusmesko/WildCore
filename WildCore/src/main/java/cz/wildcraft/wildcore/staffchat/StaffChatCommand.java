package cz.wildcraft.wildcore.staffchat;

import cz.wildcraft.wildcore.WildCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffChatCommand implements CommandExecutor{

    private WildCore spigot = WildCore.getPlugin();

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player player = (Player)sender;
        if (player.hasPermission("staffChat.write") || player.hasPermission("staffChat.admin")) {
            if (args.length == 0) {
                spigot.getStaffChatController().changeStatus(player);
                return true;
            } else if (args[0].equalsIgnoreCase("on") && args.length == 1) {
                spigot.getStaffChatController().addPlayerToStaffChat(player);
                return true;
            } else if (args[0].equalsIgnoreCase("off") && args.length == 1) {
                spigot.getStaffChatController().removePlayerFromStaffChat(player);
                return true;
            } else if (args[0].equalsIgnoreCase("reload") && args.length == 1) {
                if (!player.hasPermission("staffChat.admin")) {
                    return true;
                } else {
                    spigot.getStaffChatController().reloadConfig(player);
                    return true;
                }
            } else {
                spigot.getStaffChatController().sendStaffChatMessage(player, args);
                return true;
            }
        } else {
            spigot.getStaffChatController().sendNoPerm(player);
            return true;
        }
    }
}
