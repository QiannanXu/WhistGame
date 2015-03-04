package com.tw.game;

import com.tw.game.armor.Armor;
import com.tw.game.player.Assassin;
import com.tw.game.player.NormalPlayer;
import com.tw.game.player.Solider;
import com.tw.game.weapon.*;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class WhistGameTest {
    private WhistGame whistGame;

    @Test
    public void shouldPrintLiSiIsDefeated() {
        NormalPlayer player1 = new NormalPlayer("张三", 1, 1);
        NormalPlayer player2 = new NormalPlayer("李四", 1, 1);
        whistGame = new WhistGame(player1, player2);

        whistGame.startWhistGame();
        String gameProcess = whistGame.getGameProcess();
        assertThat(gameProcess, is("普通人张三攻击了普通人李四,李四受到了1点伤害,李四剩余生命：0\n"));

        String gameResult = whistGame.getGameResult();
        assertThat(gameResult, is("李四被打败了"));
    }

    @Test
    public void shouldPrintCorrectGameProcessWhenPlayerFightsWithASolider() {
        Weapon weapon = new Weapon("优质木棒", 1);
        Armor armor = new Armor("护具", 1);
        NormalPlayer player1 = new NormalPlayer("张三", 10, 8);
        NormalPlayer player2 = new Solider("李四", 20, 2, weapon, armor);

        whistGame = new WhistGame(player1, player2);
        whistGame.startWhistGame();
        String gameProcess = whistGame.getGameProcess();
        assertThat(gameProcess, is("普通人张三攻击了战士李四,李四受到了7点伤害,李四剩余生命：13\n" +
                "战士李四用优质木棒攻击了普通人张三,张三受到了3点伤害,张三剩余生命：7\n" +
                "普通人张三攻击了战士李四,李四受到了7点伤害,李四剩余生命：6\n" +
                "战士李四用优质木棒攻击了普通人张三,张三受到了3点伤害,张三剩余生命：4\n" +
                "普通人张三攻击了战士李四,李四受到了7点伤害,李四剩余生命：-1\n"));

    }

    @Test
    public void shouldPrintCorrectGameProcessWhenWeaponHasToxicityDamage() {
        WeaponFeature weaponFeature = WeaponFeature.Toxicity;
        Weapon weapon = new Weapon("优质毒剑", 1, weaponFeature);
        Armor armor = new Armor("护具", 1);
        NormalPlayer player1 = new NormalPlayer("张三", 10, 8);
        NormalPlayer player2 = new Solider("李四", 20, 2, weapon, armor);

        whistGame = new WhistGame(player1, player2);
        whistGame.startWhistGame();
        String gameProcess = whistGame.getGameProcess();
        assertThat(gameProcess, is("普通人张三攻击了战士李四,李四受到了7点伤害,李四剩余生命：13\n" +
                "战士李四用优质毒剑攻击了普通人张三,张三受到了3点伤害,张三中毒了,张三剩余生命：7\n" +
                "张三受到2点毒性伤害,张三剩余生命：5\n" +
                "普通人张三攻击了战士李四,李四受到了7点伤害,李四剩余生命：6\n" +
                "战士李四用优质毒剑攻击了普通人张三,张三受到了3点伤害,张三中毒了,张三剩余生命：2\n" +
                "张三受到2点毒性伤害,张三剩余生命：0\n"));
        String gameResult = whistGame.getGameResult();
        assertThat(gameResult, is("张三被打败了"));

    }

    @Test
    public void shouldPrintCorrectGameProcessWhenWeaponHasFireDamage() {
        WeaponFeature weaponFeature = WeaponFeature.Fire;
        Weapon weapon = new Weapon("火焰剑", 2, weaponFeature);
        Armor armor = new Armor("护具", 1);
        NormalPlayer player1 = new NormalPlayer("张三", 11, 5);
        NormalPlayer player2 = new Solider("李四", 13, 2, weapon, armor);

        whistGame = new WhistGame(player1, player2);
        whistGame.startWhistGame();
        String gameProcess = whistGame.getGameProcess();
        assertThat(gameProcess, is("普通人张三攻击了战士李四,李四受到了4点伤害,李四剩余生命：9\n" +
                "战士李四用火焰剑攻击了普通人张三,张三受到了4点伤害,张三着火了,张三剩余生命：7\n" +
                "张三受到2点火焰伤害,张三剩余生命：5\n" +
                "普通人张三攻击了战士李四,李四受到了4点伤害,李四剩余生命：5\n" +
                "战士李四用火焰剑攻击了普通人张三,张三受到了4点伤害,张三着火了,张三剩余生命：1\n" +
                "张三受到2点火焰伤害,张三剩余生命：-1\n"));
        String gameResult = whistGame.getGameResult();
        assertThat(gameResult, is("张三被打败了"));

    }

    @Test
    public void shouldPrintCorrectGameProcessWhenWeaponHasIcyDamage() {
        WeaponFeature weaponFeature = WeaponFeature.Icy;
        Weapon weapon = new Weapon("寒冰剑", 2, weaponFeature);
        Armor armor = new Armor("护具", 1);
        NormalPlayer player1 = new NormalPlayer("张三", 17, 5);
        NormalPlayer player2 = new Solider("李四", 13, 2, weapon, armor);

        whistGame = new WhistGame(player1, player2);
        whistGame.startWhistGame();
        String gameProcess = whistGame.getGameProcess();
        assertThat(gameProcess, is("普通人张三攻击了战士李四,李四受到了4点伤害,李四剩余生命：9\n" +
                "战士李四用寒冰剑攻击了普通人张三,张三受到了4点伤害,张三冻僵了,张三剩余生命：13\n" +
                "普通人张三攻击了战士李四,李四受到了4点伤害,李四剩余生命：5\n" +
                "战士李四用寒冰剑攻击了普通人张三,张三受到了4点伤害,张三冻僵了,张三剩余生命：9\n" +
                "普通人张三攻击了战士李四,李四受到了4点伤害,李四剩余生命：1\n" +
                "战士李四用寒冰剑攻击了普通人张三,张三受到了4点伤害,张三冻僵了,张三剩余生命：5\n" +
                "张三冻得直哆嗦，没有击中\n" +
                "战士李四用寒冰剑攻击了普通人张三,张三受到了4点伤害,张三冻僵了,张三剩余生命：1\n" +
                "普通人张三攻击了战士李四,李四受到了4点伤害,李四剩余生命：-3\n"));
        String gameResult = whistGame.getGameResult();
        assertThat(gameResult, is("李四被打败了"));

    }

    @Test
    public void shouldPrintCorrectGameProcessWhenWeaponHasFaintDamage() {
        WeaponFeature weaponFeature = WeaponFeature.Faint;
        Weapon weapon = new Weapon("晕锤", 2, weaponFeature);
        Armor armor = new Armor("护具", 1);
        NormalPlayer player1 = new NormalPlayer("张三", 17, 5);
        NormalPlayer player2 = new Solider("李四", 13, 2, weapon, armor);

        whistGame = new WhistGame(player1, player2);
        whistGame.startWhistGame();
        String gameProcess = whistGame.getGameProcess();
        assertThat(gameProcess, is("普通人张三攻击了战士李四,李四受到了4点伤害,李四剩余生命：9\n" +
                "战士李四用晕锤攻击了普通人张三,张三受到了4点伤害,张三晕倒了,张三剩余生命：13\n" +
                "张三晕倒了,无法攻击,眩晕还剩1轮\n" + "" +
                "战士李四用晕锤攻击了普通人张三,张三受到了4点伤害,张三晕倒了,张三剩余生命：9\n" +
                "张三晕倒了,无法攻击,眩晕还剩0轮\n" +
                "战士李四用晕锤攻击了普通人张三,张三受到了4点伤害,张三晕倒了,张三剩余生命：5\n" +
                "普通人张三攻击了战士李四,李四受到了4点伤害,李四剩余生命：5\n" +
                "战士李四用晕锤攻击了普通人张三,张三受到了4点伤害,张三晕倒了,张三剩余生命：1\n" +
                "张三晕倒了,无法攻击,眩晕还剩1轮\n" +
                "战士李四用晕锤攻击了普通人张三,张三受到了4点伤害,张三晕倒了,张三剩余生命：-3\n"));
        String gameResult = whistGame.getGameResult();
        assertThat(gameResult, is("张三被打败了"));

    }

    @Test
    public void shouldPrintCorrectGameProcessWhenWeaponHasWholeAttackDamage() {
        WeaponFeature weaponFeature = WeaponFeature.WholeAttack;
        Weapon weapon = new Weapon("利剑", 2, weaponFeature);
        Armor armor = new Armor("护具", 1);

        NormalPlayer player1 = new Solider("李四", 13, 2, weapon, armor);
        NormalPlayer player2 = new NormalPlayer("张三", 17, 5);
        whistGame = new WhistGame(player1, player2);
        whistGame.startWhistGame();
        String gameProcess = whistGame.getGameProcess();
        assertThat(gameProcess, is("战士李四用利剑攻击了普通人张三,李四发动了全力一击,张三受到了12点伤害,张三被击倒了,张三剩余生命：5\n" +
                "普通人张三攻击了战士李四,李四受到了4点伤害,李四剩余生命：9\n" +
                "战士李四用利剑攻击了普通人张三,李四发动了全力一击,张三受到了12点伤害,张三被击倒了,张三剩余生命：-7\n"));
        String gameResult = whistGame.getGameResult();
        assertThat(gameResult, is("张三被打败了"));

    }

    @Test
    public void shouldForwardWhenTwoPlayersAreOutOfAttackScope(){
        NormalPlayer player1 = new NormalPlayer("普通人", "张三", 3, 1, 2);
        NormalPlayer player2 = new NormalPlayer("普通人", "李四", 3, 1, 2);

        whistGame = new WhistGame(player1, player2);
        whistGame.startWhistGame();
        String gameProcess = whistGame.getGameProcess();
        assertThat(gameProcess, is("张三靠近了李四\n"+
                "李四靠近了张三\n"+
                "普通人张三攻击了普通人李四,李四受到了1点伤害,李四剩余生命：2\n"+
                "普通人李四攻击了普通人张三,张三受到了1点伤害,张三剩余生命：2\n"+
                "普通人张三攻击了普通人李四,李四受到了1点伤害,李四剩余生命：1\n"+
                "普通人李四攻击了普通人张三,张三受到了1点伤害,张三剩余生命：1\n"+
                "普通人张三攻击了普通人李四,李四受到了1点伤害,李四剩余生命：0\n"));
    }

    @Test
    public void should(){
        ShortWeapon shortWeapon = new ShortWeapon("短武器", 1);
        MiddleWeapon middleWeapon = new MiddleWeapon("中武器", 1);
        LongWeapon longWeapon = new LongWeapon("长武器", 1);
    }
}
