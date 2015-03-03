package com.tw.game.weapon;

import com.tw.game.player.Knight;
import com.tw.game.player.NormalPlayer;

public class LongWeapon extends Weapon{
    private final static int attackDistance = 2;

    public LongWeapon(String name, int attack) {
        super(name, attack);
    }

    public LongWeapon(String name, int attack, WeaponFeature weaponFeature) {
        super(name, attack, weaponFeature);
    }

    public static int getAttackDistance() {
        return attackDistance;
    }

    @Override
    public String extraEffect() {
        return "backward";
    }
}
