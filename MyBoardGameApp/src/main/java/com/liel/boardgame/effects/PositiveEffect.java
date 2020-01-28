package com.liel.boardgame.effects;

import com.liel.boardgame.Player;

public abstract class PositiveEffect extends BaseEffect {


    public PositiveEffect(String name) {
        super(name);
    }

    public abstract void applyEffect(Player player);

}
