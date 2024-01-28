package cz.wildcraft.wildcore.warps.menus;

import cz.wildcraft.wildcore.WildCore;
import cz.wildcraft.wildcore.menusystem.Menu;
import cz.wildcraft.wildcore.menusystem.PlayerMenuUtility;
import cz.wildcraft.wildcore.utils.Utils;
import cz.wildcraft.wildcore.warps.database.PlayerWarpTable;
import cz.wildcraft.wildcore.warps.model.PlayerWarpModel;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.SQLException;

public class WarpConfirmMenu extends Menu {
    public WarpConfirmMenu(PlayerMenuUtility playerMenuUtility, PlayerWarpModel playerWarpModel) {
        super(playerMenuUtility);
        this.playerWarpModel = playerWarpModel;
    }

    private PlayerWarpModel playerWarpModel;


    private int warpCost = WildCore.getPlugin().getConfig().getInt("warp-cost");

    private Economy eco = WildCore.getEconomy();

    private PlayerWarpTable playerWarpTable = new PlayerWarpTable();

    @Override
    public String getMenuName() {
        return "Vytvořit warp";
    }

    @Override
    public int getSlots() {
        return 27;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) throws SQLException {
        Player p = (Player) e.getWhoClicked();
        if (e.getSlot() == 12) {
                EconomyResponse response = eco.withdrawPlayer(p, warpCost);
                if (response.transactionSuccess()) {
                    playerWarpTable.createWarp(playerWarpModel);
                    p.sendMessage(WildCore.getPlugin().getConfig().getString("warpConfirmMenu.accept.messageAfter").replace("%w_name%", playerWarpModel.getWarp_name()));
                    p.closeInventory();
                } else {
                    p.sendMessage(WildCore.getPlugin().getConfig().getString("warpConfirmMenu.accept.errorMessage").replace("%w_name%", playerWarpModel.getWarp_name()));
                    p.closeInventory();
                }

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
        ItemStack item = new ItemStack(Material.valueOf(WildCore.getPlugin().getConfig().getString("warpConfirmMenu.accept.item")));
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(WildCore.getPlugin().getConfig().getString("warpConfirmMenu.accept.display"));
        item.setItemMeta(meta);
        return item;
    }

    private ItemStack declineButton() {
        ItemStack item = new ItemStack(Material.valueOf(WildCore.getPlugin().getConfig().getString("warpConfirmMenu.denied.item")));
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(WildCore.getPlugin().getConfig().getString("warpConfirmMenu.denied.display"));
        item.setItemMeta(meta);
        return item;
    }
}
