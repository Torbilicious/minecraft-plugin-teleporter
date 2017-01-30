package de.schrottwald;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventListener implements Listener {

    public EventListener(TeleportPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerTot(PlayerDeathEvent ev) {
        Player p = ev.getEntity();
        p.sendMessage("Du hättest doch nur noch " + p.getExpToLevel() + " Exp\nbis Level " + (p.getLevel()  + 1) + "gebraucht :)");
    }

    @EventHandler
    public void onPlayerBeitritt(PlayerJoinEvent ev) {
        Player p = ev.getPlayer();
        p.sendMessage("Hallo " + p.getDisplayName());
    }

}
