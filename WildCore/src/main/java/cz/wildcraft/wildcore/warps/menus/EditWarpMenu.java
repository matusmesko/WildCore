package cz.wildcraft.wildcore.warps.menus;

import cz.wildcraft.wildcore.WildCore;
import cz.wildcraft.wildcore.menusystem.Menu;
import cz.wildcraft.wildcore.menusystem.PlayerMenuUtility;
import cz.wildcraft.wildcore.warps.model.PlayerWarpModel;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.SQLException;

public class EditWarpMenu extends Menu {



    public EditWarpMenu(PlayerMenuUtility playerMenuUtility, PlayerWarpModel playerWarpModel) {
        super(playerMenuUtility);
        this.playerWarpModel = playerWarpModel;
    }

    private PlayerWarpModel playerWarpModel;

    @Override
    public String getMenuName() {
        return "Editovat Warp";
    }

    @Override
    public int getSlots() {
        return 27;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) throws SQLException {
        Player p = (Player) e.getWhoClicked();
        if (e.getSlot() == 18) {
            new OwnerWarpsMenu(WildCore.getPlayerMenuUtility(p)).open();
        } else if (e.getSlot() == 13) {
            new EditWarpIconMenu(WildCore.getPlayerMenuUtility(p), playerWarpModel).open();
        }
    }

    @Override
    public void setMenuItems() throws SQLException {
        setFillerGlass();
        inventory.setItem(18, backButton());
        inventory.setItem(13, changeIcon());
    }

    private ItemStack backButton() {
        HeadDatabaseAPI api = new HeadDatabaseAPI();
        ItemStack button = api.getItemHead("9226");
        ItemMeta meta = button.getItemMeta();
        meta.setDisplayName("§7Zpět");
        button.setItemMeta(meta);
        return button;
    }

    private ItemStack changeIcon() {
        ItemStack item = new ItemStack(Material.ITEM_FRAME);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§a§lZměnit ikonu warpu");
        item.setItemMeta(meta);
        return item;
    }
}
