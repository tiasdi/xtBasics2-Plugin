package me.tias.xtbasics.xtbasics.plugin.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import com.destroystokyo.paper.utils.PaperPluginLogger;
import me.tias.xtbasics.xtbasics.XtBasics;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("spawn|hub|lobby|l|s")
public class SpawnCommand extends BaseCommand {

    @Default
    public void onSpawn(CommandSender sender) {
        if (sender instanceof Player player) {
            player.teleport(Bukkit.getWorld("world").getSpawnLocation().clone().set(XtBasics.getInstance().getConfig().getInt("Spawn.X")+0.5,XtBasics.getInstance().getConfig().getInt("Spawn.Y")+1.2,XtBasics.getInstance().getConfig().getInt("Spawn.Z")+0.5));
            player.sendMessage("§a  ");
            player.sendMessage("§8[§9!§8] §dTeleporting to Spawn..");
            player.sendMessage("§a  ");
        }
    }
}
