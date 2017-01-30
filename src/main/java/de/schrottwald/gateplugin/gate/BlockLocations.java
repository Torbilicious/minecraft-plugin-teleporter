package de.schrottwald.gateplugin.gate;

import lombok.Value;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

@Value
public class BlockLocations {

    private final List<Location> obsidianLocations = new ArrayList<>();
    private final Location signLocation;
    private final Location leverLocation;
}
