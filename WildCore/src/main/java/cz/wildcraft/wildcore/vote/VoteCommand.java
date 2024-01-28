package cz.wildcraft.wildcore.vote;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class VoteCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player p = (Player) commandSender;
        p.sendMessage("");
        p.sendMessage("§a| §7Hlasuj pro server a získej skvělé odměny");
        p.sendMessage("");
        p.sendMessage("§a| https://craftlist.org/wildcraft-llgxx?nickname=" + p.getName());
        p.sendMessage("§a| https://minecraftservery.eu/server/1503/vote/" + p.getName());
        p.sendMessage("§a| https://creeperlist.eu/vote/70/" + p.getName() + "/");
        p.sendMessage("");
        return false;
    }
}
