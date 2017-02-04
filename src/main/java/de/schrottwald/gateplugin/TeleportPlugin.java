package de.schrottwald.gateplugin;

import de.schrottwald.gateplugin.gate.Gate;
import de.schrottwald.gateplugin.gate.GateNetwork;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class TeleportPlugin extends JavaPlugin {

    private GateNetwork network;

    @Override
    public void onDisable() {

        super.onDisable();

        getLogger().info("onDisable has been invoked!");
    }

    @Override
    public void onEnable() {

        super.onEnable();

        new EventListener(this);
        network = new GateNetwork("");

        Location location = new Location(getServer().getWorld("world"),-188D, 95D, 296D );
        network.addNewGate(location, "Torbilicious");

        getLogger().info("onEnable has been invoked!");

        FileConfiguration config = this.getConfig();

        config.options().header("Frei belliebiger Text auswählbar");
        config.addDefault("gateplugin.command.INFO.messages.hello", "Hallo");
        config.addDefault("gateplugin.command.INFO.messages.loaded", "geladen");
        
        config.options().copyDefaults(true);
        this.saveConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player;
        if(sender instanceof Player) {
            player = (Player) sender;
        } else {
            player = getServer().getPlayer(sender.getName());
        }

        switch (cmd.getName()) {
            case "setPort":
                network.add(new Gate(player.getLocation(), network, player.getName()));
                getLogger().info("Setting port point! " + network.getGateByName(player.getName()));
                return true;

            case "port":
                if(network.getGateByName(player.getName()) == null) {

                    player.sendMessage("You do not have any port set yet.");
                }
                player.teleport(network.getGateByName(player.getName()).getLocation());
                return true;

            case "info":
                player.sendMessage(this.getConfig().getString("gateplugin.command.INFO.messages.hello") + " " + player.getDisplayName());
                player.sendMessage(this.getDescription().getName() + " " + this.getConfig().getString("gateplugin.command.INFO.messages.loaded"));
                return true;
        }

        return false;
    }
}
