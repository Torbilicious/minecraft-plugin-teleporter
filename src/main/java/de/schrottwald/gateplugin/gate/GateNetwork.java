package de.schrottwald.gateplugin.gate;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
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

    public Gate getGateByName(String name) {

        for (Gate gate: gates) {
            if(gate.getName().equals(name)){
                return gate;
            }
        }

        return null;
    }
}
