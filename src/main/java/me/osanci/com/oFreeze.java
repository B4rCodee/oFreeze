package me.osanci.com;

import java.util.ArrayList;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class oFreeze extends JavaPlugin {

    private FreezeManager freezeManager;
    private static oFreeze instance;

    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("§8§m-----------------");
        Bukkit.getConsoleSender().sendMessage("§aoFreeze started successfully");
        Bukkit.getConsoleSender().sendMessage("§aPlugin version: §21.0");
        Bukkit.getConsoleSender().sendMessage("§aIf you like this plugin, please rate it on spigot");
        Bukkit.getConsoleSender().sendMessage("§8§m-----------------");
        this.freezeManager = new FreezeManager();
        this.instance = this;
        getServer().getPluginManager().registerEvents(new Listeners(), (Plugin)this);
        getCommand("freeze").setExecutor(new FreezeCommand());
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
    }

    public FreezeManager getFreezeManager() {
        return freezeManager;
    }
    public static oFreeze getInstance() {
        return instance;
    }

}