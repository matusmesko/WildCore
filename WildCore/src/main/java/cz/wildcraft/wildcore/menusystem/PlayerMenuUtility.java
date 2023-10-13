package cz.wildcraft.wildcore.menusystem;


/*
Companion class to all menus. This is needed to pass information across the entire
 menu system no matter how many inventories are opened or closed.

 Each player has one of these objects, and only one.
 */

import org.bukkit.entity.Player;

public class PlayerMenuUtility {
    private Player owner;
    //store the player that will be killed so we can access him in the next menu
    private Player player;

    public PlayerMenuUtility(Player p) {
        this.owner = p;
    }

    public Player getOwner() {
        return owner;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player playerToKill) {
        this.player = playerToKill;
    }

}
