package de.schrottwald;

import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class EventListener implements Listener {

    EventListener(TeleportPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent ev) {
        Player p = ev.getEntity();
        p.sendMessage("Du hättest doch nur noch " + p.getExpToLevel() + " Exp\nbis Level " + (p.getLevel()  + 1) + "gebraucht :)");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent ev) {
        Player p = ev.getPlayer();
        p.sendMessage("Hallo " + p.getDisplayName());
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();

        if (e.getAction() == Action.RIGHT_CLICK_BLOCK){

            if (e.getClickedBlock().getState() instanceof Sign) {

                Sign s = (Sign) e.getClickedBlock().getState();

                if(s.getLine(0).equalsIgnoreCase("Test")){

                    p.getInventory().addItem(new ItemStack(Material.DIAMOND, 1));
                }
            }
        }
    }

}
