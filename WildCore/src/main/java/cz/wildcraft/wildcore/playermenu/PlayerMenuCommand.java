package cz.wildcraft.wildcore.playermenu;

import cz.wildcraft.wildcore.WildCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

public class PlayerMenuCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player p = (Player) commandSender;
        try {
            new PlayerMenu(WildCore.getPlayerMenuUtility(p)).open();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
