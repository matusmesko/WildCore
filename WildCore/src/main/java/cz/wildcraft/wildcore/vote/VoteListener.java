package cz.wildcraft.wildcore.vote;

import com.vexsoftware.votifier.model.Vote;
import com.vexsoftware.votifier.model.VotifierEvent;
import cz.wildcraft.wildcore.utils.ColorAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VoteListener implements Listener {

    private VoteTable voteTable = new VoteTable();

    @EventHandler
    public void onVorifierEvent(VotifierEvent e) throws SQLException {
        Vote vote = e.getVote();
        Player p = Bukkit.getPlayer(vote.getUsername());
        int count = voteTable.getPlayerVotes(p.getName());

        Bukkit.broadcastMessage("§c§lW§6§lC §8§l» §7Hráč §c" + vote.getUsername() + " §7právě zahlasoval za server!");
        if (count % 50 == 0) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "excellentcrates key give " + vote.getUsername() +" vote 1");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "excellentcrates key give " + vote.getUsername() +" wild 1");
        }else {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "excellentcrates key give " + vote.getUsername() + " vote 1");
        }
        VoteModel model = new VoteModel(vote.getUsername(), vote.getServiceName(), vote.getTimeStamp());
        voteTable.createVote(model);
        if (p == null) return;
        p.getInventory().addItem(new ItemStack(Material.DIAMOND));
        p.playSound(p.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1.0f, 1.0f);
    }

    private ItemStack voteKey() {
        ItemStack item = new ItemStack(Material.TRIPWIRE_HOOK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ColorAPI.colorize("{#e6cf00}§lVote Klíč"));
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§7Tímto klíčem můžeš");
        lore.add("§7otevřít vote truhlu!");
        lore.add("");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.MENDING, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_DYE);
        meta.setUnbreakable(true);
        item.setDurability((short) 0);
        item.setItemMeta(meta);
        return item;
    }
}
