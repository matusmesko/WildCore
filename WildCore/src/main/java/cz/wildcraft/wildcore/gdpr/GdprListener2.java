package cz.wildcraft.wildcore.gdpr;

import cz.wildcraft.wildcore.WildCore;
import cz.wildcraft.wildcore.gender.GenderModel;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.SQLException;

public class GdprListener2 implements Listener {

    private final GdprTable gdprTable = new GdprTable();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoinEvent(PlayerJoinEvent e) throws SQLException {
        Player player = e.getPlayer();
        GdprModel model = gdprTable.findPlayerByName(player.getDisplayName());
        System.out.println("GETTIND GDPR DATA: " + model.getUsername() + model.isAccepted());
        new GdprMenu(WildCore.getPlayerMenuUtility(player)).open();



    }
}
