package cz.wildcraft.wildcore.playermenu;

import cz.wildcraft.wildcore.WildCore;
import cz.wildcraft.wildcore.gender.GenderMenu;
import cz.wildcraft.wildcore.menusystem.Menu;
import cz.wildcraft.wildcore.menusystem.PlayerMenuUtility;
import cz.wildcraft.wildcore.store.StoreMenu;
import cz.wildcraft.wildcore.warps.menus.ServerWarpMenu;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerMenu extends Menu {
    public PlayerMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "§lMenu";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) throws SQLException {
        Player p = (Player) e.getWhoClicked();
        if (e.getSlot() == 19) {
            new ServerWarpMenu(WildCore.getPlayerMenuUtility(p)).open();
        }else if (e.getSlot() == 20) {
            new StoreMenu(WildCore.getPlayerMenuUtility(p)).open();
        } else if (e.getSlot() == 21) {
            p.closeInventory();
            p.sendMessage(" ");
            p.sendMessage("§9| §7Připoj se na náš discord server");
            p.sendMessage("§9| §9discord.gg/qRRbBXeq8T");
            p.sendMessage(" ");
        } else if (e.getSlot() == 22) {
            p.closeInventory();
            Bukkit.dispatchCommand(p, "ah");
        } else if (e.getSlot() == 23) {
            new GenderMenu(WildCore.getPlayerMenuUtility(p)).open();
        } else if (e.getSlot() == 24) {
            p.closeInventory();
            Bukkit.dispatchCommand(p,"shop");
        } else if (e.getSlot() == 25) {
            p.closeInventory();
            Bukkit.dispatchCommand(p,"kit");
        } else if (e.getSlot() == 28) {
            p.closeInventory();
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "deluxemenu open jobsmain " + p.getName());
        }else if (e.getSlot() == 29) {
            p.closeInventory();
            Bukkit.dispatchCommand(p,"daily");
        }
    }

    @Override
    public void setMenuItems() throws SQLException {
        setFillerGlass();
        inventory.setItem(13, playersSkull());
        inventory.setItem(19, warpButton());
        inventory.setItem(20, storeButton());
        inventory.setItem(21, discordButton());
        inventory.setItem(22, auctionButton());
        inventory.setItem(23, genderButton());
        inventory.setItem(24, serverShopButton());
        inventory.setItem(25, kitsButton());
        inventory.setItem(28, jobsButton());
        inventory.setItem(29, dailyRewardsButton());

        //●
    }

    private ItemStack playersSkull() {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
        skullMeta.setOwner(playerMenuUtility.getOwner().getDisplayName());
        skullMeta.setDisplayName("§7" + playerMenuUtility.getOwner().getName());
        List<String> lore = new ArrayList<>();
        skullMeta.setLore(lore);
        item.setItemMeta(skullMeta);
        return item;
    }

    private ItemStack warpButton() {
        ItemStack item = new ItemStack(Material.ENDER_EYE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§a§lWarpy");
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add("§8● §fZde najdeš serverové");
        lore.add("§8● §fa hráčské warpy");
        lore.add("");
        lore.add("§e➥ Klikni pro zobrazení");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    private ItemStack storeButton() {
        ItemStack item = new ItemStack(Material.GOLD_BLOCK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§e§lObchod");
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add("§8● §fZde si můžeš zakoupit");
        lore.add("§8● §fVIP ranky a klíče");
        lore.add("");
        lore.add("§e➥ Klikni pro zobrazení");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    private ItemStack discordButton() {
        HeadDatabaseAPI api = new HeadDatabaseAPI();
        ItemStack button = api.getItemHead("4320");
        ItemMeta meta = button.getItemMeta();
        List<String> lore = new ArrayList<>();
        meta.setDisplayName("§9§lDiscord");
        lore.add("");
        lore.add("§8● §fPřipoj se na náš discord kde");
        lore.add("§8● §fnajdeš všechny noviny");
        lore.add("");
        lore.add("§e➥ Klikni pro zobrazení");
        meta.setLore(lore);
        button.setItemMeta(meta);
        return button;
    }

    private ItemStack auctionButton() {
        HeadDatabaseAPI api = new HeadDatabaseAPI();
        ItemStack button = api.getItemHead("50239");
        ItemMeta meta = button.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add("§8● §fZde najdeš všechny položky");
        lore.add("§8● §fv aukci");
        lore.add(" ");
        lore.add("§e➥ Klikni pro zobrazení");
        meta.setLore(lore);
        meta.setDisplayName("§c§lAukce");
        button.setItemMeta(meta);
        return button;
    }

    private ItemStack genderButton() {
        ItemStack item = new ItemStack(Material.LIGHT_BLUE_CONCRETE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§b§lPohlaví");
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add("§8● §fTady si můžeš změnit");
        lore.add("§8● §fsvé pohlaví");
        lore.add("");
        lore.add("§e➥ Klikni pro zobrazení");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    private ItemStack serverShopButton() {
        HeadDatabaseAPI api = new HeadDatabaseAPI();
        ItemStack button = api.getItemHead("50243");
        ItemMeta meta = button.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add("§8● §fZde najdeš serverový obchod");
        lore.add("§8● §fve kterém si můžeš koupit itemy");
        lore.add(" ");
        lore.add("§e➥ Klikni pro zobrazení");
        meta.setLore(lore);
        meta.setDisplayName("§a§lServerový obchod");
        button.setItemMeta(meta);
        return button;
    }

    private ItemStack kitsButton() {
        ItemStack item = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6§lKITY");
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add("§8● §fZde najdeš všechny");
        lore.add("§8● §fdostupné kity");
        lore.add("");
        lore.add("§e➥ Klikni pro zobrazení");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    private ItemStack jobsButton() {
        ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§b§lPRÁCE");
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add("§8● §fZde najdeš všechny");
        lore.add("§8● §fdostupné práce");
        lore.add("");
        lore.add("§e➥ Klikni pro zobrazení");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    private ItemStack dailyRewardsButton() {
        ItemStack item = new ItemStack(Material.GOLD_INGOT);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§b§lDenní odměny");
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add("§8● §fPřipoj se na server každý");
        lore.add("§8● §fden a získej odměny");
        lore.add("");
        lore.add("§e➥ Klikni pro zobrazení");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }


}
