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

    public Assassin(String name, int blood, int attack, Weapon weapon, Armor armor) {
        super(name, blood, attack);
        this.job = "刺客";
        this.armor = armor;
        this.weapon = weapon;
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

    @Override
    public int getDefense() {
        return getArmor().get().getDefense();
    }

    @Override
    public String getWeaponExtraEffect(NormalPlayer player2) {
        if(weapon instanceof ShortWeapon){
            return weapon.extraEffect();
        }
        return "";
    }
}
