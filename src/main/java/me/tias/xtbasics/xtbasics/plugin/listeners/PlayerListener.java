package me.tias.xtbasics.xtbasics.plugin.listeners;

import me.tias.xtbasics.xtbasics.XtBasics;
import me.tias.xtbasics.xtbasics.config.Bounties;
import me.tias.xtbasics.xtbasics.config.PlayerData;
import me.tias.xtbasics.xtbasics.plugin.leaderboards.BountyRankings;
import me.tias.xtbasics.xtbasics.plugin.players.User;
import me.tias.xtbasics.xtbasics.plugin.players.UserManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.PermissionAttachment;

import java.util.Objects;

public class PlayerListener implements Listener {

    XtBasics plugin;

    public PlayerListener() {
        plugin = XtBasics.getInstance();
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }


    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player player = e.getPlayer();

        User victim = UserManager.getSelected(player.getUniqueId());
            player.sendMessage("§c§lYOU DIED! §7- §7Type §8/back §7to teleport to where you died for $25.00");
            PlayerData.get().set(player.getUniqueId()+".Death.World",player.getLocation().getWorld().getName().toString());
            PlayerData.get().set(player.getUniqueId()+".Death.X",player.getLocation().getX());
            PlayerData.get().set(player.getUniqueId()+".Death.Y",player.getLocation().getY()+0.5);
            PlayerData.get().set(player.getUniqueId()+".Death.Z",player.getLocation().getZ());

    }



    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        Player player = e.getPlayer();
        String uuid = e.getPlayer().getUniqueId().toString();
        double money = -1.25;

        if (PlayerData.get().isConfigurationSection(uuid)) {
            // Player Exists
            money = PlayerData.get().getDouble(uuid+".Data.Money");
            PlayerData.get().set(uuid+".Ip.Recent",player.getAddress().toString());
            PlayerData.get().set(uuid+".Username.Recent",player.getName().toString());
            if (!PlayerData.get().isConfigurationSection(uuid+".Death")) {
                PlayerData.get().set(player.getUniqueId()+".Death.World","world");
                PlayerData.get().set(player.getUniqueId()+".Death.X",0);
                PlayerData.get().set(player.getUniqueId()+".Death.Z",0);
            }
            PlayerData.get().set(player.getUniqueId()+".Death.Y",-280);
        } else {
            // New Player
            PlayerData.get().set(uuid+".Data.Money",0.00);
            PlayerData.get().set(uuid+".Data.Star",1);
            PlayerData.get().set(uuid+".Home.X",0);
            PlayerData.get().set(uuid+".Home.Y",-256);
            PlayerData.get().set(uuid+".Home.Z",0);
            PlayerData.get().set(uuid+".Ip.Recent",player.getAddress().toString());
            PlayerData.get().set(uuid+".Ip.First",player.getAddress().toString());
            PlayerData.get().set(uuid+".Username.Recent",player.getName().toString());
            PlayerData.get().set(uuid+".Username.First",player.getName().toString());
            PlayerData.get().set(player.getUniqueId()+".Death.World","world");
            PlayerData.get().set(player.getUniqueId()+".Death.X",0);
            PlayerData.get().set(player.getUniqueId()+".Death.Y",-280);
            PlayerData.get().set(player.getUniqueId()+".Death.Z",0);
            PlayerData.get().set(player.getUniqueId()+".Data.Nickname","%");
            money = PlayerData.get().getInt(uuid + ".Data.Money");
            player.teleport(Bukkit.getWorld("world").getSpawnLocation().clone().set(XtBasics.getInstance().getConfig().getInt("Spawn.X")+0.5,XtBasics.getInstance().getConfig().getInt("Spawn.Y")+1.2,XtBasics.getInstance().getConfig().getInt("Spawn.Z")+0.5));
        }

        PlayerData.save();
        money = (double) Math.round((money) * 100) / 100;
        User user = UserManager.createPlayer(player,money);
        player.sendMessage("You have: $"+money+" ");

        //Nickname
        String name = PlayerData.get().getString(player.getUniqueId().toString()+".Data.Nickname");
        try {
            if (!Objects.requireNonNull(name).equals("%")) {
                if (!name.equalsIgnoreCase(player.getName())) {
                    player.setPlayerListName("§f" + player.getName() + " §7[" + name + "]");
                    e.setJoinMessage("§8[§a+§8] §6"+player.getName()+" §7("+name+") §ehas joined the game");
                    player.setDisplayName("§7" + name);
                } else {
                    player.setPlayerListName("§f" + name);
                    e.setJoinMessage("§8[§a+§8] §6"+player.getName()+" §ehas joined the game");
                    player.setDisplayName("§f" + name);
                }
            }
        } catch (Exception ignored) {
        }
    }

    @EventHandler
    public void onChat(PlayerChatEvent e) {
        Player player = e.getPlayer();
        e.setFormat(player.getDisplayName()+" §8> §7"+e.getMessage());
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        String name = PlayerData.get().getString(player.getUniqueId().toString() + ".Data.Nickname");
        try {
            if (!Objects.equals(name, "%")) {
                if (!Objects.requireNonNull(name).equalsIgnoreCase(player.getName())) {
                    player.setPlayerListName("§f" + player.getName() + " §7[" + name + "]");
                    e.setQuitMessage("§8[§c-§8] §6" + player.getName() + " §7(" + name + ") §ehas rage quit");
                } else {
                    player.setPlayerListName("§f" + name);
                    e.setQuitMessage("§8[§c-§8] §6" + player.getName() + " §ehas rage quit");
                }
                player.setDisplayName("§f" + name);
            }
        } catch (Exception ignored) {
        }
    }

    @EventHandler
    public void fixStrippedLogs(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (Objects.requireNonNull(e.getClickedBlock()).getType().toString().contains("STRIPPED") && e.getPlayer().getInventory().getItemInMainHand().getType().toString().contains("AXE")) {
                e.getClickedBlock().setType(Material.valueOf(e.getClickedBlock().getType().toString().replace("STRIPPED_","")));
            }
        }
    }


}
