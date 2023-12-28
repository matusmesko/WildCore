package cz.wildcraft.wildcore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DiscordCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player p = (Player) commandSender;
        p.sendMessage(" ");
        p.sendMessage("§9| §7Připoj se na náš discord server");
        p.sendMessage("§9| §9discord.gg/qRRbBXeq8T");
        p.sendMessage(" ");
        return false;
    }
}
