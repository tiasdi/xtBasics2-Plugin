package me.tias.xtbasics.xtbasics.plugin.players;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserManager {

    private static final Map<UUID, User> userList = new HashMap<>();
    User user;

    public static User createPlayer(Player player, double money) {
        User user = new User(player, money);

        if (userList.containsKey(player.getUniqueId())) {
            userList.replace(player.getUniqueId(), user);
        } else {
            userList.put(player.getUniqueId(), user);
        }

        return user;
    }

    public static void removePlayer(Player player) {
        UUID uuid = player.getUniqueId();
        userList.remove(uuid);
    }

    public static User getSelected(UUID uuid) {
        return userList.get(uuid);
    }

}
