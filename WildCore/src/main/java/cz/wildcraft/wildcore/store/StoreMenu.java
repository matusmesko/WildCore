package cz.wildcraft.wildcore.store;

import cz.wildcraft.wildcore.WildCore;
import cz.wildcraft.wildcore.menusystem.Menu;
import cz.wildcraft.wildcore.menusystem.PlayerMenuUtility;
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
        }
    }

    @Override
    public void setMenuItems() throws SQLException {
        setFillerGlass();
        // 12 14 4
        inventory.setItem(12, vipRanks());
        inventory.setItem(14, keys());
    }

    private ItemStack vipRanks() {
        ItemStack itemStack = new ItemStack(Material.GOLD_BLOCK);
        ItemMeta meta = itemStack.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§e▪ §7Tady si můžeš koupit");
        lore.add("§e▪ §7všechny vip ranky");
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
        lore.add("§a▪ §7Tady si můžeš koupit");
        lore.add("§a▪ §7všechny klíče");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LURE,3, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setDisplayName("§a§lKlíče");
        item.setItemMeta(meta);
        return item;
    }
}
