package com.tw.game.armor;

public class Armor {
    private String name;
    private int defense;

    public Armor(String name, int defense) {
        this.name = name;
        this.defense = defense;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
}
