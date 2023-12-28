package cz.wildcraft.wildcore.warps.menus;

import cz.wildcraft.wildcore.WildCore;
import cz.wildcraft.wildcore.menusystem.PaginatedMenu;
import cz.wildcraft.wildcore.menusystem.PlayerMenuUtility;
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

public class PlayerWarpsMenu extends PaginatedMenu {
    public PlayerWarpsMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    private final PlayerWarpTable database = new PlayerWarpTable();

    private int[] warpSlots = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44};


    @Override
    public String getMenuName() {
        return "Seznam hráčských warpu";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) throws SQLException {
        ItemStack item = e.getCurrentItem();
        Player p = (Player) e.getWhoClicked();
        for (int slot : warpSlots) {
            if (e.getSlot() == slot) {
                String display = item.getItemMeta().getDisplayName();
                PlayerWarpModel warp = database.findPlayerWarpByName(display);
                World world = Bukkit.getWorld(warp.getWorld());
                Location location = new Location(world, warp.getX(), warp.getY(), warp.getZ(), warp.getYaw(), warp.getPitch());
                p.teleport(location);
                warp.setVisited(warp.getVisited() + 1);
                database.updateWarp(warp);
            }
        }

        if (e.getSlot() == 49) {
            new ServerWarpMenu(WildCore.getPlayerMenuUtility(p)).open();
        }
    }

    @Override
    public void setMenuItems() throws SQLException {
        inventory.setItem(45, FILLER_GLASS);
        inventory.setItem(46, FILLER_GLASS);
        inventory.setItem(47, FILLER_GLASS);
        inventory.setItem(48, FILLER_GLASS);
        inventory.setItem(49, FILLER_GLASS);
        inventory.setItem(50, FILLER_GLASS);
        inventory.setItem(51, FILLER_GLASS);
        inventory.setItem(52, FILLER_GLASS);
        inventory.setItem(53, FILLER_GLASS);



        List<PlayerWarpModel> warps = database.getAllPlayerWarps();
        for (PlayerWarpModel warp : warps) {
            ItemStack item = new ItemStack(Material.valueOf(warp.getIcon()));
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(warp.getWarp_name());
            List<String> lore = new ArrayList<>();
            lore.add(" ");
            lore.add("§c▪ §7Název: §f" + warp.getWarp_name());
            lore.add("§c▪ §7Majitel: §f" + warp.getOwner());
            lore.add("§c▪ §7Navštíveno: §f" + warp.getVisited() + "x");
            lore.add("§c▪ §7Svet: §f" + warp.getWorld());
            lore.add("§c▪ §7Založeno: §f" + warp.getTime_created());
            lore.add(" ");
            lore.add("§e➥ Klikni pro teleport");

            meta.setLore(lore);
            item.setItemMeta(meta);
            inventory.addItem(item);

            inventory.setItem(49, serverButton());
            inventory.setItem(50, playerButton());
        }

    }

    private ItemStack serverWarpButton() {
        ItemStack pWarps = new ItemStack(Material.DIAMOND_BLOCK);
        ItemMeta pWarpmeta = pWarps.getItemMeta();
        pWarpmeta.setDisplayName("§b§lServerové warpy");
        List<String> pWarplore = new ArrayList<>();
        pWarplore.add(" ");
        pWarplore.add("§c▪ §fOtevreš seznam serverových warpů");
        pWarplore.add(" ");
        pWarplore.add("§e➥ Klikni pro zobrazení");
        pWarpmeta.setLore(pWarplore);
        pWarps.setItemMeta(pWarpmeta);
        return pWarps;
    }

    private ItemStack serverButton() {
        HeadDatabaseAPI api = new HeadDatabaseAPI();
        ItemStack button = api.getItemHead("8742");
        ItemMeta meta = button.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add("§c▪ §fOtevreš seznam serverových warpů");
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
        lore.add("§c▪ §fOtevreš seznam hráčských warpů");
        lore.add(" ");
        lore.add("§e➥ Klikni pro zobrazení");
        meta.setLore(lore);
        meta.setDisplayName("§c§lHráčské warpy");
        button.setItemMeta(meta);
        return button;
    }
}
