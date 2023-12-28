package cz.wildcraft.wildcore.gender;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.SQLException;

public class GenderListener implements Listener {

    private final GenderTable genderTable;

    public GenderListener(GenderTable genderTable) {
        this.genderTable = genderTable;
    }


    private GenderModel checkPlayer(Player player) throws SQLException {
        GenderModel genderModel = genderTable.findPlayerByName(player.getName());
        if (genderModel == null) {
            genderModel = new GenderModel(player.getName(), "male");
            genderTable.createPlayer(genderModel);
        }
        return genderModel;
    }

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        try {
           GenderModel model = checkPlayer(player);
            model.setUsername(player.getName());
            genderTable.updateGender(model);
        }catch (SQLException ex) {
            ex.printStackTrace();
        }


    }
}
