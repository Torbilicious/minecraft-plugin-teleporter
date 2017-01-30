package de.schrottwald.gateplugin.gate;

import lombok.Getter;
import lombok.ToString;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@ToString
public class Gate {

    @Getter
    private final Location location;
    @Getter
    private final String name;

    public Gate(Player player) {

        this.location = player.getLocation();
        this.name = player.getName();
    }
}
