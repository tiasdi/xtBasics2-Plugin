package me.tias.xtbasics.xtbasics.plugin.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import me.tias.xtbasics.xtbasics.XtBasics;
import me.tias.xtbasics.xtbasics.config.PlayerData;
import me.tias.xtbasics.xtbasics.plugin.players.User;
import me.tias.xtbasics.xtbasics.plugin.players.UserManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("sethome")
public class SetHomeCommand extends BaseCommand {

    @Default
    public void onSetHomeCommand(CommandSender sender) {
        if (sender instanceof Player player) {
            User user = UserManager.getSelected(player.getUniqueId());
            if (user.getMoney() > 0.99) {
                if (player.getWorld().toString().contains("end") || player.getWorld().toString().contains("nether")) {
                    player.sendMessage("§a  ");
                    player.sendMessage("§8[§6!§8] §cERROR: You cannot set your home in the End or Nether");
                    player.sendMessage("§a  ");
                } else {
                    PlayerData.get().set(player.getUniqueId() + ".Home.X", player.getLocation().getX());
                    PlayerData.get().set(player.getUniqueId() + ".Home.Y", player.getLocation().getY()+0.6);
                    PlayerData.get().set(player.getUniqueId() + ".Home.Z", player.getLocation().getZ());
                    PlayerData.save();
                    player.sendMessage("§a  ");
                    player.sendMessage("§8[§6!§8] §6Setting your new Home... §d[-$1.00]");
                    player.sendMessage("§a  ");
                    user.removeMoney(1.00);
                }
            } else {
                player.sendMessage("§a  ");
                player.sendMessage("§8[§6!§8] §cERROR: You need $1.00 to set your home!");
                player.sendMessage("§a  ");
            }
        }
    }

}