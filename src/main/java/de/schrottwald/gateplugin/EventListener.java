package de.schrottwald.gateplugin;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class EventListener implements Listener {

    private final TeleportPlugin plugin;

    EventListener(TeleportPlugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
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

    @EventHandler
    public void obsidianMined(BlockBreakEvent event) {

        Block block = event.getBlock();

        if (!(block.getType().equals(Material.OBSIDIAN))) {
            return;
        }

        plugin.getLogger().info("Obsidian abgebaut von: " + event.getPlayer().getName());
    }

    @EventHandler
    public void obsidianPlaced(BlockPlaceEvent event) {

        Block block = event.getBlock();

        if (!(block.getType().equals(Material.OBSIDIAN))) {
            return;
        }

        plugin.getLogger().info("Obsidian gebaut von: " + event.getPlayer().getName());

    }
}
