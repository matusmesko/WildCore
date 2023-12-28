package cz.wildcraft.wildcore.staffchat;

import cz.wildcraft.wildcore.WildCore;
import cz.wildcraft.wildcore.utils.ColorAPI;
import org.bukkit.entity.Player;
public class StaffChatController {
    private WildCore spigot = WildCore.getPlugin();



    public void addPlayerToStaffChat(Player player) {
        if (spigot.getStaffChatManager().getStaffChatList().contains(player.getName())) {
            player.sendMessage(ColorAPI.colorize(Messages.sendConfigMessage("staffchat.messages.error")));
            return;
        }
        spigot.getStaffChatManager().getStaffChatList().add(player.getName());
        player.sendMessage(ColorAPI.colorize(Messages.sendConfigMessage("staffchat.messages.enable")));
    }

    public void removePlayerFromStaffChat(Player player) {
        if (!spigot.getStaffChatManager().getStaffChatList().contains(player.getName())) {
            player.sendMessage(ColorAPI.colorize(Messages.sendConfigMessage("staffchat.messages.error")));
            return;
        }
        spigot.getStaffChatManager().getStaffChatList().remove(player.getName());
        player.sendMessage(ColorAPI.colorize(Messages.sendConfigMessage("staffchat.messages.disable")));
    }

    public void changeStatus(Player player) {
        if (!spigot.getStaffChatManager().getStaffChatList().contains(player.getName())) {
            addPlayerToStaffChat(player);
            return;
        }
        removePlayerFromStaffChat(player);
    }

    public void sendStaffChatMessage(Player player, String[] args) {
        spigot.getStaffChatManager().sendAdminMessageBuilder(player, args);
    }



    public void reloadConfig(Player player) {
        this.spigot.saveConfig();
        spigot.getStaffChatManager().loadData();
        player.sendMessage(ColorAPI.colorize(Messages.sendConfigMessage("staffchat.messages.reload")));
    }

    public void sendNoPerm(Player player) {
        player.sendMessage(ColorAPI.colorize(Messages.sendConfigMessage("staffchat.messages.noPerm")));
    }
}
