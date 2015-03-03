package com.tw.game.weapon;

public enum  WeaponFeature {
    Toxicity("毒性", "damageDelay", "中毒", 2, 1),
    Fire("火焰", "damageDelay", "着火", 2, 1),
    Icy("冰冻", "eachTwoRoundNoAttack", "冻僵", 2, 2),
    Faint("击晕", "twoRoundNoAttack", "晕倒", 2, 2),
    WholeAttack("全力一击", "tripleDamage", "被击倒", 2, 1);

    private String name;
    private String type;
    private String result;
    private int attack;
    private int round;

    private WeaponFeature(String name, String type, String result, int attack, int round) {
        this.name = name;
        this.type = type;
        this.result = result;
        this.attack = attack;
        this.round = round;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getPoisonRound() {
        return round;
    }

    public void setPoisonRound(int round) {
        this.round = round;
    }

    public void addPoisonRound() {
        this.round++;
    }
}
