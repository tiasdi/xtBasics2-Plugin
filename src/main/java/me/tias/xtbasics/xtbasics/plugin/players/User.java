package me.tias.xtbasics.xtbasics.plugin.players;

import me.tias.xtbasics.xtbasics.XtBasics;
import me.tias.xtbasics.xtbasics.config.PlayerData;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {

    private XtBasics plugin = null;
    private Player player = null;
    private String ign = "x";
    private String uuid = "x";

    private double money;
    public final Map<String, Player> receivedRequests = new HashMap<>();

    public User(@NotNull final Player player, double money) {
        this.plugin = XtBasics.getInstance();
        this.player = player;
        this.money = money;
        this.uuid = player.getUniqueId().toString();
        this.ign = player.getName();
    }

    public Player get() {
        return player;
    }

    public String getIGN() {return ign;}

    public Map<String,Player> getReceivedRequests() {
        return  receivedRequests;
    }

    public void addMoney(double value) {
        money = PlayerData.get().getDouble(uuid+".Data.Money") + value;
        PlayerData.get().set(uuid+".Data.Money",money);
        PlayerData.save();
    }

    public void removeMoney(double value) {
        money = PlayerData.get().getDouble(uuid+".Data.Money") - value;
        PlayerData.get().set(uuid+".Data.Money",money);
        PlayerData.save();
    }

    public String getUUID() {
        return uuid;
    }

    public double getMoney() {
        money = PlayerData.get().getDouble(uuid+".Data.Money");
        return money;
    }



}
