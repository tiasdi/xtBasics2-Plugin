package me.tias.xtbasics.xtbasics.plugin.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import com.destroystokyo.paper.utils.PaperPluginLogger;
import me.tias.xtbasics.xtbasics.plugin.players.User;
import me.tias.xtbasics.xtbasics.plugin.players.UserManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Locale;

@CommandAlias("fix|repair")
public class FixCommand extends BaseCommand {

    @Default
    public void fixItemCommand(CommandSender sender) {
        if (sender instanceof Player player) {
            User user = UserManager.getSelected(player.getUniqueId());
            try {
                ItemStack itemStack = player.getInventory().getItemInMainHand();
                String str = itemStack.getType().toString().toUpperCase(Locale.ROOT);
                if (str.contains("SWORD") || str.contains("AXE") || str.contains("HOE") || str.contains("SHOVEL") || str.contains("BOW") || str.contains("CHESTPLATE") || str.contains("LEGGING") || str.contains("PANTS") || str.contains("HELMET") || str.contains("BOOTS")) {
                    if (str.contains("DIAMOND") || str.contains("NETHERITE")) {
                        if (user.getMoney() > 11.99) {
                            player.getItemInHand().setDurability((short) 0);
                            user.removeMoney(12.00);
                            player.sendMessage("§a  ");
                            player.sendMessage("§8[§b!§8] §dYour rare item has been fixed!");
                            player.sendMessage("§a  ");
                        } else {
                            player.sendMessage("§a  ");
                            player.sendMessage("§8[§b!§8] §4That item is rare! §cYou need $12.00 to fix it.");
                            player.sendMessage("§a  ");
                        }
                    } else {
                        if (user.getMoney() > 3.99) {
                            player.getItemInHand().setDurability((short) 0);
                            user.removeMoney(4.00);
                            player.sendMessage("§a  ");
                            player.sendMessage("§8[§b!§8] §dYour item has been fixed! §7[-$4.00]");
                            player.sendMessage("§a  ");
                        } else {
                            player.sendMessage("§a  ");
                            player.sendMessage("§8[§b!§8] §cYou need $4.00 to fix that item.");
                            player.sendMessage("§a  ");
                        }
                    }
                } else {
                    player.sendMessage("§a  ");
                    player.sendMessage("§8[§b!§8] §cYou cannot fix this item.");
                    player.sendMessage("§a  ");
                }
            } catch (Exception exception) {
                player.sendMessage("§a  ");
                player.sendMessage("§8[§b!§8] §cYou cannot fix this item.");
                player.sendMessage("§a  ");
            }
        }
    }
}
