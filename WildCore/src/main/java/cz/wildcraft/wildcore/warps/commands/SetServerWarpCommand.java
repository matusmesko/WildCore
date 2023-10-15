package cz.wildcraft.wildcore.warps.commands;

import cz.wildcraft.wildcore.WildCore;
import cz.wildcraft.wildcore.warps.database.ServerWarpTable;
import cz.wildcraft.wildcore.warps.model.ServerWarpModel;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Date;

public class SetServerWarpCommand implements CommandExecutor {
    private final ServerWarpTable database;

    public SetServerWarpCommand(ServerWarpTable database) {
        this.database = database;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player p = (Player) commandSender;
        if (p.hasPermission("wildcore.vedenie")) {
            if (args.length == 0) {
                p.sendMessage(WildCore.getPlugin().getConfig().getString("serverWarpWrongUsage"));
            } else if (args.length == 1) {
                String warpName = args[0];
                String description = "Warp";
                String icon = "CHEST";
                String world =  p.getLocation().getWorld().getName();
                double x = p.getLocation().getX();
                double y = p.getLocation().getY();
                double z = p.getLocation().getZ();
                float yaw = p.getLocation().getYaw();
                float pitch = p.getLocation().getPitch();

                ServerWarpModel warp = new ServerWarpModel(warpName, description, icon, world, x, y, z, new Date(), yaw, pitch);
                database.createWarp(warp);
            }
        }else {
            p.sendMessage(WildCore.getPlugin().getConfig().getString("no-perms"));
        }
        return false;
    }
}
