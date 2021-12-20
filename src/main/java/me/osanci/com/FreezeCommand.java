package me.osanci.com;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FreezeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender.hasPermission("oFreeze.freeze")) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', oFreeze.getInstance().getConfig().getString("messages.usage")));//
                return true;
            }
            Player fp = Bukkit.getServer().getPlayer(args[0]);
            if (fp == null) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', oFreeze.getInstance().getConfig().getString("messages.error-player-not-found")));
                return true;
            }
            if (oFreeze.getInstance().getFreezeManager().isFrozen(fp)) {
                oFreeze.getInstance().getFreezeManager().setFrozen(fp, false);
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', oFreeze.getInstance().getConfig().getString("messages.unfroze-player").replace("{player}", fp.getName())));
                return true;
            }
            oFreeze.getInstance().getFreezeManager().setFrozen(fp, true);
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', oFreeze.getInstance().getConfig().getString("messages.froze-player").replace("{player}", fp.getName())));
            return true;
        }
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', oFreeze.getInstance().getConfig().getString("messages.no-permisson")));
        return false;
    }
}
