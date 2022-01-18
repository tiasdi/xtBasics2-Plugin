package me.tias.xtbasics.xtbasics.plugin.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import com.destroystokyo.paper.utils.PaperPluginLogger;
import me.tias.xtbasics.xtbasics.plugin.players.User;
import me.tias.xtbasics.xtbasics.plugin.players.UserManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

@CommandAlias("echest|enderchest")
public class EChestCommand extends BaseCommand {

    @Default
    public void onEChestCommand(CommandSender sender) {
        if (sender instanceof Player player) {
            User user = UserManager.getSelected(player.getUniqueId());
            if (player.getScoreboardTags().contains("can47OpEn3EndER4Chest48569024")) {
                player.openInventory(player.getEnderChest());
            } else {

                if (user.getMoney() > 19.99) {
                    player.sendMessage("§a  ");
                    player.sendMessage("§8[§d!§8] §aYou can now use §d/enderchest§a. §7[-$20.00]");
                    player.sendMessage("§a  ");
                    player.getScoreboardTags().add("can47OpEn3EndER4Chest48569024");
                    user.removeMoney(20.00);
                } else {
                    player.sendMessage("§a  ");
                    player.sendMessage("§8[§d!§8] §cYou need $20.00 to purchase full access to the /enderchest command.");
                    player.sendMessage("§a  ");
                }


            }
        }
    }

}
