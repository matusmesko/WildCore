package cz.wildcraft.wildcore.commands;

import cz.wildcraft.wildcore.WildCore;
import cz.wildcraft.wildcore.managers.RandomTeleportManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class PrirodaCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player p = (Player) commandSender;
        RandomTeleportManager randomTeleportManager = new RandomTeleportManager();
        randomTeleportManager.randomTeleportPlayer(p);
        p.sendMessage(WildCore.getPlugin().getConfig().getString("priroda.message"));
        return true;
    }


}
