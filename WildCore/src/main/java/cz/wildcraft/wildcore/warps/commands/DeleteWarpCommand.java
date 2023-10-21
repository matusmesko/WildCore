package cz.wildcraft.wildcore.warps.commands;

import cz.wildcraft.wildcore.WildCore;
import cz.wildcraft.wildcore.warps.database.PlayerWarpTable;
import cz.wildcraft.wildcore.warps.menus.DeleteWarpConfirmMenu;
import cz.wildcraft.wildcore.warps.model.PlayerWarpModel;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class DeleteWarpCommand implements CommandExecutor {

    private PlayerWarpTable database = new PlayerWarpTable();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args)  {
        Player p = (Player) commandSender;
        if (args.length == 0) {
            p.sendMessage(WildCore.getPlugin().getConfig().getString("playerWarpDeleteWrongUsage"));
        }else if (args.length == 1) {
            String wName = args[0];
            try {
                PlayerWarpModel warp = database.findPlayerWarpByName(wName);
                if (warp == null) {
                    p.sendMessage(WildCore.getPlugin().getConfig().getString("deleteWarp.warpNotFound"));
                    return true;
                }
                if (!p.getName().equals(warp.getOwner())) {
                    p.sendMessage(WildCore.getPlugin().getConfig().getString("deleteWarp.warpNotOwning"));
                    return true;
                }

            new DeleteWarpConfirmMenu(WildCore.getPlayerMenuUtility(p), warp).open();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        }
        return false;
    }
}
