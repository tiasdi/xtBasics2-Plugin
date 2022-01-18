package me.tias.xtbasics.xtbasics.plugin.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import com.destroystokyo.paper.utils.PaperPluginLogger;
import me.tias.xtbasics.xtbasics.XtBasics;
import me.tias.xtbasics.xtbasics.config.PlayerData;
import me.tias.xtbasics.xtbasics.plugin.players.User;
import me.tias.xtbasics.xtbasics.plugin.players.UserManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

@CommandAlias("home")
public class HomeCommand extends BaseCommand {

    @Default
    public void onHomeCommand(CommandSender sender) {
        if (sender instanceof Player player) {
            User user = UserManager.getSelected(player.getUniqueId());
            if (user.getMoney() > 0.14) {
                if (PlayerData.get().getInt(player.getUniqueId()+".Home.Y") > -250) {
                    Location location = new Location(Bukkit.getWorld("world"),PlayerData.get().getInt(player.getUniqueId()+".Home.X"),PlayerData.get().getInt(player.getUniqueId()+".Home.Y"),PlayerData.get().getInt(player.getUniqueId()+".Home.Z"));
                    player.sendMessage("§a  ");
                    player.sendMessage("§8[§6!§8] §6Teleporting Home... §d[-$0.15]");
                    player.sendMessage("§a  ");
                    Bukkit.getScheduler().runTaskLater(XtBasics.getInstance(), () -> {
                        if (!player.isDead()) {
                            player.teleport(location);
                            user.removeMoney(0.15);
                        }
                    }, 43);

                } else {
                    player.sendMessage("§a  ");
                    player.sendMessage("§8[§6!§8] §cERROR: You need to set your home first with /sethome!");
                    player.sendMessage("§a  ");
                }
            } else {
                player.sendMessage("§a  ");
                player.sendMessage("§8[§6!§8] §cERROR: You need $0.15 to teleport home!");
                player.sendMessage("§a  ");
            }
        }
    }

}
