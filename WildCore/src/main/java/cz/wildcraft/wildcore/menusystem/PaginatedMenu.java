package cz.wildcraft.wildcore.menusystem;

/*

A class extending the functionality of the regular Menu, but making it Paginated

This pagination system was made from Jer's code sample. <3

 */

import cz.wildcraft.wildcore.WildCore;
import org.bukkit.ChatColor;
import org.bukkit.Material;


public abstract class PaginatedMenu extends Menu{
    //Keep track of what page the menu is on
    protected int page = 0;
    //28 is max items because with the border set below,
    //28 empty slots are remaining.
    protected int maxItemsPerPage = 28;
    //the index represents the index of the slot
    //that the loop is on
    protected int index = 0;

    public PaginatedMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    //Set the border and menu buttons for the menu
    public void addMenuBorder(){
        inventory.setItem(48, makeItem(Material.valueOf(WildCore.getPlugin().getConfig().getString("next-page.item")), WildCore.getPlugin().getConfig().getString("next-page.display")));

        inventory.setItem(49, makeItem(Material.valueOf(WildCore.getPlugin().getConfig().getString("close-page.item")), WildCore.getPlugin().getConfig().getString("close-page.display")));

        inventory.setItem(50, makeItem(Material.valueOf(WildCore.getPlugin().getConfig().getString("previous-page.item")), WildCore.getPlugin().getConfig().getString("previous-page.display")));



        for (int i = 44; i < 54; i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, super.FILLER_GLASS);
            }
        }
    }

    public int getMaxItemsPerPage() {
        return maxItemsPerPage;
    }

}
