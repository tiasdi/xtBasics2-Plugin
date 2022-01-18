package me.tias.xtbasics.xtbasics.plugin.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Default;
import me.tias.xtbasics.xtbasics.XtBasics;
import me.tias.xtbasics.xtbasics.config.PlayerData;
import me.tias.xtbasics.xtbasics.config.Warps;
import me.tias.xtbasics.xtbasics.plugin.players.User;
import me.tias.xtbasics.xtbasics.plugin.players.UserManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.checkerframework.framework.qual.DefaultQualifierForUse;

import java.util.Objects;

@CommandAlias("warp")
public class WarpCommand extends BaseCommand {

    @Default
    @CommandCompletion("@warps")
    public void onWarpCommand(CommandSender sender, String[] args) {
        if (sender instanceof Player player) {
            User user = UserManager.getSelected(player.getUniqueId());
          if (args.length == 1) {
              String warp = args[0];
              if (Warps.get().isConfigurationSection(warp)) {
                  if (user.getMoney() > 0.19) {
                      try {
                          Location location = Objects.requireNonNull(Warps.get().getLocation(warp + ".Location")).clone().add(0.0,0.5,0.0);
                          String creator = Warps.get().getString(warp+".Creator");

                          if (player.getUniqueId().toString().equalsIgnoreCase(creator)) {

                              player.sendMessage("§a  ");
                              player.sendMessage("§8[§9!§8] §dTeleporting to §5"+warp+"§d.. §7[-$0.20]");
                              player.sendMessage("§a  ");
                              user.removeMoney(0.20);
                              Warps.get().set(warp+".Uses",Warps.get().getInt(warp+"Uses")+1);
                              Warps.save();

                          } else {

                              player.sendMessage("§a  ");
                              player.sendMessage("§8[§9!§8] §dTeleporting to §5"+warp+"§d.. §7[-$0.20]");
                              player.sendMessage("§a  ");
                              user.removeMoney(0.20);
                              PlayerData.get().set(creator+".Data.Money",PlayerData.get().getDouble(creator+".Data.Money"+0.10));
                              Warps.get().set(warp+".Uses",Warps.get().getInt(warp+"Uses")+1);


                          }
                          // Teleport
                          PlayerData.save();
                          Warps.save();
                          PotionEffect damage = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 60, 255, false, false);
                          PotionEffect falling = new PotionEffect(PotionEffectType.SLOW_FALLING, 30, 4, false, false);
                          PotionEffect fire = new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 100, 266, false, false);
                          Bukkit.getScheduler().runTaskLater(XtBasics.getInstance(), () -> {
                              player.teleport(location);
                              player.addPotionEffect(damage);
                              player.addPotionEffect(fire);
                              player.addPotionEffect(falling);
                          }, 35);





                      } catch (Exception e) {
                          player.sendMessage("§a  ");
                          player.sendMessage("§8[§9!§8] §cThat warp does not exist");
                          player.sendMessage("§a  ");
                      }
                  } else {
                      player.sendMessage("§a  ");
                      player.sendMessage("§8[§9!§8] §cYou need $0.20 to use a warp");
                      player.sendMessage("§a  ");
                  }
              } else {
                  player.sendMessage("§a  ");
                  player.sendMessage("§8[§9!§8] §cThat warp does not exist");
                  player.sendMessage("§a  ");
              }
          } else {
              player.sendMessage("§a  ");
              player.sendMessage("§8[§9!§8] §c/warp <warp>");
              player.sendMessage("§a  ");
          }
        }
    }

}
