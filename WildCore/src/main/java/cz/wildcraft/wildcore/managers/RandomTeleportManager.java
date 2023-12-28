package cz.wildcraft.wildcore.managers;

import cz.wildcraft.wildcore.WildCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.ThreadLocalRandom;

public class RandomTeleportManager {

    int radiusX = 2000;
    int radiusZ = 2000;

    public  void randomTeleportPlayer (Player player) {
        Location location = getRandomLocation(player);
        player.teleport(location);

    }
    private  Location getRandomLocation (Player player) {
        World world = Bukkit.getWorld("world");
        int randomX = ThreadLocalRandom.current().nextInt(-radiusX,radiusX);
        int randomZ = ThreadLocalRandom.current().nextInt(-radiusZ,radiusZ);
        int randomY = world.getHighestBlockYAt(randomX, randomZ);
        return new Location(world, randomX,randomY,randomZ);
    }
}
