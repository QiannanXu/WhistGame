package com.tw.game.weapon;

public class MiddleWeapon extends Weapon{
    private final static int attackDistance = 1;

    public MiddleWeapon(String name, int attack) {
        super(name, attack);
    }

    public MiddleWeapon(String name, int attack, WeaponFeature weaponFeature) {
        super(name, attack, weaponFeature);
    }

    public static int getAttackDistance() {
        return attackDistance;
    }

    @Override
    public String extraEffect() {
        return "extraDefense";
    }
}
