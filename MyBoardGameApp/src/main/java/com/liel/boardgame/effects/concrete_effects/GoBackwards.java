package com.liel.boardgame.effects.concrete_effects;

import com.liel.boardgame.Player;
import com.liel.boardgame.board.Board;
import com.liel.boardgame.effects.NegativeEffect;

public class GoBackwards extends NegativeEffect {

    private final int steps;
    private final Board board;

    public GoBackwards(String name, Board board, int steps) {
        super(name);
        this.steps = steps;
        this.board = board;
    }

    @Override
    public void applyEffect(Player player) {
        board.retreatPlayer(player, steps);
    }
}
