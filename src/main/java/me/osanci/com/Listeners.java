package me.osanci.com;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Listeners implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (oFreeze.getInstance().getFreezeManager().isFrozen(p)) {
            e.setTo(e.getFrom());
            for(String s : oFreeze.getInstance().getConfig().getStringList("messages.frozen-message")) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', s));
            }
        }
    }
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        Player p = (Player)event.getEntity();
        Player d = (Player) event.getDamager();
        if (p instanceof Player && oFreeze.getInstance().getFreezeManager().isFrozen(p)) {
            event.setCancelled(true);
            if (d instanceof Player) {
                d.sendMessage(ChatColor.translateAlternateColorCodes('&', oFreeze.getInstance().getConfig().getString("messages.cant-damage-frozen-message").replace("{player}", p.getName())));
            }
        } else if (d instanceof Player && oFreeze.getInstance().getFreezeManager().isFrozen(d)) {
            event.setCancelled(true);
            d.sendMessage(ChatColor.translateAlternateColorCodes('&', oFreeze.getInstance().getConfig().getString("messages.cant-damage-while-frozen-message")));

        }
    }


    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (oFreeze.getInstance().getFreezeManager().isFrozen(p)) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', oFreeze.getInstance().getConfig().getString("messages.break-block-message")));//
        }
    }
    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        if (oFreeze.getInstance().getFreezeManager().isFrozen(p)) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', oFreeze.getInstance().getConfig().getString("messages.place-block-message")));//
        }
    }
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if(oFreeze.getInstance().getFreezeManager().isFrozen(p)) {
            if (e.getMessage().startsWith("/")) return;
            if (!oFreeze.getInstance().getConfig().getBoolean("send-messages-while-frozen")) return;
            e.setCancelled(true);//
            e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', oFreeze.getInstance().getConfig().getString("messages.chat-message-blocked")));//
        }
    }


    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (!oFreeze.getInstance().getFreezeManager().isFrozen(p)) return;
        if (!oFreeze.getInstance().getConfig().getBoolean("settings.ban-players")) return;
        oFreeze.getInstance().getFreezeManager().setFrozen(e.getPlayer(), false);
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', oFreeze.getInstance().getConfig().getString("messages.left-while-frozen")).replace("{player}", e.getPlayer().getName()));
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ChatColor.translateAlternateColorCodes('&', oFreeze.getInstance().getConfig().getString("settings.ban-command").replace("{player}", e.getPlayer().getName())));
    }
}
