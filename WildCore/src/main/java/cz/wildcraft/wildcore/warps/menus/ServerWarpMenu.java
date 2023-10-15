package cz.wildcraft.wildcore.warps.menus;

import cz.wildcraft.wildcore.menusystem.Menu;
import cz.wildcraft.wildcore.menusystem.PlayerMenuUtility;
import cz.wildcraft.wildcore.warps.database.ServerWarpTable;
import cz.wildcraft.wildcore.warps.model.ServerWarpModel;
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
    private final ServerWarpTable database;
    public ServerWarpMenu(PlayerMenuUtility playerMenuUtility, ServerWarpTable database) {
        super(playerMenuUtility);
        this.database = database;
    }

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
        String display = item.getItemMeta().getDisplayName();
        System.out.println("ITEM DISPLAY NAME: " + display);
        Player p = (Player) e.getWhoClicked();
        ServerWarpModel warp = database.findServerWarpByName(display);
        World world = Bukkit.getWorld(warp.getWorld());
        Location location = new Location(world, warp.getX(), warp.getY(), warp.getZ(), warp.getYaw(), warp.getPitch());
        p.teleport(location);

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

        for (ServerWarpModel warp : database.getAllServerWarps()) {
            ItemStack item = new ItemStack(Material.valueOf(warp.getIcon()));
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(warp.getWarp_name());


            List<String> lore = new ArrayList<>();
            lore.add(" ");
            lore.add("§c| §7Název: §f" + warp.getWarp_name());
            lore.add("§c| §7description: §f" + warp.getDescription());
            lore.add("§c| §7Svet: §f" + warp.getWorld());
            lore.add("§c| §7Založeno: §f" + warp.getTime_created());
            lore.add(" ");
            lore.add("§e| Klikni pro teleport");
            meta.setLore(lore);
            item.setItemMeta(meta);
            inventory.addItem(item);
        }
    }
}
