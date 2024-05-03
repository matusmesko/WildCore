package cz.wildcraft.wildcore.staffchat;

import cz.wildcraft.wildcore.WildCore;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.List;
public class StaffChatManager {
    private WildCore spigot;

    private List<String> staffChatList;

    private boolean symbolBol;

    private String symbol;

    private boolean soundBol;

    private String sound;



    public StaffChatManager() {
        this.spigot = WildCore.getPlugin();
        this.staffChatList = new ArrayList<>();
        loadData();
        loadSoundData();
    }

    public void loadData() {
        this.symbolBol = this.spigot.getConfig().getBoolean("staffchat.symbolBool");
        this.symbol = this.spigot.getConfig().getString("staffchat.symbol");
    }

    public void loadSoundData() {
        this.sound = this.spigot.getConfig().getString("staffchat.soundType");
        this.soundBol = this.spigot.getConfig().getBoolean("staffchat.soundBool");
    }

    public void sendAdminMessageBuilder(Player sender, String[] args) {
        StringBuilder messageBuilder = new StringBuilder();
        for (String arg : args) {
            String a = arg + " ";
            messageBuilder.append(a);
        }
        String message = messageBuilder.toString();
        for (Player players : Bukkit.getOnlinePlayers()) {
            if (players.hasPermission("staffChat.see")) {
                playSoundToPlayer(players);
                spigot.getMessages().sendListMessagesSpigot(players, sender, "staffchat.chat", message);
            }
        }
    }

    public void sendStaffMessage(Player sender, String message) {
        for (Player players : Bukkit.getOnlinePlayers()) {
            if (players.hasPermission("staffChat.see") || players.hasPermission("staffChat.admin")) {
                playSoundToPlayer(players);
                spigot.getMessages().sendListMessagesSpigot(players, sender, "staffchat.chat", message);
            }
        }
    }

    public void sendStaffMessageConsole(String message) {
        for (Player players : Bukkit.getOnlinePlayers()) {
            if (players.hasPermission("staffChat.see") || players.hasPermission("staffChat.admin"))
                playSoundToPlayer(players);
            spigot.getMessages().sendListMessagesConsole(players, "staffchat.chat", message);
        }
    }

    public void sendStaffMessageSender(String sender, String message) {
        for (Player players : Bukkit.getOnlinePlayers()) {
            if (players.hasPermission("staffChat.see") || players.hasPermission("staffChat.admin"))
                playSoundToPlayer(players);
            spigot.getMessages().sendListMessagesSender(players, sender, "staffchat.chat", message);
        }
    }

    public void playSoundToPlayer(Player player) {
        if (!isSoundBol())
            return;
        Sound sound = Sound.valueOf(getSound());
        if (sound == null)
            return;
        player.playSound(player.getLocation(), sound, 1.0F, 1.0F);
    }

    public List<String> getStaffChatList() {
        return this.staffChatList;
    }

    public void setStaffChatList(List<String> staffChatList) {
        this.staffChatList = staffChatList;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public boolean isSymbolBol() {
        return this.symbolBol;
    }

    public void setSymbolBol(boolean symbolBol) {
        this.symbolBol = symbolBol;
    }

    public boolean isSoundBol() {
        return this.soundBol;
    }

    public String getSound() {
        return this.sound;
    }
}
