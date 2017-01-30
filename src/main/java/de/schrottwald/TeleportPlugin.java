package de.schrottwald;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class TeleportPlugin extends JavaPlugin {

    private Map<Player, Location> ports = new HashMap<>();

    @Override
    public void onDisable() {

        super.onDisable();

        getLogger().info("onDisable has been invoked!");
    }

    @Override
    public void onEnable() {

        new EventListener(this);

        super.onEnable();

        getLogger().info("onEnable has been invoked!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = getServer().getPlayer(sender.getName());

        switch (cmd.getName()) {
            case "setPort":
                ports.put(player, player.getLocation());
                sender.sendMessage("Setting port point! " + ports.get(player));
                return true;

            case "port":
                if(ports.get(player) == null) {

                    player.sendMessage("You do not have any port set yet.");
                }
                player.teleport(ports.get(player));
                return true;

        }

        return false;
    }
}
