package de.schrottwald.gateplugin;

import org.bukkit.ChatColor;
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

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getLogger;

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

    @EventHandler
    public void signClick(PlayerInteractEvent e){

        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {

            if (e.getClickedBlock().getState() instanceof Sign) {
                List<String> gateList = new ArrayList<>();
                for (int i = 1; i < 10; i++) {
                    gateList.add("Gate" + i);
                }

                Sign s = (Sign) e.getClickedBlock().getState();

                int index = 0;
                for (int i = 0; i < gateList.size(); i++) {
                    String line2 = s.getLine(2);
                    String text = line2.substring(line2.indexOf(">§a") + 1, line2.indexOf("§0<"));
//                    String text2 = ((line2).replaceAll(">§a","")).replaceAll("§0<","");
                    if(gateList.get(i).equals(text)) {
                        index = i;
                    }
                }
                /* Kann ich für die umgekehrte Funktion noch gebrauchen
                if (index - 1 < 0) {
                    s.setLine(0,gateList.get(gateList.size()));
                } else {
                    s.setLine(0,gateList.get(index - 1));
                }
                */
                s.setLine(1,s.getLine(2));
                s.setLine(2,">" + ChatColor.GREEN + s.getLine(3) + ChatColor.BLACK + "<");
                if (index + 2 == gateList.size()) {
                    s.setLine(3, gateList.get(0));
                } else if (index + 2 > gateList.size()) {
                    s.setLine(3, gateList.get(1));
                } else {
                    s.setLine(3,gateList.get(index + 2));
                }
                s.update();
                getLogger().info("" + index + "/" + gateList.size());
                getLogger().info("" + ((s.getLine(2)).replaceAll(">§a","")).replaceAll("§0<",""));
                getLogger().info("" + s.getLine(2));
            }
        }
    }
}
