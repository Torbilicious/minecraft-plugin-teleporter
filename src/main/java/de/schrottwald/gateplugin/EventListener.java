package de.schrottwald.gateplugin;

import de.schrottwald.gateplugin.gate.Gate;
import de.schrottwald.gateplugin.gate.GateNetwork;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Lever;

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

            //TODO: check if this sign has the correct gate syntax

            if (e.getClickedBlock().getState() instanceof Sign) {
                GateNetwork network = new GateNetwork("");
                Location tempLocation = new Location(plugin.getServer().getWorld("world"), 0, 0, 0);

                for (int i = 1; i < 10; i++) {
                    network.addNewGate(tempLocation, "Gate" + i);
                }

                Sign s = (Sign) e.getClickedBlock().getState();

                Gate target = network.getGateByName(extractGateName(s.getLine(2)));

                s.setLine(1,s.getLine(2));
                s.setLine(2,s.getLine(3));

                Gate gateLine4 = network.getGateByName(s.getLine(3));

                s.setLine(3, network.getNextGate(gateLine4).getName());

                s.update();
                Integer index = network.getIndexOfGate(s.getLine(2));
                getLogger().info("" + index + "/" + network.getNumberOfGates());
                getLogger().info("Target: jbdfklbhdghi" + target.getName());
                getLogger().info("" + s.getLine(2));
            }
        }
    }

    @EventHandler
    public void leverClick(PlayerInteractEvent e){

        getLogger().info("Lever: 1");

        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {

            getLogger().info("Lever: 2");


            if (e.getClickedBlock().getType() == Material.LEVER) {
                Lever l = (Lever) e.getClickedBlock().getState().getData();

                getLogger().info("Lever facing: " + l.getFacing());
                getLogger().info("Lever powered: " + l.isPowered());
            }
        }
    }

    private String extractGateName(String line) {

//        return line.substring(line.indexOf(">§a") + 1, line.indexOf("§0<"));
        return line;
    }
}
