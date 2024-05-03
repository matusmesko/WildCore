package cz.wildcraft.wildcore.task;

import cz.wildcraft.wildcore.WildCore;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Random;

public class InfoMessages extends BukkitRunnable {
    @Override
    public void run() {
        List<String> messages = (List<String>) WildCore.getPlugin().getConfig().getList("taskMessages");
        Random randomizer = new Random();
        String random = messages.get(randomizer.nextInt(messages.size()));
        Bukkit.broadcastMessage(random);
    }
}
