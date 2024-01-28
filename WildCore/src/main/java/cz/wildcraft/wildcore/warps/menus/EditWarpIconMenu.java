package cz.wildcraft.wildcore.warps.menus;

import cz.wildcraft.wildcore.WildCore;
import cz.wildcraft.wildcore.menusystem.Menu;
import cz.wildcraft.wildcore.menusystem.PlayerMenuUtility;
import cz.wildcraft.wildcore.warps.database.PlayerWarpTable;
import cz.wildcraft.wildcore.warps.model.PlayerWarpModel;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.SQLException;

public class EditWarpIconMenu extends Menu {
    public EditWarpIconMenu(PlayerMenuUtility playerMenuUtility, PlayerWarpModel playerWarpModel) {
        super(playerMenuUtility);
        this.playerWarpModel = playerWarpModel;
    }

    private PlayerWarpModel playerWarpModel;

    private int[] filled = {19,20,21,22,23,24,25,26};

    private int[] iconSlots = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17};

    private final PlayerWarpTable playerWarpTable = new PlayerWarpTable();

    @Override
    public String getMenuName() {
        return "Změnit ikonu warpu";
    }

    @Override
    public int getSlots() {
        return 27;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) throws SQLException {
        Player p = (Player) e.getWhoClicked();
        if (e.getSlot() == 18) {
            new EditWarpMenu(WildCore.getPlayerMenuUtility(p), playerWarpModel).open();
        }

        for (int slot : iconSlots) {
            if (e.getSlot() == slot) {
                ItemStack item = e.getCurrentItem();
                String MATERIAL = item.getType().toString();
                playerWarpModel.setIcon(MATERIAL);
                playerWarpTable.updateWarp(playerWarpModel);
                p.sendMessage("§c§lW§6§lC §8§l» §aIkona warpu byla změněna na §c" + MATERIAL);
                p.closeInventory();
            }
        }
    }

    @Override
    public void setMenuItems() throws SQLException {
        inventory.setItem(18, backButton());
        Player p = playerMenuUtility.getOwner();
        if (p.hasPermission("wildcore.ultimate")) {
            inventory.addItem(new ItemStack(Material.DIAMOND));
            inventory.addItem(new ItemStack(Material.BIRCH_SIGN));
            inventory.addItem(new ItemStack(Material.ENDER_CHEST));
            inventory.addItem(new ItemStack(Material.BARREL));
            inventory.addItem(new ItemStack(Material.BELL));
            inventory.addItem(new ItemStack(Material.CAMPFIRE));
            inventory.addItem(new ItemStack(Material.BLUE_CANDLE));
            inventory.addItem(new ItemStack(Material.DRAGON_EGG));
            inventory.addItem(new ItemStack(Material.BOOK));
            inventory.addItem(new ItemStack(Material.GOLD_INGOT));
            inventory.addItem(new ItemStack(Material.GOLD_NUGGET));
            inventory.addItem(new ItemStack(Material.SADDLE));
            inventory.addItem(new ItemStack(Material.SHULKER_BOX));
            inventory.addItem(new ItemStack(Material.FLOWER_POT));
            inventory.addItem(new ItemStack(Material.HOPPER));
            inventory.addItem(new ItemStack(Material.CROSSBOW));
            inventory.addItem(new ItemStack(Material.TNT));
            inventory.addItem(new ItemStack(Material.LANTERN));
        } else if (p.hasPermission("wildcore.legendary")) {
            inventory.addItem(new ItemStack(Material.DIAMOND));
            inventory.addItem(new ItemStack(Material.BIRCH_SIGN));
            inventory.addItem(new ItemStack(Material.ENDER_CHEST));
            inventory.addItem(new ItemStack(Material.BARREL));
            inventory.addItem(new ItemStack(Material.BELL));
            inventory.addItem(new ItemStack(Material.CAMPFIRE));
            inventory.addItem(new ItemStack(Material.BLUE_CANDLE));
            inventory.addItem(new ItemStack(Material.DRAGON_EGG));
            inventory.addItem(new ItemStack(Material.BOOK));
        }else {
            inventory.addItem(new ItemStack(Material.DIAMOND));
            inventory.addItem(new ItemStack(Material.BIRCH_SIGN));
        }

        for (int slot : filled) {
            inventory.setItem(slot, FILLER_GLASS);
        }
    }

    private ItemStack backButton() {
        HeadDatabaseAPI api = new HeadDatabaseAPI();
        ItemStack button = api.getItemHead("9226");
        ItemMeta meta = button.getItemMeta();
        meta.setDisplayName("§7Zpět");
        button.setItemMeta(meta);
        return button;
    }
}
