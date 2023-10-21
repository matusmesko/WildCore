package cz.wildcraft.wildcore.warps.menus;

import cz.wildcraft.wildcore.WildCore;
import cz.wildcraft.wildcore.menusystem.Menu;
import cz.wildcraft.wildcore.menusystem.PlayerMenuUtility;
import cz.wildcraft.wildcore.warps.database.PlayerWarpTable;
import cz.wildcraft.wildcore.warps.model.PlayerWarpModel;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.SQLException;

public class DeleteWarpConfirmMenu extends Menu {
    public DeleteWarpConfirmMenu(PlayerMenuUtility playerMenuUtility, PlayerWarpModel playerWarpModel) {
        super(playerMenuUtility);
        this.playerWarpModel = playerWarpModel;
    }

    private PlayerWarpTable database = new PlayerWarpTable();

    private final PlayerWarpModel playerWarpModel;

    @Override
    public String getMenuName() {
        return "Zmazat warp";
    }

    @Override
    public int getSlots() {
        return 27;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) throws SQLException {
        Player p = (Player) e.getWhoClicked();
        if (e.getSlot() == 12) {
            database.deleteWarp(playerWarpModel);
            p.sendMessage(WildCore.getPlugin().getConfig().getString("deleteWarp.accept.messageAfter").replace("%w_name%", playerWarpModel.getWarp_name()));
            p.closeInventory();
        } else if (e.getSlot() == 14) {
            p.closeInventory();
        }
    }

    @Override
    public void setMenuItems() throws SQLException {
        setFillerGlass();
        inventory.setItem(12, confirmButton());
        inventory.setItem(14, declineButton());
    }


    private ItemStack confirmButton() {
        ItemStack item = new ItemStack(Material.valueOf(WildCore.getPlugin().getConfig().getString("deleteWarp.accept.item")));
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(WildCore.getPlugin().getConfig().getString("deleteWarp.accept.display"));
        item.setItemMeta(meta);
        return item;
    }

    private ItemStack declineButton() {
        ItemStack item = new ItemStack(Material.valueOf(WildCore.getPlugin().getConfig().getString("deleteWarp.denied.item")));
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(WildCore.getPlugin().getConfig().getString("deleteWarp.denied.display"));
        item.setItemMeta(meta);
        return item;
    }
}
