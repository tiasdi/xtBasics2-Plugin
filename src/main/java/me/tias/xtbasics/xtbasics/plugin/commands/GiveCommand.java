package me.tias.xtbasics.xtbasics.plugin.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("give")
public class GiveCommand extends BaseCommand {

    @Default
    public void onGiveCommand(CommandSender sender) {
        if (sender instanceof Player player) {
            player.sendMessage("§a  ");
            player.sendMessage("§8[§a!§8] §ctias does not want you using this, clown.");
            player.sendMessage("§a  ");
        }
    }

}
