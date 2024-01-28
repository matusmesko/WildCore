package cz.wildcraft.wildcore.warps.menus;

import cz.wildcraft.wildcore.WildCore;
import cz.wildcraft.wildcore.menusystem.Menu;
import cz.wildcraft.wildcore.menusystem.PlayerMenuUtility;
import cz.wildcraft.wildcore.playermenu.PlayerMenu;
import cz.wildcraft.wildcore.warps.database.PlayerWarpTable;
import cz.wildcraft.wildcore.warps.model.PlayerWarpModel;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OwnerWarpsMenu extends Menu {
    private final PlayerWarpTable database = new PlayerWarpTable();
    private int[] warpSlots = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44};

    public OwnerWarpsMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "Tvé warpy";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) throws SQLException {
        Player p = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();
        for (int slot : warpSlots) {
            if (e.getSlot() == slot) {
                String display = item.getItemMeta().getDisplayName();
                PlayerWarpModel warp = database.findPlayerWarpByName(display);
                new EditWarpMenu(WildCore.getPlayerMenuUtility(p), warp).open();
            }
        }


        if (e.getSlot() == 48) {
            new ServerWarpMenu(WildCore.getPlayerMenuUtility(p)).open();
        }else if (e.getSlot() == 45) {
            new PlayerMenu(WildCore.getPlayerMenuUtility(p)).open();
        } else if (e.getSlot() == 49) {
            new PlayerWarpsMenu(WildCore.getPlayerMenuUtility(p)).open();
        } else if (e.getSlot() == 50) {
            new OwnerWarpsMenu(WildCore.getPlayerMenuUtility(p)).open();
        }
    }

    @Override
    public void setMenuItems() throws SQLException {



        List<PlayerWarpModel> warps = database.getAllPlayersWarps(playerMenuUtility.getOwner().getName());
        if (warps.isEmpty()) {
            inventory.setItem(22, nothing());
        }else {
            for (PlayerWarpModel warp : warps) {
                ItemStack item = new ItemStack(Material.valueOf(warp.getIcon()));
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(warp.getWarp_name());
                List<String> lore = new ArrayList<>();
                lore.add(" ");
                lore.add("§8● §7Název: §f" + warp.getWarp_name());
                lore.add("§8● §7Majitel: §f" + warp.getOwner());
                lore.add("§8● §7Navštíveno: §f" + warp.getVisited() + "x");
                lore.add("§8● §7Svet: §f" + warp.getWorld());
                lore.add("§8● §7Založeno: §f" + warp.getTime_created());
                lore.add(" ");
                lore.add("§e➥ Klikni pro editaci");

                meta.setLore(lore);
                item.setItemMeta(meta);
                inventory.addItem(item);
            }
        }






        inventory.setItem(45, FILLER_GLASS);
        inventory.setItem(46, FILLER_GLASS);
        inventory.setItem(47, FILLER_GLASS);
        inventory.setItem(48, FILLER_GLASS);
        inventory.setItem(49, FILLER_GLASS);
        inventory.setItem(50, FILLER_GLASS);
        inventory.setItem(51, FILLER_GLASS);
        inventory.setItem(52, FILLER_GLASS);
        inventory.setItem(53, FILLER_GLASS);

        inventory.setItem(48, serverButton());
        inventory.setItem(49, playerButton());
        inventory.setItem(45,backButton());
        inventory.setItem(50,ownedButton());
    }

    private ItemStack serverButton() {
        HeadDatabaseAPI api = new HeadDatabaseAPI();
        ItemStack button = api.getItemHead("8742");
        ItemMeta meta = button.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add("§8● §fOtevreš seznam serverových warpů");
        lore.add(" ");
        lore.add("§e➥ Klikni pro zobrazení");
        meta.setLore(lore);
        meta.setDisplayName("§b§lServerové warpy");
        button.setItemMeta(meta);
        return button;
    }

    private ItemStack playerButton() {
        HeadDatabaseAPI api = new HeadDatabaseAPI();
        ItemStack button = api.getItemHead("50239");
        ItemMeta meta = button.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add("§8● §fOtevreš seznam hráčských warpů");
        lore.add(" ");
        lore.add("§e➥ Klikni pro zobrazení");
        meta.setLore(lore);
        meta.setDisplayName("§c§lHráčské warpy");
        button.setItemMeta(meta);
        return button;
    }

    private ItemStack nothing() {
        ItemStack item = new ItemStack(Material.BARRIER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§c§lNevlastníš žádné warpy");
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

    private ItemStack ownedButton() {
        HeadDatabaseAPI api = new HeadDatabaseAPI();
        ItemStack button = api.getItemHead("15991");
        ItemMeta meta = button.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add("§8● §fOtevreš seznam tvých warpů");
        lore.add(" ");
        lore.add("§e➥ Klikni pro zobrazení");
        meta.setLore(lore);
        meta.setDisplayName("§9§lTvé warpy");
        button.setItemMeta(meta);
        return button;
    }
}
