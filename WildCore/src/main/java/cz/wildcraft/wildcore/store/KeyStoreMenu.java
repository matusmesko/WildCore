package cz.wildcraft.wildcore.store;

import cz.wildcraft.wildcore.WildCore;
import cz.wildcraft.wildcore.menusystem.Menu;
import cz.wildcraft.wildcore.menusystem.PlayerMenuUtility;
import cz.wildcraft.wildcore.utils.ColorAPI;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import me.realized.tokenmanager.api.TokenManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KeyStoreMenu extends Menu {
    public KeyStoreMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "§lKlíče";
    }

    @Override
    public int getSlots() {
        return 27;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) throws SQLException {

        Player p = (Player) e.getWhoClicked();
        TokenManager tokenManager = (TokenManager) Bukkit.getPluginManager().getPlugin("TokenManager");
        Sound sound = Sound.ENTITY_PLAYER_LEVELUP;
        final long balance = tokenManager.getTokens(p).orElse(0);

        if (e.getSlot() == 10) {
            if (balance < 35) {
                p.closeInventory();
                p.sendMessage(WildCore.getPlugin().getConfig().getString("vip.notEnough"));
            }else {
                tokenManager.removeTokens(p, 35);
                p.closeInventory();
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "excellentcrates key give "+ p.getName() + " wild 1");
                p.playSound(p.getLocation(), sound, 1.0F, 1.0F);
                p.sendMessage(ColorAPI.colorize(WildCore.getPlugin().getConfig().getString("vip.purchased").replace("%item%","§71x {#c51b17}&lWild Klíč")));
            }
        }

        if (e.getSlot() == 11) {
            if (balance < 68) {
                p.closeInventory();
                p.sendMessage(WildCore.getPlugin().getConfig().getString("vip.notEnough"));
            }else {
                tokenManager.removeTokens(p, 68);
                p.closeInventory();
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "excellentcrates key give "+ p.getName() + " wild 2");
                p.playSound(p.getLocation(), sound, 1.0F, 1.0F);
                p.sendMessage(ColorAPI.colorize(WildCore.getPlugin().getConfig().getString("vip.purchased").replace("%item%","§72x {#c51b17}&lWild Klíč")));
            }
        }

        if (e.getSlot() == 12) {
            if (balance < 102) {
                p.closeInventory();
                p.sendMessage(WildCore.getPlugin().getConfig().getString("vip.notEnough"));
            }else {
                tokenManager.removeTokens(p, 102);
                p.closeInventory();
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "excellentcrates key give "+ p.getName() + " wild 3");
                p.playSound(p.getLocation(), sound, 1.0F, 1.0F);
                p.sendMessage(ColorAPI.colorize(WildCore.getPlugin().getConfig().getString("vip.purchased").replace("%item%","§73x {#c51b17}&lWild Klíč")));
            }
        }

        if (e.getSlot() == 13) {
            if (balance < 135) {
                p.closeInventory();
                p.sendMessage(WildCore.getPlugin().getConfig().getString("vip.notEnough"));
            }else {
                tokenManager.removeTokens(p, 135);
                p.closeInventory();
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "excellentcrates key give "+ p.getName() + " wild 4");
                p.playSound(p.getLocation(), sound, 1.0F, 1.0F);
                p.sendMessage(ColorAPI.colorize(WildCore.getPlugin().getConfig().getString("vip.purchased").replace("%item%","§74x {#c51b17}&lWild Klíč")));
            }
        }

        if (e.getSlot() == 14) {
            if (balance < 168) {
                p.closeInventory();
                p.sendMessage(WildCore.getPlugin().getConfig().getString("vip.notEnough"));
            }else {
                tokenManager.removeTokens(p, 168);
                p.closeInventory();
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "excellentcrates key give "+ p.getName() + " wild 5");
                p.playSound(p.getLocation(), sound, 1.0F, 1.0F);
                p.sendMessage(ColorAPI.colorize(WildCore.getPlugin().getConfig().getString("vip.purchased").replace("%item%","§75x {#c51b17}&lWild Klíč")));
            }
        }

        if (e.getSlot() == 15) {
            if (balance < 200) {
                p.closeInventory();
                p.sendMessage(WildCore.getPlugin().getConfig().getString("vip.notEnough"));
            }else {
                tokenManager.removeTokens(p, 200);
                p.closeInventory();
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "excellentcrates key give "+ p.getName() + " wild 6");
                p.playSound(p.getLocation(), sound, 1.0F, 1.0F);
                p.sendMessage(ColorAPI.colorize(WildCore.getPlugin().getConfig().getString("vip.purchased").replace("%item%","§76x {#c51b17}&lWild Klíč")));
            }
        }

        if (e.getSlot() == 16) {
            if (balance < 220) {
                p.closeInventory();
                p.sendMessage(WildCore.getPlugin().getConfig().getString("vip.notEnough"));
            }else {
                tokenManager.removeTokens(p, 220);
                p.closeInventory();
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "excellentcrates key give "+ p.getName() + " wild 7");
                p.playSound(p.getLocation(), sound, 1.0F, 1.0F);
                p.sendMessage(ColorAPI.colorize(WildCore.getPlugin().getConfig().getString("vip.purchased").replace("%item%","§77x {#c51b17}&lWild Klíč")));
            }
        }

         if (e.getSlot() == 18) {
            new StoreMenu(WildCore.getPlayerMenuUtility(p)).open();
        }

    }

    @Override
    public void setMenuItems() throws SQLException {
        // 10 - 16
        setFillerGlass();
        inventory.setItem(10, previewKey(35, 1));
        inventory.setItem(11, previewKey(68, 2));
        inventory.setItem(12, previewKey(102, 3));
        inventory.setItem(13, previewKey(135, 4));
        inventory.setItem(14, previewKey(168, 5));
        inventory.setItem(15, previewKey(200, 6));
        inventory.setItem(16, previewKey(220, 7));
        inventory.setItem(18, backButton());



    }

    private ItemStack previewKey(long l, int amount) {
        Player p = playerMenuUtility.getOwner();
        TokenManager tokenManager = (TokenManager) Bukkit.getPluginManager().getPlugin("TokenManager");
        final long balance = tokenManager.getTokens(p).orElse(0);

        ItemStack item = new ItemStack(Material.TRIPWIRE_HOOK);
        ItemMeta meta = item.getItemMeta();
        item.setAmount(amount);
        List<String> lore = new ArrayList<>();
        meta.setDisplayName(ColorAPI.colorize("{#c51b17}&lWild Klíč"));
        lore.add(" ");
        lore.add("§4▪ Cena: §7" + String.valueOf(l) + " kreditů");
        lore.add("");
        if (balance < l) {
            long required = l - balance;
            if (required == 1) {
                lore.add("§c✗ Nemáš dostatek kreditů!");
                lore.add("§cPotřebuješ ještě 1 kredit.");
            }else if (required == 2 || required == 3 || required == 4) {
                lore.add("§c✗ Nemáš dostatek kreditů!");
                lore.add("§cPotřebuješ ještě " + required + " kredity.");
            }else {
                lore.add("§c✗ Nemáš dostatek kreditů!");
                lore.add("§cPotřebuješ ještě " + required + " kreditů.");
            }
        }else {
            lore.add("§a✔ §aTento item si můžeš zakoupit!");
        }
        meta.addEnchant(Enchantment.LURE,3, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setLore(lore);
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

}
