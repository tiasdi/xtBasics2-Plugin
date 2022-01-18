package me.tias.xtbasics.xtbasics.config;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class PlayerData {

    // File Name
    private static final String FILENAME = "PlayerData";

    private static File file;
    private static FileConfiguration customFile;

    //Create config
    private static void createFile() {
        //customFile.addDefault("Path.Path","Value");







    }

    //region >> Code
    //Finds or generates custom config file
    public static void setup() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("xtBasics").getDataFolder(),FILENAME+".yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception ex) {
                //file doesnt exist.
            }
        }
        customFile = YamlConfiguration.loadConfiguration(file);
        createFile();
        customFile.options().copyDefaults(true);
        save();
    }

    public static FileConfiguration get() {
        return customFile;
    }

    public static void save() {
        try {
            customFile.save(file);
        } catch (IOException ex) {
            System.out.println("Couldn't save file: "+FILENAME+".yml");
        }
    }

    public static void reload() {
        customFile = YamlConfiguration.loadConfiguration(file);
    }
    //endregion
}
