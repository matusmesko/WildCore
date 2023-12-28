package cz.wildcraft.wildcore.gdpr;

import cz.wildcraft.wildcore.WildCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GdprListener implements Listener {

    private final GdprTable gdprTable;

    public GdprListener(GdprTable gdprTable) {
        this.gdprTable = gdprTable;
    }

    private GdprModel checkPlayer(Player player) throws SQLException {
        GdprModel gdprModel = gdprTable.findPlayerByName(player.getName());
        if (gdprModel == null) {
            gdprModel = new GdprModel(player.getName(), false, "none");
            gdprTable.createPlayer(gdprModel);
        }
        return gdprModel;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        try {
            GdprModel model = checkPlayer(player);
            model.setUsername(player.getName());
            gdprTable.updateGdpr(model);
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
