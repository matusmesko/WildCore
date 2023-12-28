package cz.wildcraft.wildcore.warps.commands;

import cz.wildcraft.wildcore.WildCore;
import cz.wildcraft.wildcore.warps.database.PlayerWarpTable;
import cz.wildcraft.wildcore.warps.database.ServerWarpTable;
import cz.wildcraft.wildcore.warps.menus.WarpConfirmMenu;
import cz.wildcraft.wildcore.warps.model.PlayerWarpModel;
import cz.wildcraft.wildcore.warps.model.ServerWarpModel;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SetPlayerWarpCommand implements CommandExecutor {

    private PlayerWarpTable database = new PlayerWarpTable();
    private ServerWarpTable serverDatabase = new ServerWarpTable();
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player p = (Player) commandSender;
        if (args.length == 0) {
            p.sendMessage(WildCore.getPlugin().getConfig().getString("playerWarpWrongUsage"));
        } else if (args.length == 1) {
            String wName = args[0];
            try {
                for (PlayerWarpModel warp : database.getAllPlayerWarps()) {
                    if (warp.getWarp_name().equals(wName)) {
                        p.sendMessage(WildCore.getPlugin().getConfig().getString("warpAlreadyExist"));
                        return true;
                    }
                }

                for (ServerWarpModel warp : serverDatabase.getAllServerWarps()) {
                    if (warp.getWarp_name().equals(wName)) {
                        p.sendMessage(WildCore.getPlugin().getConfig().getString("warpAlreadyExist"));
                        return true;
                    }
                }


            }catch (SQLException ex) {

            }

            try {
                int count = database.getPlayerCountOfWarps(p.getName());
                if (count == 1 && !p.hasPermission("wildcore.legendary")) {
                    p.sendMessage(WildCore.getPlugin().getConfig().getString("warpMaxCount"));
                    return true;
                }else if (count == 3 && !p.hasPermission("wildcore.ultimate")) {
                    p.sendMessage(WildCore.getPlugin().getConfig().getString("warpMaxCount"));
                    return true;
                }else if (count == 5) {
                    p.sendMessage(WildCore.getPlugin().getConfig().getString("warpMaxCount"));
                    return true;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            String description = "none";
            String icon = "CHEST";
            String owner = p.getName();
            String world =  p.getLocation().getWorld().getName();
            int visited = 0;
            double x = p.getLocation().getX();
            double y = p.getLocation().getY();
            double z = p.getLocation().getZ();
            float yaw = p.getLocation().getYaw();
            float pitch = p.getLocation().getPitch();
            DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            String data = df.format(new Date());

            PlayerWarpModel warp = new PlayerWarpModel(wName, description, owner, visited,world, icon, data, x,y,z, yaw, pitch);

            try {
                new WarpConfirmMenu(WildCore.getPlayerMenuUtility(p), warp).open();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return false;
    }
}
