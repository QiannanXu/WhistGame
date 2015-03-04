package com.tw.game;

import com.tw.game.player.NormalPlayer;
import com.tw.game.weapon.WeaponFeature;

public class WhistGame {

    private String gameProcess = "";
    private NormalPlayer player1;
    private NormalPlayer player2;

    public WhistGame(NormalPlayer player1, NormalPlayer player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void startWhistGame() {

        while (player1.isAlive() && player2.isAlive()) {
            //攻击前判断自己有没有延时等伤害，若有，先掉血
            gameProcess += player1.carryOutPoisonAttack();

            if (!player1.whetherChangeAttacker()) {
                boolean attackValid = player1.commonAttack(player2);
                gameProcess += getCommonAttackOutput(player1, player2, attackValid);
            }

            NormalPlayer temp = player1;
            player1 = player2;
            player2 = temp;
        }


    }

    private String getCommonAttackOutput(NormalPlayer player1, NormalPlayer player2,  boolean attackValid) {
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
        String gameResult = "";
        if(player1.isDead()){
            gameResult = player1.getName()+"被打败了";
        } else {
            gameResult = player2.getName()+"被打败了";
        }

        return gameResult;
    }
}
