package com.liel.boardgame.effects.concrete_effects;

import com.liel.boardgame.Player;
import com.liel.boardgame.effects.PositiveEffect;

public class GrantMoney extends PositiveEffect {

    private int howMuch;

    public GrantMoney(String name, int howMuch) {
        super(name);
        this.howMuch = howMuch;
    }
    @Override
    public void applyEffect(Player player) {
        player.grantMoney(howMuch);
    }
}
