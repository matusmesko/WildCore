package cz.wildcraft.wildcore.warps.commands;

import cz.wildcraft.wildcore.WildCore;
import cz.wildcraft.wildcore.warps.menus.WarpConfirmMenu;
import cz.wildcraft.wildcore.warps.model.PlayerWarpModel;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SetPlayerWarpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player p = (Player) commandSender;
        if (args.length == 0) {
            p.sendMessage(WildCore.getPlugin().getConfig().getString("playerWarpWrongUsage"));
        } else if (args.length == 1) {
            String wName = args[0];
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
