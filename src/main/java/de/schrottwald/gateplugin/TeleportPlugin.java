package de.schrottwald.gateplugin;

import de.schrottwald.gateplugin.gate.Gate;
import de.schrottwald.gateplugin.gate.GateNetwork;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
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

        new EventListener(this);
        network = new GateNetwork();

        super.onEnable();

        getLogger().info("onEnable has been invoked!");
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
                network.add(new Gate(player));
                sender.sendMessage("Setting port point! " + network.getGateByName(player.getName()));
                return true;

            case "port":
                if(network.getGateByName(player.getName()) == null) {

                    player.sendMessage("You do not have any port set yet.");
                }
                player.teleport(network.getGateByName(player.getName()).getLocation());
                return true;
        }

        return false;
    }
}
