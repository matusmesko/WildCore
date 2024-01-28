package cz.wildcraft.wildcore.warps.menus;

import cz.wildcraft.wildcore.WildCore;
import cz.wildcraft.wildcore.menusystem.Menu;
import cz.wildcraft.wildcore.menusystem.PlayerMenuUtility;
import cz.wildcraft.wildcore.playermenu.PlayerMenu;
import cz.wildcraft.wildcore.warps.database.ServerWarpTable;
import cz.wildcraft.wildcore.warps.model.ServerWarpModel;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServerWarpMenu extends Menu {
    private ServerWarpTable database = new ServerWarpTable();
    public ServerWarpMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    private int[] warpSlots = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44};

    @Override
    public String getMenuName() {
        return "Seznam serverových warpů";
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
                ServerWarpModel warp = database.findServerWarpByName(display);
                World world = Bukkit.getWorld(warp.getWorld());
                Location location = new Location(world, warp.getX(), warp.getY(), warp.getZ(), warp.getYaw(), warp.getPitch());
                p.teleport(location);
            }
        }

        if (e.getSlot() == 49) {
            new PlayerWarpsMenu(WildCore.getPlayerMenuUtility(p)).open();
        }else if (e.getSlot() == 45) {
            new PlayerMenu(WildCore.getPlayerMenuUtility(p)).open();
        } else if (e.getSlot() == 50) {
            new OwnerWarpsMenu(WildCore.getPlayerMenuUtility(p)).open();
        }

    }

    @Override
    public void setMenuItems() throws SQLException {
        inventory.setItem(45, FILLER_GLASS);
        inventory.setItem(46, FILLER_GLASS);
        inventory.setItem(47, FILLER_GLASS);
        inventory.setItem(48, FILLER_GLASS);
        inventory.setItem(50, FILLER_GLASS);
        inventory.setItem(51, FILLER_GLASS);
        inventory.setItem(52, FILLER_GLASS);
        inventory.setItem(53, FILLER_GLASS);

        for (ServerWarpModel warp : database.getAllServerWarps()) {
            ItemStack item = new ItemStack(Material.valueOf(warp.getIcon()));
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(warp.getWarp_name());


            List<String> lore = new ArrayList<>();
            lore.add(" ");
            lore.add("§8● §7Název: §f" + warp.getWarp_name());
            lore.add("§8● §7description: §f" + warp.getDescription());
            lore.add("§8● §7Svet: §f" + warp.getWorld());
            lore.add(" ");
            lore.add("§e➥ Klikni pro teleport");
            meta.setLore(lore);
            item.setItemMeta(meta);
            inventory.addItem(item);
        }

        ItemStack pWarps = new ItemStack(Material.REDSTONE_BLOCK);
        ItemMeta pWarpmeta = pWarps.getItemMeta();
        pWarpmeta.setDisplayName("§c§lHráčské warpy");
        List<String> pWarplore = new ArrayList<>();
        pWarplore.add(" ");
        pWarplore.add("§8● §fOtevreš seznam hráčských warpů");
        pWarplore.add(" ");
        pWarplore.add("§e➥ Klikni pro zobrazení");
        pWarpmeta.setLore(pWarplore);
        pWarps.setItemMeta(pWarpmeta);

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
        meta.setDisplayName("§c§lNic k videní");
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
