package com.tw.game.weapon;

import com.tw.game.player.NormalPlayer;

public class Weapon {
    private String name;
    private int attack;
    private WeaponFeature weaponFeature;

    public Weapon(String name, int attack) {
        this.name = name;
        this.attack = attack;
    }

    public Weapon(String name, int attack, WeaponFeature weaponFeature){
        this.name = name;
        this.attack = attack;
        this.weaponFeature = weaponFeature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public WeaponFeature getWeaponFeature() {
        return weaponFeature;
    }

    public void setWeaponFeature(WeaponFeature weaponFeature) {
        this.weaponFeature = weaponFeature;
    }

    public boolean hasFeature() {
        return (weaponFeature != null) ? true : false;
    }

    public String extraEffect(){
        return "none";
    }

}
