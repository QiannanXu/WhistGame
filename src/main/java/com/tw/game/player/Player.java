package com.tw.game.player;

import com.google.common.base.Optional;
import com.tw.game.armor.Armor;
import com.tw.game.weapon.Weapon;

public interface Player {
    public Optional<Weapon> getWeapon();
    public Optional<Armor> getArmor();
    public boolean hasWeapon();
    public boolean hasArmor();
    public void dropBlood(NormalPlayer player);
}
