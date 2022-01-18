package me.tias.xtbasics.xtbasics.plugin.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import me.tias.xtbasics.xtbasics.config.PlayerData;
import me.tias.xtbasics.xtbasics.plugin.players.User;
import me.tias.xtbasics.xtbasics.plugin.players.UserManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("bal|balance")
public class BalanceCommand extends BaseCommand {

    @Default
    public void onBalanceCommand(CommandSender sender) {
        if (sender instanceof Player player) {
            player.sendMessage("§a  ");
            player.sendMessage("§8[§6!§8] §aYour current balance is §2$"+ ((double) Math.round((PlayerData.get().getDouble(player.getUniqueId()+".Data.Money")) * 100) / 100)+"§a!");
            player.sendMessage("§a  ");
        }
    }


}
