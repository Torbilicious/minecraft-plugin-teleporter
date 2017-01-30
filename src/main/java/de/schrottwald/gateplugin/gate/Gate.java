package de.schrottwald.gateplugin.gate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.bukkit.Location;

@ToString
@AllArgsConstructor
public class Gate {

    @Getter
    private final Location location;
    @Getter
    private final GateNetwork network;
    @Getter
    private final String name;
//    @Getter
//    private final BlockLocations;
}
