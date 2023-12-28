package cz.wildcraft.wildcore.discord;

import cz.wildcraft.wildcore.WildCore;
import net.dv8tion.jda.api.entities.WebhookClient;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatWebhook implements Listener {

    private WildCore spigot = WildCore.getPlugin();
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String message = e.getMessage();
        Player player = e.getPlayer();

        if ((player.hasPermission("staffChat.write") || player.hasPermission("staffChat.admin")) && message.startsWith(this.spigot.getStaffChatManager().getSymbol()) && this.spigot.getStaffChatManager().isSymbolBol()) {
            return;
        }
        if (this.spigot.getStaffChatManager().getStaffChatList().contains(player.getName())) {
            return;
        }

        WebhookClient client = WebhookClient.createClient(WildCore.getPlugin().getJda(), "https://discord.com/api/webhooks/1183167873359810592/YzcpFYbokJTklq6kNtGXi7tBZmkeI-4wcN2iHn-CXwvk0-kMuU2GaWwLUfsS1qQA-_Q-");
        client.sendMessage(e.getMessage()).setAvatarUrl("https://minotar.net/avatar/" + p.getName()).setUsername(p.getName()).queue();
    }
}
