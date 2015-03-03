package com.tw.game.player;

import com.google.common.base.Optional;
import com.tw.game.armor.Armor;
import com.tw.game.weapon.WeaponFeature;
import com.tw.game.weapon.Weapon;

public class NormalPlayer implements Player {
    private String job;
    private String name;
    private int blood;
    private int attack;
    private boolean poisonFlag = false;
    private WeaponFeature poisonState;

    public NormalPlayer(String job, String name, int blood, int attack) {
        this.job = job;
        this.name = name;
        this.blood = blood;
        this.attack = attack;
    }

    public void commonAttack(NormalPlayer player2){
        dropBlood(player2);
        modifyPoisonState(player2);
    }

    private void modifyPoisonState(NormalPlayer player2) {
        if(this.hasWeapon() && this.getWeapon().get().hasFeature() && !player2.isPoisoning()){
           player2.poisonFlag = true;
           player2.poisonState = this.getWeapon().get().getWeaponFeature();
        }
    }

    @Override
    public void dropBlood(NormalPlayer player2) {
        if(player2 instanceof Solider){
            player2.blood = player2.blood - this.attack + player2.getArmor().get().getDefense();
        }else{
            player2.blood = player2.blood - this.attack;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public boolean isAlive() {
        return (this.blood > 0) ? true : false;
    }

    @Override
    public boolean hasWeapon(){
        return false;
    }

    @Override
    public boolean hasArmor(){
        return false;
    }

    @Override
    public Optional<Weapon> getWeapon() {
        return Optional.absent();
    }

    @Override
    public Optional<Armor> getArmor() {
        return Optional.absent();
    }

    public boolean isPoisoning() {
        return this.poisonFlag;
    }

    public void setPoisonState(boolean poisonFlag, WeaponFeature poisonState) {
        this.poisonFlag = poisonFlag;
        this.poisonState = poisonState;
    }

    public WeaponFeature getPoisonState(){
        return this.poisonState;
    }

    public void poisonAttack() {
        this.blood -= this.poisonState.getAttack();
    }
}
