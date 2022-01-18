package me.tias.xtbasics.xtbasics.plugin.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import me.tias.xtbasics.xtbasics.plugin.players.User;
import me.tias.xtbasics.xtbasics.plugin.players.UserManager;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@CommandAlias("sell")
public class SellCommand extends BaseCommand {

    @Default
    public void onSellCommand(CommandSender sender) {
        if (sender instanceof Player player) {
            User user = UserManager.getSelected(player.getUniqueId());
            ItemStack item = player.getInventory().getItemInMainHand();
            int amount = item.getAmount();
            if (item.getType().equals(Material.IRON_INGOT)) {
                player.sendMessage("§a  ");
                if (amount > 1) {
                    player.sendMessage("§8[§a!§8] §eYou have sold §7§l" + amount + " Iron Ingots§r§e! §d[+$"+amount*0.10+"]");
                } else {
                    player.sendMessage("§8[§a!§8] §eYou have sold §7§l" + amount + " Iron Ingot§r§e! §d[+$0.10]");
                }
                player.getInventory().clear(player.getInventory().getHeldItemSlot());
                user.addMoney(amount*0.10);
                player.sendMessage("§a  ");
            } else if (item.getType().equals(Material.DIAMOND)) {
                player.sendMessage("§a  ");
                if (amount > 1) {
                    player.sendMessage("§8[§a!§8] §eYou have sold §b§l" + amount + " Diamonds§r§e! §d[+$"+amount*0.50+"]");
                } else {
                    player.sendMessage("§8[§a!§8] §eYou have sold §b§l" + amount + " Diamond§r§e! §d[+$0.50]");
                }
                player.getInventory().clear(player.getInventory().getHeldItemSlot());
                user.addMoney(amount*0.50);
                player.sendMessage("§a  ");
            } else if (item.getType().equals(Material.NETHERITE_INGOT)) {
                player.sendMessage("§a  ");
                if (amount > 1) {
                    player.sendMessage("§8[§a!§8] §eYou have sold §4§l" + amount + " Netherite§r§e! §d[+$"+amount*10.00+"]");
                } else {
                    player.sendMessage("§8[§a!§8] §eYou have sold §4§l" + amount + " Netherite§r§e! §d[+$10.00]");
                }
                player.getInventory().clear(player.getInventory().getHeldItemSlot());
                user.addMoney(amount*10.00);
                player.sendMessage("§a  ");
            } else if (item.getType().equals(Material.GOLD_INGOT)) {
                player.sendMessage("§a  ");
                if (amount > 1) {
                    player.sendMessage("§8[§a!§8] §eYou have sold §e§l" + amount + " Gold§r§e! §d[+$"+amount*0.05+"]");
                } else {
                    player.sendMessage("§8[§a!§8] §eYou have sold §e§l" + amount + " Gold§r§e! §d[+$0.05]");
                }
                player.getInventory().clear(player.getInventory().getHeldItemSlot());
                user.addMoney(amount*0.05);
                player.sendMessage("§a  ");
            } else {
                player.sendMessage("§a  ");
                player.sendMessage("§8[§a!§8] §cYou cannot sell the item that you are holding!");
                player.sendMessage("§a  ");
            }
        }
    }

}
