package cz.wildcraft.wildcore.discord;
import cz.wildcraft.wildcore.WildCore;
import litebans.api.Database;
import litebans.api.Entry;
import litebans.api.Events;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

public class LiteBansPunismentLog {

    private static TextChannel channel = WildCore.getPlugin().punishmentLogChannel();
    public static void registerEvents() {


        Events.get().register(new Events.Listener() {
            @Override
            public void entryAdded(Entry entry) {
                switch (entry.getType()) {
                    case "ban":
                        // This is a ban event.
                        channel.sendMessageEmbeds(embedWithCountLog("Ban Systém","Byl udělen ban",getPlayerName(entry.getUuid()),entry.getExecutorName(), entry.getReason(), entry.getDurationString(), getPlayersBanCounts(entry.getUuid())).build()).queue();
                        break;
                    case "mute":
                        // This is a mute event.
                        channel.sendMessageEmbeds(embedLog("Mute Systém","Byl udělen mute",getPlayerName(entry.getUuid()),entry.getExecutorName(), entry.getReason(), entry.getDurationString()).build()).queue();
                        break;
                    case "warn":
                        // This is a warn event.
                        break;
                    case "kick":
                        // This is a kick event.
                        break;
                }
            }
            @Override
            public void entryRemoved(Entry entry) {
                switch (entry.getType()) {
                    case "ban":
                        // This is an unban event.
                        channel.sendMessageEmbeds(embedUnLog("Ban Systém","Byl udělen unban",getPlayerName(entry.getUuid()),entry.getExecutorName(), entry.getReason()).build()).queue();
                        break;
                    case "mute":
                        // This is an unmute event.
                        channel.sendMessageEmbeds(embedUnLog("Mute Systém","Byl udělen unmute",getPlayerName(entry.getUuid()),entry.getExecutorName(), entry.getReason()).build()).queue();
                        break;
                    case "warn":
                        // This is an unwarn event.
                        break;
                }
            }
        });
    }

    private static EmbedBuilder embedLog(String title, String description, String playername, String executor, String reason, String duration) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(Color.RED);
        embedBuilder.setTitle(title);
        embedBuilder.setThumbnail("https://minotar.net/avatar/" + playername);
        embedBuilder.setDescription(description);
        embedBuilder.addField("Administrátor", executor, true);
        embedBuilder.addField("Důvod", reason, true);
        embedBuilder.addField("", "", false);
        embedBuilder.addField("Hráč", playername, true);
        embedBuilder.addField("Doba trestu", duration, true);

        embedBuilder.setTimestamp(Instant.now());

        return embedBuilder;
    }
    private static EmbedBuilder embedUnLog(String title, String description, String playername, String executor, String reason) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(Color.GREEN);
        embedBuilder.setTitle(title);
        embedBuilder.setThumbnail("https://minotar.net/avatar/" + playername);
        embedBuilder.setDescription(description);
        embedBuilder.addField("Administrátor", executor, true);
        embedBuilder.addField("Důvod", reason, true);
        embedBuilder.addField("", "", false);
        embedBuilder.addField("Hráč", playername, true);

        embedBuilder.setTimestamp(Instant.now());

        return embedBuilder;
    }

    private static EmbedBuilder embedWithCountLog(String title, String description, String playername, String executor, String reason, String duration, long count) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(Color.RED);
        embedBuilder.setTitle(title);
        embedBuilder.setThumbnail("https://minotar.net/avatar/" + playername);
        embedBuilder.setDescription(description);
        embedBuilder.addField("Administrátor", executor, true);
        embedBuilder.addField("Důvod", reason, true);
        embedBuilder.addField("", "", false);
        embedBuilder.addField("Hráč", playername, true);
        embedBuilder.addField("Doba trestu", duration, true);
        embedBuilder.addField("Počet trestů", String.valueOf(count), false);

        embedBuilder.setTimestamp(Instant.now());

        return embedBuilder;
    }

    private static String getPlayerName(String uuid) {
        try {
            PreparedStatement stmt = Database.get()
                    .prepareStatement("SELECT name FROM {history} WHERE uuid = ? ORDER BY id DESC LIMIT 1");
            try {
                stmt.setString(1, uuid);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    String str = rs.getString(1);
                    if (stmt != null)
                        stmt.close();
                    return str;
                }
                if (stmt != null)
                    stmt.close();
            } catch (Throwable throwable) {
                if (stmt != null)
                    try {
                        stmt.close();
                    } catch (Throwable throwable1) {
                        throwable.addSuppressed(throwable1);
                    }
                throw throwable;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static long getPlayersBanCounts(String uuid) {
        String query = "SELECT COUNT(*) FROM {bans} WHERE uuid=?";
        try (PreparedStatement st = Database.get().prepareStatement(query)) {
            st.setString(1, uuid);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                   return rs.getLong(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}

