package cz.wildcraft.wildcore.store;

import cz.wildcraft.wildcore.WildCore;
import cz.wildcraft.wildcore.menusystem.Menu;
import cz.wildcraft.wildcore.menusystem.PlayerMenuUtility;
import cz.wildcraft.wildcore.playermenu.PlayerMenu;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StoreMenu extends Menu {
    public StoreMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "§lStore";
    }

    @Override
    public int getSlots() {
        return 27;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) throws SQLException {
        Player p = (Player) e.getWhoClicked();
        if (e.getSlot() == 12) {
            new VipStoreMenu(WildCore.getPlayerMenuUtility(p)).open();
        }else if (e.getSlot() == 14) {
            new KeyStoreMenu(WildCore.getPlayerMenuUtility(p)).open();
        } else if (e.getSlot() == 18) {
            new PlayerMenu(WildCore.getPlayerMenuUtility(p)).open();
        }else if (e.getSlot() == 4) {
            storeMessage(p);
        }
    }

    @Override
    public void setMenuItems() throws SQLException {
        setFillerGlass();
        // 12 14 4
        inventory.setItem(12, vipRanks());
        inventory.setItem(14, keys());
        inventory.setItem(18, backButton());
        inventory.setItem(4, info());
    }

    private ItemStack vipRanks() {
        ItemStack itemStack = new ItemStack(Material.GOLD_BLOCK);
        ItemMeta meta = itemStack.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§8● §7Tady si můžeš koupit");
        lore.add("§8● §7všechny vip ranky");
        meta.setLore(lore);
        meta.setDisplayName("§e§lVIP Ranky");
        meta.addEnchant(Enchantment.LURE,3, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    private ItemStack keys() {
        ItemStack item = new ItemStack(Material.TRIPWIRE_HOOK);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§8● §7Tady si můžeš koupit");
        lore.add("§8● §7všechny klíče");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LURE,3, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setDisplayName("§a§lKlíče");
        item.setItemMeta(meta);
        return item;
    }

    private ItemStack backButton() {
        HeadDatabaseAPI api = new HeadDatabaseAPI();
        ItemStack button = api.getItemHead("9226");
        ItemMeta meta = button.getItemMeta();
        meta.setDisplayName("§7Zpět");
        button.setItemMeta(meta);
        return button;
    }

    private ItemStack info() {
        ItemStack item = new ItemStack(Material.BOOK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§e§lJak získat kredity ?");
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§fKredity můžeš získat hraním, s truhly");
        lore.add("§fnebo si je můžeš zakoupit v našem obchodě");
        lore.add("");
        lore.add("§e➥ Klikni pro nákup");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    private void storeMessage(Player p) {
        p.sendMessage("");
        p.sendMessage("§e| §7Tady si můžeš zakoupit kredity");
        p.sendMessage("§e| https://wildcraftcz.craftingstore.net/category/417000");
        p.sendMessage("");
    }
}
