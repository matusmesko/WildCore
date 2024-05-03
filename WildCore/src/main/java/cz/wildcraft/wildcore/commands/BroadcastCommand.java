package cz.wildcraft.wildcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BroadcastCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player p = (Player) commandSender;
        if (!p.hasPermission("wildcore.vedeni")) {
            p.sendMessage("§c§lW§6§lC §8§l» §cNa tohle nemáš oprávnění");
            return true;
        }

        String msg = String.join(" ", (CharSequence[])args);
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("§c§lOZNÁMENÍ");
        Bukkit.broadcastMessage(msg);
        Bukkit.broadcastMessage("");

        return false;
    }
}
