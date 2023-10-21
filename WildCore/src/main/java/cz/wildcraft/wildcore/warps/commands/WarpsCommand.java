package cz.wildcraft.wildcore.warps.commands;

import cz.wildcraft.wildcore.WildCore;
import cz.wildcraft.wildcore.warps.database.ServerWarpTable;
import cz.wildcraft.wildcore.warps.menus.ServerWarpMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.text.ParseException;

public class WarpsCommand implements CommandExecutor {

    private final ServerWarpTable database;

    public WarpsCommand(ServerWarpTable database) {
        this.database = database;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        try {
            new ServerWarpMenu(WildCore.getPlayerMenuUtility(p)).open();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return false;
    }
}
