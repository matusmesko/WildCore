package cz.wildcraft.wildcore.playermenu;

import cz.wildcraft.wildcore.menusystem.Menu;
import cz.wildcraft.wildcore.menusystem.PlayerMenuUtility;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.sql.SQLException;

public class PlayerMenu extends Menu {
    public PlayerMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "§lMenu";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) throws SQLException {

    }

    @Override
    public void setMenuItems() throws SQLException {
        setFillerGlass();
        //▪
    }
}
