package me.tias.xtbasics.xtbasics.plugin.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Default;
import me.tias.xtbasics.xtbasics.XtBasics;
import me.tias.xtbasics.xtbasics.plugin.players.User;
import me.tias.xtbasics.xtbasics.plugin.players.UserManager;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("tp|teleport")
public class TeleportCommand extends BaseCommand {

    @Default
    @CommandCompletion("@players")
    public void onTpCommand(CommandSender sender, String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 1) {
                if (!player.getName().equalsIgnoreCase(args[0])) {
                    try {
                        Player target = Bukkit.getPlayer(args[0]);
                        if (target.isOnline()) {
                            User userT = UserManager.getSelected(target.getUniqueId());
                            if (!userT.getReceivedRequests().containsKey(player.getUniqueId().toString())) {

                                userT.getReceivedRequests().put(player.getUniqueId().toString(),player);

                                player.sendMessage("§a  ");
                                player.sendMessage("§8[§9!§8] §6"+target.getName()+" §ahas recieved your Teleport Request.");
                                player.sendMessage("§7(They have 30 seconds to accept your request via /accept "+player.getName()+")");
                                player.sendMessage("§a  ");

                                target.sendMessage("§a  ");
                                target.sendMessage("§8[§9!§8] §2"+player.getName()+" §ahas requested to teleport to you!");
                                target.sendMessage("§7(You may accept their request by using /accept "+player.getName()+")");
                                target.sendMessage("§a  ");
                                target.playSound(target.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_STEP,50,1);

                                Bukkit.getScheduler().runTaskLater(XtBasics.getInstance(), () -> {
                                    if(userT.getReceivedRequests().containsKey(player.getUniqueId().toString())) {
                                        player.sendMessage("§a  ");
                                        player.sendMessage("§8[§9!§8] §cYour teleport request to §4"+target.getName()+" §chas expired.");
                                        player.sendMessage("§a  ");
                                        target.sendMessage("§a  ");
                                        target.sendMessage("§8[§9!§8] §cThe teleport request from §4"+player.getName()+" §chas expired.");
                                        target.sendMessage("§a  ");
                                        userT.getReceivedRequests().remove(player.getUniqueId().toString());
                                    }
                                }, 640);



                            } else {
                                player.sendMessage("§a  ");
                                player.sendMessage("§8[§9!§8] §cERROR: You already have a pending TP request to this player.");
                                player.sendMessage("§a  ");
                            }
                        } else {
                            player.sendMessage("§a  ");
                            player.sendMessage("§8[§9!§8] §cERROR: That player is not online.");
                            player.sendMessage("§a  ");
                        }
                    } catch (Exception ex) {
                        player.sendMessage("§a  ");
                        player.sendMessage("§8[§9!§8] §cERROR: That player is not online.");
                        player.sendMessage("§a  ");
                    }
                } else {
                    player.sendMessage("§a  ");
                    player.sendMessage("§8[§9!§8] §cERROR: You cannot teleport to yourself!");
                    player.sendMessage("§a  ");
                }
            } else {
                player.sendMessage("§a  ");
                player.sendMessage("§8[§9!§8] §cERROR: /tp <player>");
                player.sendMessage("§a  ");
            }
        }
    }


}
