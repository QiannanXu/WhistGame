package com.tw.game.weapon;

public class MiddleWeapon extends Weapon{
    public MiddleWeapon(String name, int attack) {
        super(name, attack);
    }

    public MiddleWeapon(String name, int attack, WeaponFeature weaponFeature) {
        super(name, attack, weaponFeature);
    }
}
