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

public class VipStoreMenu extends Menu {

    private int legendaryCost = WildCore.getPlugin().getConfig().getInt("vip.legendary");
    private int ultimateCost = WildCore.getPlugin().getConfig().getInt("vip.ultimate");

    public VipStoreMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "§lVIP Ranky";
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

        if (e.getSlot() == 12) {
            if (balance < legendaryCost) {
                p.closeInventory();
                p.sendMessage(WildCore.getPlugin().getConfig().getString("vip.notEnough"));
            }else {
                tokenManager.removeTokens(p, legendaryCost);
                p.closeInventory();
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "giveactivableitem legendary15 " + p.getName());
                p.playSound(p.getLocation(), sound, 1.0F, 1.0F);
                p.sendMessage(ColorAPI.colorize(WildCore.getPlugin().getConfig().getString("vip.purchased").replace("%item%","{#81e600>}&lLegendary{#006623<} §7(15 dní)")));
            }
        }else if (e.getSlot() == 14) {
            if (balance < ultimateCost) {
                p.closeInventory();
                p.sendMessage(WildCore.getPlugin().getConfig().getString("vip.notEnough"));
            }else {
                tokenManager.removeTokens(p, ultimateCost);
                p.closeInventory();
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "giveactivableitem ultimate15 " + p.getName());
                p.playSound(p.getLocation(), sound, 1.0F, 1.0F);
                p.sendMessage(ColorAPI.colorize(WildCore.getPlugin().getConfig().getString("vip.purchased").replace("%item%","{#e67c00>}&lUltimate{#bccc00<} §7(15 dní)")));
            }
        }else if (e.getSlot() == 18) {
            new StoreMenu(WildCore.getPlayerMenuUtility(p)).open();
        }
    }

    @Override
    public void setMenuItems() throws SQLException {
        // 12 14
        setFillerGlass();
        inventory.setItem(12, legendary());
        inventory.setItem(14, ultimate());
        inventory.setItem(18, backButton());
    }

    private ItemStack legendary() {
        Player p = playerMenuUtility.getOwner();
        TokenManager tokenManager = (TokenManager) Bukkit.getPluginManager().getPlugin("TokenManager");
        final long balance = tokenManager.getTokens(p).orElse(0);

        ItemStack item = new ItemStack(Material.EMERALD);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§8● §aCena: §7" + legendaryCost + " kreditů");
        lore.add("");
        lore.add("§8● §aVýhody:");
        lore.add("");
        lore.add("§8● §7/feed");
        lore.add("§8● §7/heal");
        lore.add("§8● §7/back");
        lore.add("§8● §7/sit");
        lore.add("§8● §7/anvil");
        lore.add("§8● §7/enchant");
        lore.add("§8● §7/ender");
        lore.add("§8● §7/hat");
        lore.add("§8● §7/repair");
        lore.add("§8● §73x /setwarpwarp");
        lore.add("§8● §76x /sethome");
        lore.add("§8● §75x residence");
        lore.add("§8● §7residence 150x150");
        lore.add("§8● §75min AFK");
        lore.add("§8● §75 itemů /ah");
        lore.add("§8● §7/vault (27 slotů)");
        lore.add("");
        if (balance < legendaryCost) {
            long required = legendaryCost - balance;
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
        meta.setDisplayName(ColorAPI.colorize("{#81e600>}&lLegendary{#006623<} §7(15 dní)"));
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    private ItemStack ultimate() {
        Player p = playerMenuUtility.getOwner();
        TokenManager tokenManager = (TokenManager) Bukkit.getPluginManager().getPlugin("TokenManager");
        final long balance = tokenManager.getTokens(p).orElse(0);

        ItemStack item = new ItemStack(Material.GOLD_INGOT);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§8● §eCena: §7" + ultimateCost + " kreditů");
        lore.add("");
        lore.add("§8● §eVýhody:");
        lore.add("");
        lore.add("§8● §7/feed");
        lore.add("§8● §7/heal");
        lore.add("§8● §7/back");
        lore.add("§8● §7/sit");
        lore.add("§8● §7/anvil");
        lore.add("§8● §7/enchant");
        lore.add("§8● §7/ender");
        lore.add("§8● §7/hat");
        lore.add("§8● §7/repair");
        lore.add("§8● §75x /setwarpwarp");
        lore.add("§8● §715x /sethome");
        lore.add("§8● §78x residence");
        lore.add("§8● §7residence 300x300");
        lore.add("§8● §7AFK bypass");
        lore.add("§8● §710 itemů /ah");
        lore.add("§8● §7/vault (54 slotů)");
        lore.add("");

        if (balance < ultimateCost) {
            long required = ultimateCost - balance;
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
        meta.setDisplayName(ColorAPI.colorize("{#e67c00>}&lUltimate{#bccc00<} §7(15 dní)"));
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
