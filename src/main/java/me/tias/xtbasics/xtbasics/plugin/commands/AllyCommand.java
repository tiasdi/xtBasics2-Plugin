package me.tias.xtbasics.xtbasics.plugin.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import me.tias.xtbasics.xtbasics.config.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@CommandAlias("ally|alliance")
public class AllyCommand extends BaseCommand {

    @Default
    public void onAllyCommand(CommandSender sender) {
        if (sender instanceof Player player) {
            player.sendMessage("§a  ");
            player.sendMessage("§8[§6!§8] §cERROR: /ally <add/end> <player>");
            player.sendMessage("§a  ");
        }
    }

    @Subcommand("add")
    @CommandCompletion("@players")
    public void onAllyAdd(CommandSender sender, String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 1) {
                if (!player.getName().equalsIgnoreCase(args[0])) {
                    try {
                        Player target = Bukkit.getPlayer(args[0]);
                        if (target.isOnline()) {
                            if (!player.getScoreboardTags().contains(target.getUniqueId().toString())) {
                                player.getScoreboardTags().add(target.getUniqueId().toString());
                                player.sendMessage("§a  ");
                                player.sendMessage("§8[§6!§8] §2"+target.getName()+" §ais now your ally!");
                                player.sendMessage("§a  ");

                                target.sendMessage("§a  ");
                                target.sendMessage("§8[§6!§8] §2"+player.getName()+" §ahas allied you! §7Use §8/ally add "+player.getName()+" §7to ally them back!");
                                target.sendMessage("§a  ");
                                target.playSound(target.getLocation(), Sound.BLOCK_NOTE_BLOCK_COW_BELL,50,1);
                            } else {
                                player.sendMessage("§a  ");
                                player.sendMessage("§8[§6!§8] §cERROR: You have already allied this player.");
                                player.sendMessage("§a  ");
                            }
                        } else {
                            player.sendMessage("§a  ");
                            player.sendMessage("§8[§6!§8] §cERROR: That player is not online.");
                            player.sendMessage("§a  ");
                        }
                    } catch (Exception ex) {
                        player.sendMessage("§a  ");
                        player.sendMessage("§8[§6!§8] §cERROR: That player is not online.");
                        player.sendMessage("§a  ");
                    }
                } else {
                    player.sendMessage("§a  ");
                    player.sendMessage("§8[§6!§8] §cERROR: You cannot ally yourself!");
                    player.sendMessage("§a  ");
                }
            } else {
                player.sendMessage("§a  ");
                player.sendMessage("§8[§6!§8] §cERROR: /ally <add> <player>");
                player.sendMessage("§a  ");
            }
        }
    }

    @Subcommand("end")
    @CommandCompletion("@players")
    public void onAllyEnd(CommandSender sender, String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 1) {
                if (!player.getName().equalsIgnoreCase(args[0])) {
                    try {
                        Player target = Bukkit.getPlayer(args[0]);
                        if (target.isOnline()) {
                            if (player.getScoreboardTags().contains(target.getUniqueId().toString())) {
                                player.getScoreboardTags().remove(target.getUniqueId().toString());
                                player.sendMessage("§a  ");
                                player.sendMessage("§8[§6!§8] §2"+target.getName()+" §ais no longer your ally!");
                                player.sendMessage("§a  ");

                                target.sendMessage("§a  ");
                                target.sendMessage("§8[§6!§8] §4"+player.getName()+" §chas un-allied you!");
                                target.sendMessage("§a  ");
                                target.playSound(target.getLocation(), Sound.BLOCK_ANVIL_DESTROY,50,1);
                            } else {
                                player.sendMessage("§a  ");
                                player.sendMessage("§8[§6!§8] §cERROR: You do not have this player allied.");
                                player.sendMessage("§a  ");
                            }
                        } else {
                            player.sendMessage("§a  ");
                            player.sendMessage("§8[§6!§8] §cERROR: That player is not online.");
                            player.sendMessage("§a  ");
                        }
                    } catch (Exception ex) {
                        player.sendMessage("§a  ");
                        player.sendMessage("§8[§6!§8] §cERROR: That player is not online.");
                        player.sendMessage("§a  ");
                    }
                } else {
                    player.sendMessage("§a  ");
                    player.sendMessage("§8[§6!§8] §cERROR: You cannot ally yourself!");
                    player.sendMessage("§a  ");
                }
            } else {
                player.sendMessage("§a  ");
                player.sendMessage("§8[§6!§8] §cERROR: /ally <end> <player>");
                player.sendMessage("§a  ");
            }
        }
    }

    @Subcommand("list")
    public void onAllyList(CommandSender sender, String[] args) {
        if (sender instanceof Player player) {
            player.sendMessage("§a  ");
            player.sendMessage("§8[§f!§8] §c§lPlayers that you have allied §r§8[§f!§8]");
            Set<String> allies = player.getScoreboardTags().stream().filter(s -> s.length() >= 35).collect(Collectors.toSet());
            if (allies.size() == 0) {
                player.sendMessage("§7 - §f§oYou have no allies.");
            } else {
                for (String str : allies) {
                    player.sendMessage("§7  - §e" + PlayerData.get().getString(str+".Username.Recent"));
                }
            }
            player.sendMessage("§a  ");
        }
    }


}
