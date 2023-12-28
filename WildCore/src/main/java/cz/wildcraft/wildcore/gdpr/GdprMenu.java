package cz.wildcraft.wildcore.gdpr;

import cz.wildcraft.wildcore.WildCore;
import cz.wildcraft.wildcore.menusystem.Menu;
import cz.wildcraft.wildcore.menusystem.PlayerMenuUtility;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GdprMenu extends Menu {
    public GdprMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);

    }

    private final GdprTable gdprTable = new GdprTable();

    @Override
    public String getMenuName() {
        return "§lGDPR";
    }

    @Override
    public int getSlots() {
        return 27;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) throws SQLException {
        Player p = (Player) e.getWhoClicked();
        if (e.getSlot() == 12) {
            GdprModel model = gdprTable.findPlayerByName(p.getName());
            DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            String data = df.format(new Date());
            model.setAccepted(true);
            model.setAccept_date(data);
            gdprTable.updateGdpr(model);
            p.closeInventory();
        }
        if (e.getSlot() == 14) {
            p.kickPlayer(WildCore.getPlugin().getConfig().getString("gdpr.kick"));
        }

    }

    @Override
    public void setMenuItems() throws SQLException {
        setFillerGlass();
        ArrayList<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add("§fSouhlasíš s GDPR (Zásady zpracování osobních údajů) ?");
        lore.add("§fGDPR najdeš na našem discordu");

        ItemStack accept = makeItem(Material.LIME_TERRACOTTA, "§a§lSOUHLASÍM");
        ItemStack decline = makeItem(Material.RED_TERRACOTTA, "§c§lNESOUHLASÍM");
        ItemStack book = makeItem(Material.BOOK, "§e§lGDPR");
        ItemMeta meta = book.getItemMeta();
        meta.setLore(lore);
        book.setItemMeta(meta);

        inventory.setItem(12, accept);
        inventory.setItem(14, decline);
        inventory.setItem(4, book);
    }
}
