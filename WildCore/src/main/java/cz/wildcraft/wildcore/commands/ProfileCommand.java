package cz.wildcraft.wildcore.commands;

import cz.wildcraft.wildcore.gender.GenderModel;
import cz.wildcraft.wildcore.gender.GenderTable;
import cz.wildcraft.wildcore.utils.Utils;
import cz.wildcraft.wildcore.vote.VoteTable;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

public class ProfileCommand implements CommandExecutor {

    private final GenderTable genderTable = new GenderTable();
    private final VoteTable voteTable = new VoteTable();
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player p = (Player) commandSender;
        String name = strings[0];
        Player target = Bukkit.getPlayer(name);

        if (!p.hasPermission("wildcore.helper") || !p.hasPermission("wildcore.vedeni")) {
            p.sendMessage("§c§lW§6§lC §8§l» §cNeznámy príkaz");
            return true;
        }
        if (target == null) {
            p.sendMessage("§c§lW§6§lC §8§l» §cNeznámý hráč");
            return true;
        }





        p.sendMessage("");
        p.sendMessage("§7§lInformace o uživateli §a§l" + target.getName());
        p.sendMessage("");
        p.sendMessage("§aNick: §7" + target.getName());
        p.sendMessage("§aUUID: §7" + target.getUniqueId());
        try {
            p.sendMessage("§aGender: §7" + Utils.getGenderToString(target,genderTable));
            p.sendMessage("§aHlasy: §7" + voteTable.getPlayerVotes(target.getName()) + "§7x");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        p.sendMessage("§aRank: §7" + PlaceholderAPI.setPlaceholders(target, "%luckperms_primary_group_name%"));
        p.sendMessage("§aNahraný čas: §7" + PlaceholderAPI.setPlaceholders(target, "%cmi_user_playtime_hoursf%") + " §7hod.");
        p.sendMessage("§aFly: §7" + PlaceholderAPI.setPlaceholders(target, "%cmi_user_flightcharge%" + " §7bloků"));
        p.sendMessage("");
        return false;
    }




}
