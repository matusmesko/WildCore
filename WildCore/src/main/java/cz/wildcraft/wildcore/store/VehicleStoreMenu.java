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

public class VehicleStoreMenu extends Menu {
    public VehicleStoreMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "§lVozidla";
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
        if (e.getSlot() == 18) {
            new StoreMenu(WildCore.getPlayerMenuUtility(p)).open();
        }else if (e.getSlot() == 12) {

            if (balance < 15) {
                p.closeInventory();
                p.sendMessage(WildCore.getPlugin().getConfig().getString("vip.notEnough"));
            }else {
                tokenManager.removeTokens(p, 15);
                p.closeInventory();
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "givedrill " + p.getName() + " D1");
                p.playSound(p.getLocation(), sound, 1.0F, 1.0F);
                p.sendMessage(ColorAPI.colorize(WildCore.getPlugin().getConfig().getString("vip.purchased").replace("%item%","§6§lDrill Level 1")));
            }

        } else if (e.getSlot() == 13) {
            if (balance < 25) {
                p.closeInventory();
                p.sendMessage(WildCore.getPlugin().getConfig().getString("vip.notEnough"));
            }else {
                tokenManager.removeTokens(p, 25);
                p.closeInventory();
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "givedrill " + p.getName() + " D2");
                p.playSound(p.getLocation(), sound, 1.0F, 1.0F);
                p.sendMessage(ColorAPI.colorize(WildCore.getPlugin().getConfig().getString("vip.purchased").replace("%item%","§7§lDrill Level 2")));
            }
        } else if (e.getSlot() == 14) {
            if (balance < 45) {
                p.closeInventory();
                p.sendMessage(WildCore.getPlugin().getConfig().getString("vip.notEnough"));
            }else {
                tokenManager.removeTokens(p, 45);
                p.closeInventory();
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "givedrill " + p.getName() + " D3");
                p.playSound(p.getLocation(), sound, 1.0F, 1.0F);
                p.sendMessage(ColorAPI.colorize(WildCore.getPlugin().getConfig().getString("vip.purchased").replace("%item%","§e§lDrill Level 3")));
            }
        }


    }

    @Override
    public void setMenuItems() throws SQLException {
        setFillerGlass();
        inventory.setItem(18, backButton());
        inventory.setItem(12, d1Drill());
        inventory.setItem(13, d2Drill());
        inventory.setItem(14, d3Drill());
    }

    private ItemStack backButton() {
        HeadDatabaseAPI api = new HeadDatabaseAPI();
        ItemStack button = api.getItemHead("9226");
        ItemMeta meta = button.getItemMeta();
        meta.setDisplayName("§7Zpět");
        button.setItemMeta(meta);
        return button;
    }

    private ItemStack d1Drill() {
        Player p = playerMenuUtility.getOwner();
        TokenManager tokenManager = (TokenManager) Bukkit.getPluginManager().getPlugin("TokenManager");
        final long balance = tokenManager.getTokens(p).orElse(0);

        ItemStack item = new ItemStack(Material.WOODEN_PICKAXE);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        meta.setDisplayName("§6§lDrill Level 1");
        lore.add(" ");
        lore.add("§8● Cena: §715 kreditů");
        lore.add("");
        if (balance < 15) {
            long required = 15 - balance;
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
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    private ItemStack d2Drill() {
        Player p = playerMenuUtility.getOwner();
        TokenManager tokenManager = (TokenManager) Bukkit.getPluginManager().getPlugin("TokenManager");
        final long balance = tokenManager.getTokens(p).orElse(0);

        ItemStack item = new ItemStack(Material.IRON_PICKAXE);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        meta.setDisplayName("§7§lDrill Level 2");
        lore.add(" ");
        lore.add("§8● Cena: §725 kreditů");
        lore.add("");
        if (balance < 25) {
            long required = 25 - balance;
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
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    private ItemStack d3Drill() {
        Player p = playerMenuUtility.getOwner();
        TokenManager tokenManager = (TokenManager) Bukkit.getPluginManager().getPlugin("TokenManager");
        final long balance = tokenManager.getTokens(p).orElse(0);

        ItemStack item = new ItemStack(Material.GOLDEN_PICKAXE);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        meta.setDisplayName("§e§lDrill Level 3");
        lore.add(" ");
        lore.add("§8● Cena: §745 kreditů");
        lore.add("");
        if (balance < 45) {
            long required = 45 - balance;
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
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
}
