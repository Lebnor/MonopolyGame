package com.liel.boardgame.effects;

import com.liel.boardgame.Player;

public abstract class NegativeEffect extends BaseEffect {

    public NegativeEffect(String name) {
        super(name);
    }

    public abstract void applyEffect(Player player);
}
