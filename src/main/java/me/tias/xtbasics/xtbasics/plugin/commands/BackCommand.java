package me.tias.xtbasics.xtbasics.plugin.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import com.destroystokyo.paper.utils.PaperPluginLogger;
import me.tias.xtbasics.xtbasics.XtBasics;
import me.tias.xtbasics.xtbasics.config.PlayerData;
import me.tias.xtbasics.xtbasics.plugin.players.User;
import me.tias.xtbasics.xtbasics.plugin.players.UserManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;

@CommandAlias("back")
public class BackCommand extends BaseCommand {

    @Default
    public void onBackCommand(CommandSender sender) {
        if (sender instanceof Player player) {
            User user = UserManager.getSelected(player.getUniqueId());
            double x = PlayerData.get().getDouble(user.getUUID()+".Death.X");
            double y = PlayerData.get().getDouble(user.getUUID()+".Death.Y");
            double z = PlayerData.get().getDouble(user.getUUID()+".Death.Z");
            PotionEffect damage = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 60, 255, false, false);
            PotionEffect falling = new PotionEffect(PotionEffectType.SLOW_FALLING, 40, 4, false, false);
            PotionEffect fire = new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 120, 266, false, false);
            String world = PlayerData.get().getString(user.getUUID()+".Death.World");
            if (y > -250) {
                if (user.getMoney() > 24.99) {
                    user.removeMoney(25.00);
                    player.sendMessage("§a  ");
                    player.sendMessage("§8[§c!§8] §5Teleporting you to where you last died.. §d[-$25.00]");
                    player.sendMessage("§a  ");
                    Bukkit.getScheduler().runTaskLater(XtBasics.getInstance(), () -> {
                        player.teleport(new Location(Bukkit.getWorld(world), x, y, z));
                        player.addPotionEffect(damage);
                        player.addPotionEffect(falling);
                        player.addPotionEffect(fire);
                    }, 30);
                } else {
                    player.sendMessage("§a  ");
                    player.sendMessage("§8[§c!§8] §cYou need $25.00 to use /back.");
                    player.sendMessage("§a  ");
                }
            } else {
                player.sendMessage("§a  ");
                player.sendMessage("§8[§c!§8] §cYou cannot do /back as you have not died recently.");
                player.sendMessage("§a  ");
            }
        }
    }
}
