package cz.wildcraft.wildcore.commands;


import me.realized.tokenmanager.api.TokenManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.OptionalLong;

public class KreditCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player p = (Player) commandSender;
        TokenManager tokenManager = (TokenManager)Bukkit.getPluginManager().getPlugin("TokenManager");
        final long balance = tokenManager.getTokens(p).orElse(0);
        if (strings.length == 0) {
            p.sendMessage("§c§lW§6§lC §8§l» §7Právě máš §c" + balance + " §7kreditů.");
        }else if (strings[0].equalsIgnoreCase("send")) {
            if (strings.length != 3) {
                p.sendMessage("§c§lW§6§lC §8§l» §cNeznámý príkaz. Použij /kredity send <jméno> <částka>");
                return true;
            }

            String name = strings[1];
            String amount = strings[2];
            Player target = Bukkit.getPlayer(name);
            String regex = "[0-9]+";
            if (!amount.matches(regex)) {
                p.sendMessage("§c§lW§6§lC §8§l» §cMusíš zadat platnou částku");
                return true;
            }
            long amountt = Long.parseLong(amount);

            if (target == null) {
                p.sendMessage("§c§lW§6§lC §8§l» §cNeznámý hráč");
                return true;
            }
            if (amount.isEmpty()) {
                p.sendMessage("§c§lW§6§lC §8§l» §cMusíš zadat platnou částku");
                return true;
            }

            if (amountt == 0) {
                p.sendMessage("§c§lW§6§lC §8§l» §cMusíš zadat platnou částku");
                return true;
            }

            if (balance < amountt) {
                p.sendMessage("§c§lW§6§lC §8§l» §cNemáš dostatek kreditů");
                return true;
            }

            if (p == target) {
                p.sendMessage("§c§lW§6§lC §8§l» §cNemůžeš posílat kredity sám sobě");
                return true;
            }
            tokenManager.removeTokens(p, amountt);
            tokenManager.addTokens(target, amountt);

            p.sendMessage("§c§lW§6§lC §8§l» §7Odeslal jsi §c" + amountt + " §7kreditů hráči §c" + target.getName());
            target.sendMessage("§c§lW§6§lC §8§l» §7Dostal jsi §c" + amountt + " §7kreditů od hráče §c" + p.getName());

        }
        return false;
    }
}
