package cz.wildcraft.wildcore.gender;


import cz.wildcraft.wildcore.WildCore;
import cz.wildcraft.wildcore.menusystem.Menu;
import cz.wildcraft.wildcore.menusystem.PlayerMenuUtility;
import cz.wildcraft.wildcore.playermenu.PlayerMenu;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.SQLException;

public class GenderMenu extends Menu {
    public GenderMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    private final GenderTable genderTable = new GenderTable();

    @Override
    public String getMenuName() {
        return "§lPohlaví";
    }

    @Override
    public int getSlots() {
        return 27;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) throws SQLException {
        Player p = (Player) e.getWhoClicked();
        GenderModel model = genderTable.findPlayerByName(p.getName());
        if (e.getSlot() == 12) {
            model.setGender("male");
            genderTable.updateGender(model);
            p.closeInventory();
            p.sendMessage(WildCore.getPlugin().getConfig().getString("gender.gender-changed").replace("%gender%", "§bmuž"));
        }

        if (e.getSlot() == 14) {
            model.setGender("female");
            genderTable.updateGender(model);
            p.closeInventory();
            p.sendMessage(WildCore.getPlugin().getConfig().getString("gender.gender-changed").replace("%gender%", "§džena"));
        }

        if (e.getSlot() == 18) {
            new PlayerMenu(WildCore.getPlayerMenuUtility(p)).open();
        }

    }

    @Override
    public void setMenuItems() throws SQLException {
        setFillerGlass();
        //12 14
        ItemStack male = makeItem(Material.LIGHT_BLUE_CONCRETE,"§b§lMuž");
        ItemStack female = makeItem(Material.PINK_CONCRETE, "§d§lŽena");
        inventory.setItem(12, male);
        inventory.setItem(14, female);
        inventory.setItem(18, backButton());
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
