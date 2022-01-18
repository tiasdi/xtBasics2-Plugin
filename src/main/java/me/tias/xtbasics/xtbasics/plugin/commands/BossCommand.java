package me.tias.xtbasics.xtbasics.plugin.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import me.tias.xtbasics.xtbasics.config.PlayerData;
import me.tias.xtbasics.xtbasics.plugin.players.User;
import me.tias.xtbasics.xtbasics.plugin.players.UserManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("boss|summon")
public class BossCommand extends BaseCommand {

    public void onBossCommand(CommandSender sender) {
        if (sender instanceof Player player) {
            player.sendMessage("§a  ");
            player.sendMessage("§8[§a!§8] §cBosses Coming Soon");
            player.sendMessage("§a  ");
        }
    }


}
