package de.schrottwald.gateplugin.gate;

import java.util.ArrayList;
import java.util.List;

public class GateNetwork {

    private List<Gate> gates = new ArrayList<>();

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
