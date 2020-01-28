package com.liel.boardgame.effects;

import com.liel.boardgame.Player;

public abstract class BaseEffect {

    private String name;

    public BaseEffect(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void applyEffect(Player player);

    @Override
    public String toString() {
        return
                "Effect =" + name;
    }
}
