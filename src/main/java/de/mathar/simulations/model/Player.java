package de.mathar.simulations.model;

import org.springframework.data.annotation.Id;

import java.util.Map;

public class Player {

    @Id
    public String id;

    private boolean attacker;
    private Map<UnitName, Integer> units;

    public Player(Map<UnitName, Integer> units, boolean attacker) {
        this.units = units;
        this.attacker = attacker;
    }

    public boolean isAttacker() {
        return attacker;
    }

    public Map<UnitName, Integer> getUnits() {
        return units;
    }

}
