package me.tias.xtbasics.xtbasics.plugin.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import com.destroystokyo.paper.utils.PaperPluginLogger;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("jetpack|jp")
public class JetPackCommand extends BaseCommand {

    @Default
    public void onJetPackCommand(CommandSender sender) {
        if (sender instanceof Player player) {
            player.sendMessage("§a  ");
            player.sendMessage("§8[§a!§8] §cComing Soon");
            player.sendMessage("§a  ");
        }
    }


}
