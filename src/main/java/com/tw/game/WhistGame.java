package com.tw.game;

import com.tw.game.player.NormalPlayer;
import com.tw.game.weapon.WeaponFeature;

public class WhistGame {

    private String gameProcess = "";
    private String gameResult = "";
    private NormalPlayer player1;
    private NormalPlayer player2;
    private boolean changeAttacker = false;

    public WhistGame(NormalPlayer player1, NormalPlayer player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void startWhistGame() {

        while (player1.isAlive() && player2.isAlive()) {
            //攻击前判断自己有没有延时等伤害，若有，先掉血
            changeAttacker = carryOutPoisonAttack();
            if (!changeAttacker) {
                boolean attackValid = player1.commonAttack(player2);
                gameProcess += getCommonAttackOutput(player1, player2, attackValid);
            }

            NormalPlayer temp = player1;
            player1 = player2;
            player2 = temp;
        }


    }

    private boolean carryOutPoisonAttack() {
        changeAttacker = false;
        if (player1.isPoisoning()) {
            gameProcess += poisonSelfAttack(player1);
        }
        return changeAttacker;
    }

    private String poisonSelfAttack(NormalPlayer player1) {
        String poisonOutput = "";

        WeaponFeature poisonState = player1.getPoisonState();
        if (poisonState.getType().equals("damageDelay")) {
            poisonOutput = damageDelaySituation(player1, poisonOutput, poisonState);
        } else if (poisonState.getType().equals("eachTwoRoundNoAttack")) {
            poisonOutput = eachTwoRoundNoAttackSituation(player1, poisonOutput, poisonState);
        } else if (poisonState.getType().equals("twoRoundNoAttack")) {
            poisonOutput = twoRoundNoAttackSituation(player1, poisonOutput, poisonState);
        }

        return poisonOutput;
    }

    private String twoRoundNoAttackSituation(NormalPlayer player1, String poisonOutput, WeaponFeature poisonState) {
        if (poisonState.getPoisonRound() > 0) {
            poisonOutput += player1.getName() + "晕倒了,无法攻击,眩晕还剩" + (poisonState.getPoisonRound() - 1) + "轮\n";
            changeAttacker = true;
            poisonState.setPoisonRound(poisonState.getPoisonRound() - 1);
        } else {
            poisonState.setPoisonRound(2);
        }
        return poisonOutput;
    }

    private String eachTwoRoundNoAttackSituation(NormalPlayer player1, String poisonOutput, WeaponFeature poisonState) {
        if (poisonState.getPoisonRound() <= 0) {
            poisonOutput += player1.getName() + "冻得直哆嗦，没有击中\n";
            changeAttacker = true;
            poisonState.setPoisonRound(2);
        } else {
            poisonState.setPoisonRound(poisonState.getPoisonRound() - 1);
        }
        return poisonOutput;
    }

    private String damageDelaySituation(NormalPlayer player1, String poisonOutput, WeaponFeature poisonState) {
        player1.poisonAttack();
        if (!player1.isAlive()) {
            changeAttacker = true;
        }
        poisonOutput += player1.getName() + "受到" + poisonState.getAttack() + "点" + poisonState.getName() + "伤害,";
        poisonOutput += player1.getName() + "剩余生命：" + player1.getBlood() + "\n";
        return poisonOutput;
    }

    private String getCommonAttackOutput(NormalPlayer player1, NormalPlayer player2, boolean attackValid) {
        String output = "";
        if(attackValid){
            output += getCommonAttackProcessOutput(player1, player2);
            output += getCommonAttackResultString(player1, player2);
        }else{
            output += player1.getName()+"靠近了"+player2.getName()+"\n";
        }

        return output;
    }

    private int getAttackedBlood(NormalPlayer player1, NormalPlayer player2) {
        int attackedBlood;
        if (player2.hasArmor()) {
            attackedBlood = player1.getAttack()  - player2.getDefense();
        } else {
            attackedBlood = player1.getAttack();
        }

        if (player1.hasWeapon() && player1.getWeapon().get().hasFeature() && player1.getWeapon().get().getWeaponFeature().getType().equals("tripleDamage")) {
            attackedBlood *= 3;
        }
        return attackedBlood;
    }

    private String getCommonAttackResultString(NormalPlayer player1, NormalPlayer player2) {
        int attackedBlood = getAttackedBlood(player1, player2);
        String output = "";
        output += player2.getName() + "受到了" + attackedBlood + "点伤害,";
        if (player2.isPoisoning()) {
            output += player2.getName() + player2.getPoisonState().getResult() + "了,";
        }
        output += player2.getName() + "剩余生命：" + player2.getBlood() + "\n";
        return output;
    }

    private String getCommonAttackProcessOutput(NormalPlayer player1, NormalPlayer player2) {
        String output = "";
        if (player1.hasWeapon()) {
            output += player1.getJob() + player1.getName() + "用" + player1.getWeapon().get().getName() + "攻击了" + player2.getJob() + player2.getName() + ",";
        } else {
            output += player1.getJob() + player1.getName() + "攻击了" + player2.getJob() + player2.getName() + ",";
        }

        if (player1.hasWeapon() && player1.getWeapon().get().hasFeature() && player1.getWeapon().get().getWeaponFeature().getType().equals("tripleDamage")) {
            output += player1.getName() + "发动了" + player1.getWeapon().get().getWeaponFeature().getName() + ",";
        }
        return output;
    }

    public String getGameProcess() {
        return gameProcess;
    }

    public String getGameResult() {
        if(player1.isDead()){
            gameResult  = player1.getName()+"被打败了";
        } else {
            gameResult = player2.getName()+"被打败了";
        }

        return gameResult;
    }
}
