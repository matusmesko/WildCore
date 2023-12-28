package cz.wildcraft.wildcore;

import cz.wildcraft.wildcore.utils.ColorAPI;
import cz.wildcraft.wildcore.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WildCoreCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player p = (Player) commandSender;
        if (!p.hasPermission("wildcore.vedenie")) {
            p.sendMessage(WildCore.getPlugin().getConfig().getString("no-perms"));
            return true;
        }

        if (args.length == 0) {
            p.sendMessage(WildCore.getPlugin().getConfig().getString("wildcoreCommandWrongUsage"));
        }else if (args.length == 1) {
            if  (args[0].equalsIgnoreCase("reload")) {
                WildCore.getPlugin().reloadConfig();
                p.sendMessage(ColorAPI.colorize(WildCore.getPlugin().getConfig().getString("configReloaded")));
            }
        }
        return false;
    }
}
