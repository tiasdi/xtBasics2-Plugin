package me.tias.xtbasics.xtbasics.plugin.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Default;
import me.tias.xtbasics.xtbasics.XtBasics;
import me.tias.xtbasics.xtbasics.plugin.players.User;
import me.tias.xtbasics.xtbasics.plugin.players.UserManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@CommandAlias("accept|accepttp")
public class AcceptCommand extends BaseCommand {

    @Default
    @CommandCompletion("@players")
    public void onAccept(CommandSender sender, String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 1) {
                User user = UserManager.getSelected(player.getUniqueId());
                try {
                    String str = args[0];
                    Player accept = Bukkit.getPlayer(str);
                    if (accept.isOnline()) {
                        if (user.getReceivedRequests().containsKey(accept.getUniqueId().toString())) {
                            Location location = player.getLocation().clone().add(0.0, 0.8, 0.0);
                            PotionEffect damage = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 60, 255, false, false);
                            PotionEffect falling = new PotionEffect(PotionEffectType.SLOW_FALLING, 30, 4, false, false);
                            PotionEffect fire = new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 100, 266, false, false);
                            accept.sendMessage("§a  ");
                            accept.sendMessage("§8[§9!§8] §eYour teleport request to §6" + player.getName() + " §ehas been accepted! §d§oTeleporting...");
                            accept.sendMessage("§a  ");
                            accept.playSound(accept.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 50, 1);
                            player.sendMessage("§a  ");
                            player.sendMessage("§8[§9!§8] §eRequest accepted! §d§oTeleporting...");
                            player.sendMessage("§a  ");
                            user.getReceivedRequests().remove(accept.getUniqueId().toString());
                            Bukkit.getScheduler().runTaskLater(XtBasics.getInstance(), () -> {
                                accept.teleport(location);
                                accept.addPotionEffect(damage);
                                accept.addPotionEffect(fire);
                                accept.addPotionEffect(falling);
                            }, 45);
                        } else {
                            player.sendMessage("§a  ");
                            player.sendMessage("§8[§9!§8] §cYou do not have a request from that person.");
                            player.sendMessage("§a  ");
                        }
                    } else {
                        player.sendMessage("§a  ");
                        player.sendMessage("§8[§9!§8] §cThat player is not online.");
                        player.sendMessage("§a  ");
                    }
                } catch (Exception ex) {
                    player.sendMessage("§a  ");
                    player.sendMessage("§8[§9!§8] §cThat player is not online.");
                    player.sendMessage("§a  ");
                }
            } else {
                player.sendMessage("§a  ");
                player.sendMessage("§8[§9!§8] §c/accept <player>");
                player.sendMessage("§a  ");
            }
        }

    }

}
