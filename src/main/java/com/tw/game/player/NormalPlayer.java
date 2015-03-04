package com.tw.game.player;

import com.google.common.base.Optional;
import com.tw.game.armor.Armor;
import com.tw.game.weapon.WeaponFeature;
import com.tw.game.weapon.Weapon;

public class NormalPlayer implements Player {
    protected String job;
    protected String name;
    protected int blood;
    protected int attack;

    private boolean poisonFlag = false;
    private boolean attackValid;
    private WeaponFeature poisonState;
    private int distance;
    private final static int attackScope = 2;

    public NormalPlayer(String name, int blood, int attack) {
        this.job = "普通人";
        this.distance = 1;
        this.name = name;
        this.blood = blood;
        this.attack = attack;
    }

    public NormalPlayer(String job, String name, int blood, int attack, int distance){
        this.job = job;
        this.name = name;
        this.blood = blood;
        this.attack = attack;
        this.distance = distance;
    }

    public boolean commonAttack(NormalPlayer player2){
        //不在攻击范围内
        if(this.distance + player2.distance > attackScope ){
            forward(1);
            attackValid = false;
        }else{
            dropBlood(player2);
            modifyPoisonState(player2);
            attackValid = true;
        }
        return attackValid;
    }

    private void modifyPoisonState(NormalPlayer player2) {
        if(this.hasWeapon() && this.getWeapon().get().hasFeature() && !player2.isPoisoning()){
           player2.poisonFlag = true;
           player2.poisonState = this.getWeapon().get().getWeaponFeature();
        }
    }

    @Override
    public void dropBlood(NormalPlayer player2) {
        int droppedBlood;
        if(player2 instanceof Solider){
            droppedBlood = this.attack - player2.getDefense();
        }else{
            droppedBlood = this.attack;
        }

        player2.blood -= droppedBlood;
    }

    @Override
    public void forward(int distance) {
        this.distance -= distance;
    }

    @Override
    public void backward(int distance) {
        this.distance += distance;
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
    public int getAttack() {
        return attack;
    }

    @Override
    public int getDefense() {
        return 0;
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
