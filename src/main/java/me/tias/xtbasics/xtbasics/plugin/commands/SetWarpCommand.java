package me.tias.xtbasics.xtbasics.plugin.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import com.destroystokyo.paper.utils.PaperPluginLogger;
import me.tias.xtbasics.xtbasics.XtBasics;
import me.tias.xtbasics.xtbasics.config.Warps;
import me.tias.xtbasics.xtbasics.plugin.players.User;
import me.tias.xtbasics.xtbasics.plugin.players.UserManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("setwarp")
public class SetWarpCommand extends BaseCommand {

    @Default
    public void createWarp(CommandSender sender, String[] args) {
        if (sender instanceof Player player) {
            User user = UserManager.getSelected(player.getUniqueId());
            if (user.getMoney() > 19.99) {
                if (args.length == 1) {
                    String name = args[0];
                    if (Warps.get().isConfigurationSection(name)) {
                        player.sendMessage("§a  ");
                        player.sendMessage("§8[§9!§8] §cA warp with that name already exists!");
                        player.sendMessage("§a  ");
                    } else {

                        player.sendMessage("§a  ");
                        player.sendMessage("§8[§9!§8] §dCreated Warp: §5"+name+"§d! §7[-$20.00]");
                        player.sendMessage("§a  ");

                        Warps.get().set(name + ".Creator", player.getUniqueId().toString());
                        Warps.get().set(name + ".Location", player.getLocation());
                        Warps.get().set(name + ".Uses", 0);
                        user.removeMoney(20.00);
                        Warps.save();
                        XtBasics.addWarp(name);
                    }
                } else {
                    player.sendMessage("§a  ");
                    player.sendMessage("§8[§9!§8] §c/setwarp <name>");
                    player.sendMessage("§a  ");
                }
            } else {
                player.sendMessage("§a  ");
                player.sendMessage("§8[§9!§8] §cIt costs $20.00 to create a warp.");
                player.sendMessage("§a  ");
            }
        }
    }

}
