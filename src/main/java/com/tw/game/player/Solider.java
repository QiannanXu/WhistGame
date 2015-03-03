package com.tw.game.player;

import com.google.common.base.Optional;
import com.tw.game.armor.Armor;
import com.tw.game.weapon.MiddleWeapon;
import com.tw.game.weapon.Weapon;

/**
 * 战士只能装备 中 武器
 * */
public class Solider extends NormalPlayer {
    private Weapon weapon;
    private Armor armor;

    public Solider(String job, String name, int blood, int attack, Weapon weapon, Armor armor) {
        super(job, name, blood, attack);
        this.weapon = weapon;
        this.armor = armor;
    }

    public Solider(String job, String name, int blood, int attack, MiddleWeapon weapon, Armor armor) {
        super(job, name, blood, attack);
        this.weapon = weapon;
        this.armor = armor;
    }

    @Override
    public void dropBlood(NormalPlayer player2) {
        int dropBlood;
        if(player2 instanceof Solider){
            dropBlood =  this.getWeapon().get().getAttack() + this.getAttack() - player2.getArmor().get().getDefense();
        }else{
            dropBlood = this.getWeapon().get().getAttack() + this.getAttack();
        }

        if(weapon.hasFeature() && weapon.getWeaponFeature().getType().equals("tripleDamage")){
            dropBlood *= 3;
        }

        player2.setBlood(player2.getBlood() - dropBlood);
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
