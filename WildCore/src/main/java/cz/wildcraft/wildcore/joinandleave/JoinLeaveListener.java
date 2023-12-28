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

    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        e.setQuitMessage("§c§l← §f" + p.getName());
    }
}
