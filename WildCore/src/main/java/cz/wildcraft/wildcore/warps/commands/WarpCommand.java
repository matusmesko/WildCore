package cz.wildcraft.wildcore.warps.commands;

import cz.wildcraft.wildcore.WildCore;
import cz.wildcraft.wildcore.warps.database.PlayerWarpTable;
import cz.wildcraft.wildcore.warps.database.ServerWarpTable;
import cz.wildcraft.wildcore.warps.model.PlayerWarpModel;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

public class WarpCommand implements CommandExecutor {

    private PlayerWarpTable database = new PlayerWarpTable();
    private ServerWarpTable serverDatabase = new ServerWarpTable();
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player p = (Player) commandSender;
        if (args.length == 0) {
            p.sendMessage("§c§lW§6§lC §8§l» §cMusíš zadat jméno warpu");
        }else if (args.length == 1) {
            String wName = args[0];
            try {
                for (PlayerWarpModel warp : database.getAllPlayerWarps()) {
                    if (warp.getWarp_name().equals(wName)) {
                        World world = Bukkit.getWorld(warp.getWorld());
                        Location location = new Location(world, warp.getX(), warp.getY(), warp.getZ(), warp.getYaw(), warp.getPitch());
                        p.teleport(location);
                        p.sendMessage("§c§lW§6§lC §8§l» §aByl jsi teleportován na warp §c" + warp.getWarp_name());
                        warp.setVisited(warp.getVisited() + 1);
                        database.updateWarp(warp);
                        return true;
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            p.sendMessage("§c§lW§6§lC §8§l» §cTenhle warp neexistuje");
            return true;
        }
        return false;
    }
}
