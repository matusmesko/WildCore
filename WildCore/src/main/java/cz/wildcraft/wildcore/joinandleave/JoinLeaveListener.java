package cz.wildcraft.wildcore.joinandleave;

import cz.wildcraft.wildcore.WildCore;
import cz.wildcraft.wildcore.gdpr.GdprMenu;
import cz.wildcraft.wildcore.gdpr.GdprModel;
import cz.wildcraft.wildcore.gdpr.GdprTable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.SQLException;

public class JoinLeaveListener implements Listener {



    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        if (p.hasPlayedBefore()) {
            e.setJoinMessage("§a§l→ §7" + p.getName());
        }else {
            e.setJoinMessage("§e§l→ §7" + p.getName());
        }
        /*
        p.sendMessage("");
        p.sendMessage("§c| §7Vítej §c" + p.getName() + " §7na serveru!");
        p.sendMessage("§c| §7Příroda: §c/priroda");
        p.sendMessage("§c| §7Menu: §c/menu");
        */

    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        e.setQuitMessage("§c§l← §7" + p.getName());
    }
}
