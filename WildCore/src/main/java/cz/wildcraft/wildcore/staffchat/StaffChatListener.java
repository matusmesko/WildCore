package cz.wildcraft.wildcore.staffchat;

import cz.wildcraft.wildcore.WildCore;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class StaffChatListener implements Listener{

    private WildCore spigot = WildCore.getPlugin();
    @EventHandler(priority = EventPriority.LOWEST)
    public void onStaffChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        if ((player.hasPermission("staffChat.write") || player.hasPermission("staffChat.admin")) && message.startsWith(this.spigot.getStaffChatManager().getSymbol()) && this.spigot.getStaffChatManager().isSymbolBol()) {
            message = message.replace(this.spigot.getStaffChatManager().getSymbol(), "");
            event.setCancelled(true);
            this.spigot.getStaffChatManager().sendStaffMessage(player, message);
        } else if (this.spigot.getStaffChatManager().getStaffChatList().contains(player.getName())) {
            event.setCancelled(true);
            this.spigot.getStaffChatManager().sendStaffMessage(player, message);
        }
    }
}
