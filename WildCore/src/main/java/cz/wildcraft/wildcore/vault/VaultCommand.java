package cz.wildcraft.wildcore.vault;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class VaultCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player){

            Player p = (Player) commandSender;
            ArrayList<ItemStack> vaultItems = VaultUtils.getItems(p);

            if (p.hasPermission("wildcore.legendary")) {
                Inventory vault = Bukkit.createInventory(p, 27, "§lTrezor");

                vaultItems.stream()
                        .forEach(itemStack -> vault.addItem(itemStack));

                p.openInventory(vault);
            }else if (p.hasPermission("wildcore.ultimate")) {

                Inventory vault = Bukkit.createInventory(p, 54, "§lTrezor");

                vaultItems.stream()
                        .forEach(itemStack -> vault.addItem(itemStack));

                p.openInventory(vault);
            }else {
                p.sendMessage("§c§lW§6§lC §8§l» §cNemáš oprávnění");
                return true;
            }

        }


        return true;
    }
}
