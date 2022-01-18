package me.tias.xtbasics.xtbasics.plugin.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import com.destroystokyo.paper.utils.PaperPluginLogger;
import me.tias.xtbasics.xtbasics.config.PlayerData;
import me.tias.xtbasics.xtbasics.plugin.players.User;
import me.tias.xtbasics.xtbasics.plugin.players.UserManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("nick|nickname")
public class NickNameCommand extends BaseCommand {

    @Default
    public void onNickCommand(CommandSender sender, String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 1) {
                String name = args[0];
                User user = UserManager.getSelected(player.getUniqueId());
                if (user.getMoney() > 9.99) {
                    if (name.length() > 1 && name.length() < 17) {
                        if (name.contains(" ")) {
                            player.sendMessage("§a  ");
                            player.sendMessage("§8[§6!§8] §cNicknames may not contain spaces, bitch.");
                            player.sendMessage("§a  ");
                        } else {
                            if (!name.equalsIgnoreCase(player.getName())) {
                                player.setPlayerListName("§f" + player.getName() + " §7[" + name + "]");

                                player.setDisplayName("§7" + name);
                                player.sendMessage("§a  ");
                                player.sendMessage("§8[§6!§8] §bYour nickname is now §3"+name+"§b! §7[-$10.00]");
                                player.sendMessage("§a  ");
                                user.removeMoney(10.00);


                            } else {
                                player.setPlayerListName("§f" + name);

                                player.setDisplayName("§f" + name);
                                player.sendMessage("§a  ");
                                player.sendMessage("§8[§6!§8] §bYour nickname has been reset to §3"+name+"§b! §7[-$0.00]");
                                player.sendMessage("§a  ");
                            }
                            PlayerData.get().set(player.getUniqueId().toString()+".Data.Nickname", name);
                            PlayerData.save();
                        }
                    } else {
                        player.sendMessage("§a  ");
                        player.sendMessage("§8[§6!§8] §cNicknames must be between 2 and 16 Characters.");
                        player.sendMessage("§a  ");
                    }
                } else {
                    if (name.equalsIgnoreCase(player.getName())) {
                        player.setPlayerListName("§f" + name);

                        player.setDisplayName("§f" + name);
                        player.sendMessage("§a  ");
                        player.sendMessage("§8[§6!§8] §bYour nickname has been reset to §3"+name+"§b! §7[-$0.00]");
                        player.sendMessage("§a  ");
                        PlayerData.get().set(player.getUniqueId().toString()+".Data.Nickname",name);
                        PlayerData.save();
                    } else {
                        player.sendMessage("§a  ");
                        player.sendMessage("§8[§6!§8] §cIt costs $10.00 to change your nickname.");
                        player.sendMessage("§a  ");
                    }
                }
            } else {
                player.sendMessage("§a  ");
                player.sendMessage("§8[§6!§8] §c/nickname <NewName>");
                player.sendMessage("§a  ");
            }
        }
    }
}
