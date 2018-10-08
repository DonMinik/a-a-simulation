package de.mathar.simulations.model;

import org.springframework.data.annotation.Id;

public class Unit {

    @Id
    public String id;



    private UnitName name;
    private int value;
    private int attack;
    private int defense;

    public Unit(UnitName name, int value, int attack, int defense) {
        this.name = name;
        this.value = value;
        this.attack = attack;
        this.defense = defense;
    }

    @Override
    public String toString() {
        return String.format(
                "Unit [id=%s, name='%s', value='%s', attack='%s', defense='%s']",
                id, name, value, attack, defense);
    }

    public UnitName getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }
}
