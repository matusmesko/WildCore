package cz.wildcraft.wildcore.utils;

import cz.wildcraft.wildcore.gender.GenderModel;
import cz.wildcraft.wildcore.gender.GenderTable;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class Utils {



    public static String getGenderTag(Player player, GenderTable genderTable) throws SQLException {
        GenderModel gender = genderTable.findPlayerByName(player.getName());
        String format = "";
        if (gender.getGender().equalsIgnoreCase("male")) {
            format = "§8[§b♂§8]§r";
        }else {
            format = "§8[§d♀§8]§r";
        }
        return format;
    }

}
