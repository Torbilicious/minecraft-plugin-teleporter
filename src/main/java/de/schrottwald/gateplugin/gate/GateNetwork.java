package de.schrottwald.gateplugin.gate;

import lombok.Getter;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class GateNetwork {

    private List<Gate> gates = new ArrayList<>();

    @Getter
    private final String networkName;

    public GateNetwork(String networkName) {
        this.networkName = networkName;
    }

    public Gate getGateWithId(Integer id) {

        return gates.get(id);
    }

    public void add(Gate gate) {

        gates.add(gate);
    }

    public void addNewGate(Location location, String name) {

        gates.add(new Gate(location, this, name));
    }

    public Gate getGateByName(String name) {

        for (Gate gate: gates) {
            if(gate.getName().equals(name)){
                return gate;
            }
        }

        return null;
    }

    public Integer getIndexOfGate(Gate gate) {

        return gates.lastIndexOf(gate);
    }

    public Integer getIndexOfGate(String gate) {

        int index = 0;
        for (int i = 0; i < gates.size(); i++) {

            if(gates.get(i).getName().equals(gate)) {
                index = i;
            }
        }

        return index;
    }

    public Gate getNextGate(Gate gate) {

        Integer index = gates.lastIndexOf(gate);
        if (index == -1) {

            return null;
        } else if (index == gates.size()-1) {
            return gates.get(0);
        } else {
            return gates.get(index + 1);
        }
    }

    public Integer getNumberOfGates() {

        return gates.size();
    }

    public void printAllGates() {

        gates.forEach(System.out::println);
    }
}
