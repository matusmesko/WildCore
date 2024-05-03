package cz.wildcraft.wildcore.placeholders;

import cz.wildcraft.wildcore.WildCore;
import cz.wildcraft.wildcore.utils.Utils;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

public class WildCorePlaceholder extends PlaceholderExpansion {

    private final WildCore plugin;

    public WildCorePlaceholder(WildCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "wildcore";
    }

    @Override
    public @NotNull String getAuthor() {
        return "WildCraft";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {

        if(params.equalsIgnoreCase("gender")) {
            try {
                return Utils.getGenderTag((Player) player, plugin.getGenderTable());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else if (params.equalsIgnoreCase("staffchat")) {
            if (plugin.getStaffChatManager().getStaffChatList().contains(player.getName())) {
                return "§cATEAM";
            }else {
                return "§aGLOBAL";
            }
        }else if (params.equalsIgnoreCase("chatVotes")) {
            try {
                String gender = Utils.getGenderToString((Player) player, plugin.getGenderTable());
                if (gender.equals("male")) {
                    return "§7Hlasoval: §f" + plugin.getVoteTable().getPlayerVotes(player.getName() + "x");
                }else {
                    return "§7Hlasovala: §f" + plugin.getVoteTable().getPlayerVotes(player.getName() + "x");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else if (params.equalsIgnoreCase("playerVotes")) {
            try {
                return "" + plugin.getVoteTable().getPlayerVotes(player.getName());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


        return null; // Placeholder is unknown by the Expansion
    }
}
