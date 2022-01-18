package me.tias.xtbasics.xtbasics.plugin.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import me.tias.xtbasics.xtbasics.config.Bounties;
import me.tias.xtbasics.xtbasics.config.PlayerData;
import me.tias.xtbasics.xtbasics.plugin.leaderboards.BountyRankings;
import me.tias.xtbasics.xtbasics.plugin.players.User;
import me.tias.xtbasics.xtbasics.plugin.players.UserManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.swing.*;
import java.util.*;

@CommandAlias("bounties|bounty")
public class BountyCommand extends BaseCommand {

    @Default
    public void onBountyCommand(CommandSender sender) {
        if (sender instanceof Player player) {
            player.sendMessage("§a  ");
            player.sendMessage("§8[§9!§8] §cERROR: /bounty <list/add>");
            player.sendMessage("§a  ");
        }
    }

    @Subcommand("add")
    @CommandCompletion("@players")
    public void onBountyAdd(CommandSender sender, String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 2) {
                try {
                    Player target = Bukkit.getPlayer(args[0]);
                    try {
                        if (Double.parseDouble(args[1]) > 0.99) {
                            User user = UserManager.getSelected(player.getUniqueId());
                            User userT = UserManager.getSelected(target.getUniqueId());
                            if (user.getMoney() >= Double.parseDouble(args[1])) {
                                BountyRankings.addBounty(target,(double) Math.round((Double.parseDouble(args[1])) * 100) / 100);
                                user.removeMoney(Double.parseDouble(args[1]));
                                double newValue = PlayerData.get().getDouble(target.getUniqueId()+".Data.Bounty");
                                Bukkit.getOnlinePlayers().forEach(send -> {
                                    send.sendMessage("§a  ");
                                    send.sendMessage("§8[§e!§8] §e§lBOUNTY! §7- §6"+target.getName()+" now has a bounty of $"+newValue);
                                    send.sendMessage("§a  ");
                                });
                            } else {
                                player.sendMessage("§a  ");
                                player.sendMessage("§8[§9!§8] §cERROR: Insufficient funds");
                                player.sendMessage("§a  ");
                            }
                        } else {
                            player.sendMessage("§a  ");
                            player.sendMessage("§8[§9!§8] §cERROR: Value must be greater than $1.00");
                            player.sendMessage("§a  ");
                        }
                    } catch (Exception ex) {
                        player.sendMessage("§a  ");
                        player.sendMessage("§8[§9!§8] §cERROR: Please enter a valid number!");
                        player.sendMessage("§a  ");
                    }
                } catch (Exception ex) {
                    player.sendMessage("§a  ");
                    player.sendMessage("§8[§9!§8] §cERROR: That player is not online!");
                    player.sendMessage("§a  ");
                }
            } else {
                player.sendMessage("§a  ");
                player.sendMessage("§8[§9!§8] §cERROR: /bounty add <player> <value>");
                player.sendMessage("§a  ");
            }
        }
    }

    @Subcommand("list")
    public void onBountyList(CommandSender sender) {
        if (sender instanceof Player player) {
            player.sendMessage("§a  ");
            player.sendMessage("§8[§f!§8] §c§lPlayers with Active Bounties §r§8[§f!§8]");
            TreeMap<String,Double> bounties = BountyRankings.updateBounties();
            if (bounties.size() == 0 ) {
                player.sendMessage("  §7- §6"+bounties);
            } else if (bounties.size() > 7) {
                for(int i = 0; i < 8; ++i) {
                    player.sendMessage(bounties.keySet().toArray()[0]+"");
                }
            }
            player.sendMessage("§a  ");
        }
        }

    }
