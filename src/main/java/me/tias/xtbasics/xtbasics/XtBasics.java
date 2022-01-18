package me.tias.xtbasics.xtbasics;

import co.aikar.commands.PaperCommandManager;
import me.tias.xtbasics.xtbasics.config.Bounties;
import me.tias.xtbasics.xtbasics.config.PlayerData;
import me.tias.xtbasics.xtbasics.config.Warps;
import me.tias.xtbasics.xtbasics.plugin.commands.*;
import me.tias.xtbasics.xtbasics.plugin.listeners.EntityListener;
import me.tias.xtbasics.xtbasics.plugin.listeners.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public final class XtBasics extends JavaPlugin {

    static XtBasics instance;
    static ArrayList<String> warps = new ArrayList<>();
    static PaperCommandManager commandManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        setupListeners();
        setupConfig();
        warps.addAll(Objects.requireNonNull(Warps.get().getConfigurationSection("")).getKeys(false));
        commandManager = new PaperCommandManager(instance);
        setupCommands();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static XtBasics getInstance() {
        return instance;
    }

    public static ArrayList<String> getWarps() {
        return warps;
    }

    public static void addWarp(String Warp) {
        warps.add(Warp);
        commandManager.getCommandCompletions().registerCompletion("warps", c -> warps);
    }

    public void setupCommands() {
        commandManager.getCommandCompletions().registerCompletion("warps", c -> warps);
        commandManager.registerCommand(new BalanceCommand());
        commandManager.registerCommand(new SellCommand());
        commandManager.registerCommand(new AcceptCommand());
        commandManager.registerCommand(new TeleportCommand());
        commandManager.registerCommand(new HomeCommand());
        commandManager.registerCommand(new SetHomeCommand());
        commandManager.registerCommand(new GiveCommand());
        commandManager.registerCommand(new GameModeCommand());
        commandManager.registerCommand(new SpawnCommand());
        commandManager.registerCommand(new BackCommand());
        commandManager.registerCommand(new EChestCommand());
        commandManager.registerCommand(new FixCommand());
        commandManager.registerCommand(new NickNameCommand());
        commandManager.registerCommand(new SetWarpCommand());
        commandManager.registerCommand(new WarpCommand());
    }

    public void setupConfig() {
        //Default Config
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        //Custom Config
        PlayerData.setup();
        Bounties.setup();
        Warps.setup();
    }

    public void setupListeners() {
        new PlayerListener();
        new EntityListener();
    }






}
