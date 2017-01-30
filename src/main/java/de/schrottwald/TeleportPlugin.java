package de.schrottwald;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Objects;

public final class TeleportPlugin extends JavaPlugin {

    private ArrayList<Gate> gates = new ArrayList<>();

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

        Player player;
        if(sender instanceof Player) {
            player = (Player) sender;
        } else {
            player = getServer().getPlayer(sender.getName());
        }

        switch (cmd.getName()) {
            case "setPort":
                gates.add(new Gate(player));
                sender.sendMessage("Setting port point! " + getGateByName(player.getName()));
                return true;

            case "port":
                if(gates.get(0) == null) {

                    player.sendMessage("You do not have any port set yet.");
                }
                player.teleport(getGateByName(player.getName()).getLocation());
                return true;

        }

        return false;
    }

    private Gate getGateByName(String name) {

        final Gate[] desiredGate = new Gate[1];
        gates.forEach(gate ->{
            if(Objects.equals(gate.getName(), name)){
                desiredGate[0] = gate;
            }
        } );

        return desiredGate[0];
    }
}
