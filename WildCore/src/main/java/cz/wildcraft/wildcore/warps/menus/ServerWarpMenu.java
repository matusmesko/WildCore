package cz.wildcraft.wildcore.warps.menus;

import cz.wildcraft.wildcore.menusystem.Menu;
import cz.wildcraft.wildcore.menusystem.PlayerMenuUtility;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ServerWarpMenu extends Menu {
    public ServerWarpMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "Seznam serverových warpů";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {

    }

    @Override
    public void setMenuItems() {

    }
}
