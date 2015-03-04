package com.tw.game.player;

import com.google.common.base.Optional;
import com.tw.game.armor.Armor;
import com.tw.game.weapon.WeaponFeature;
import com.tw.game.weapon.Weapon;

public class NormalPlayer implements Player {
    private final static int attackScope = 2;

    protected String job;
    protected String name;
    protected int blood;
    protected int defense;

    protected int attack;
    private boolean poisonFlag = false;
    private WeaponFeature poisonState;
    private int distance;
    private boolean changeAttacker = false;

    public NormalPlayer(String name, int blood, int attack) {
        this.job = "普通人";
        this.distance = 1;
        this.defense = 0;
        this.name = name;
        this.blood = blood;
        this.attack = attack;
    }
    public NormalPlayer(String name, int blood, int attack, int distance) {
        this(name, blood, attack);
        this.distance = distance;
    }

    public boolean commonAttack(NormalPlayer player2){
        //不在攻击范围内
        boolean attackValid;
        if(this.distance + player2.distance > attackScope ){
            forward(1);
            attackValid = false;
        }else{
            //攻击, 掉血，中毒，武器的extraEffect
            dropBlood(player2);
            carryoutWeaponExtraEffect(player2);
            makePoisoned(player2);
            attackValid = true;
        }
        return attackValid;
    }

    private void carryoutWeaponExtraEffect(NormalPlayer player2) {
        if(getWeaponExtraEffect().equals("backward")){
            player2.backward(1);
        }else if(getWeaponExtraEffect().equals("doubleAttack")){
            dropBlood(player2);
        }else if(getWeaponExtraEffect().equals("extraDefense")){
            addDefense();
        }
    }


    private void makePoisoned(NormalPlayer player2) {
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

    @Override
    public String getWeaponExtraEffect() {
        return "";
    }

    @Override
    public void addDefense() {
        defense++;
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
        return this.blood > 0;
    }

    public boolean isDead(){
        return this.blood <= 0 ;
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

    public String poisonSelfAttack() {
        String poisonOutput = "";

        WeaponFeature poisonState = getPoisonState();
        if (poisonState.getType().equals("damageDelay")) {
            poisonOutput = damageDelaySituation();
        } else if (poisonState.getType().equals("eachTwoRoundNoAttack")) {
            poisonOutput = eachTwoRoundNoAttackSituation();
        } else if (poisonState.getType().equals("twoRoundNoAttack")) {
            poisonOutput = twoRoundNoAttackSituation();
        }

        return poisonOutput;
    }

    private String twoRoundNoAttackSituation() {
        WeaponFeature poisonState = getPoisonState();
        String poisonOutput = "";

        if (poisonState.getPoisonRound() > 0) {
            poisonOutput += getName() + "晕倒了,无法攻击,眩晕还剩" + (poisonState.getPoisonRound() - 1) + "轮\n";
            changeAttacker = true;
            poisonState.setPoisonRound(poisonState.getPoisonRound() - 1);
        } else {
            poisonState.setPoisonRound(2);
            changeAttacker = false;
        }
        return poisonOutput;
    }

    private String eachTwoRoundNoAttackSituation() {
        WeaponFeature poisonState = getPoisonState();
        String poisonOutput = "";

        if (poisonState.getPoisonRound() <= 0) {
            poisonOutput += getName() + "冻得直哆嗦，没有击中\n";
            changeAttacker = true;
            poisonState.setPoisonRound(2);
        } else {
            poisonState.setPoisonRound(poisonState.getPoisonRound() - 1);
            changeAttacker = false;
        }
        return poisonOutput;
    }

    private String damageDelaySituation() {
        WeaponFeature poisonState = getPoisonState();

        String poisonOutput = "";
        poisonAttack();
        if (isDead()) {
            changeAttacker = true;
        }
        poisonOutput += getName() + "受到" + poisonState.getAttack() + "点" + poisonState.getName() + "伤害,";
        poisonOutput += getName() + "剩余生命：" + getBlood() + "\n";
        return poisonOutput;
    }


    public boolean whetherChangeAttacker() {
        return changeAttacker;
    }

    public String carryOutPoisonAttack() {
        changeAttacker = false;
        String gameProcess = "";
        if (isPoisoning()) {
            gameProcess += poisonSelfAttack();
            changeAttacker = whetherChangeAttacker();
        }
        return gameProcess;
    }

}
