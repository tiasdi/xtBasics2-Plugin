package me.tias.xtbasics.xtbasics.plugin.leaderboards;

import me.tias.xtbasics.xtbasics.config.Bounties;
import me.tias.xtbasics.xtbasics.config.PlayerData;
import org.bukkit.entity.Player;

import java.util.*;

public class BountyRankings {

    static HashMap<String, Double> map = new HashMap<String, Double>();

    public static TreeMap<String, Double> updateBounties() {
        map.clear();
        for (String s : Objects.requireNonNull(PlayerData.get().getConfigurationSection("")).getKeys(false)) {
            if (PlayerData.get().getDouble(s + ".Data.Bounty") > 0.95) {
                map.put(PlayerData.get().getString(s + ".Username.Recent"), PlayerData.get().getDouble(s + ".Data.Bounty"));
            }
        }
        ValueComparator bvc = new ValueComparator(map);
        TreeMap<String, Double> sortedMap = new TreeMap<String, Double>(bvc);
        sortedMap.putAll(map);
        return sortedMap;
    }

    public static void addBounty(Player target, double Money) {
        PlayerData.get().set(target.getUniqueId().toString()+".Data.Bounty",  PlayerData.get().getDouble(target.getUniqueId().toString()+".Data.Bounty")+Money);
        PlayerData.save();
        updateBounties();
    }


    public static void removeBounty(Player target) {
        PlayerData.get().set(target.getUniqueId().toString()+".Data.Bounty", 0.0);
        PlayerData.save();
        updateBounties();
    }


}



class ValueComparator implements Comparator<String> {
    Map<String, Double> base;

    public ValueComparator(Map<String, Double> base) {
        this.base = base;
    }

    // Note: this comparator imposes orderings that are inconsistent with
    // equals.
    public int compare(String a, String b) {
        if (base.get(a) >= base.get(b)) {
            return -1;
        } else {
            return 1;
        } // returning 0 would merge keys
    }
}
