package cz.wildcraft.wildcore.staffchat;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;

public class StaffChatCompleter implements TabCompleter {
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        Player player = (Player)sender;
        if (args.length == 1) {
            player.playSound(player.getLocation(), Sound.BLOCK_DISPENSER_FAIL, 80.0F, 1.0F);
            return (List<String>) StringUtil.copyPartialMatches(args[0], Arrays.asList(new String[]{"on", "off", "reload"}), new ArrayList());
        }
        return new ArrayList<>();
    }
}
