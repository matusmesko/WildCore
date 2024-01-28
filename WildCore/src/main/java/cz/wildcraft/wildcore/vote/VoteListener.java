package cz.wildcraft.wildcore.vote;

import com.vexsoftware.votifier.model.Vote;
import com.vexsoftware.votifier.model.VotifierEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class VoteListener implements Listener {

    @EventHandler
    public void onVorifierEvent(VotifierEvent e) {
        Vote vote = e.getVote();
        Player p = Bukkit.getPlayer(vote.getUsername());
        Bukkit.broadcastMessage("§c§lW§6§lC §8§l» §7Hráč §c" + vote.getUsername() + " §7právě zahlasoval za server!");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "excellentcrates key give " + vote.getUsername() +" vote 1");
        p.getInventory().addItem(new ItemStack(Material.DIAMOND));
        p.playSound(p.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1.0f, 1.0f);
    }
}
