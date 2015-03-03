package com.tw.game.player;

import com.google.common.base.Optional;
import com.tw.game.armor.Armor;
import com.tw.game.weapon.MiddleWeapon;
import com.tw.game.weapon.ShortWeapon;
import com.tw.game.weapon.Weapon;

/**
 * 刺客只能装备 中 短 武器
 * */
public class Assassin extends NormalPlayer{
    private Armor armor;
    private Weapon weapon;

    public Assassin(String job, String name, int blood, int attack, Weapon weapon, Armor armor) {
        super(job, name, blood, attack);
        this.weapon = weapon;
        this.armor = armor;
    }

    public Assassin(String job, String name, int blood, int attack, MiddleWeapon weapon, Armor armor) {
        super(job, name, blood, attack);
        this.weapon = weapon;
        this.armor = armor;
    }

    public Assassin(String job, String name, int blood, int attack, ShortWeapon weapon, Armor armor) {
        super(job, name, blood, attack);
        this.weapon = weapon;
        this.armor = armor;
    }


    @Override
    public boolean hasWeapon() {
        return true;
    }

    @Override
    public boolean hasArmor() {
        return true;
    }

    @Override
    public Optional<Weapon> getWeapon() {
        return Optional.of(this.weapon);
    }

    @Override
    public Optional<Armor> getArmor() {
        return Optional.of(this.armor);
    }


}
