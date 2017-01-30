package de.schrottwald;

import lombok.Data;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@Data
public class Gate {

    private final Location location;
    private final String name;

    public Gate(Location location, String name) {

        this.location = location;
        this.name = name;
    }

    public Gate(Player player) {

        this.location = player.getLocation();
        this.name = player.getName();
    }

    public Location getLocation() {

        return location;
    }

    public String getName() {

        return name;
    }
}
