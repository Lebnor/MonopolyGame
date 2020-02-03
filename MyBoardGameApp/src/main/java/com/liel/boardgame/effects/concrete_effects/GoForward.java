package com.liel.boardgame.effects.concrete_effects;

import com.liel.boardgame.Player;
import com.liel.boardgame.board.Board;
import com.liel.boardgame.effects.NegativeEffect;
import com.liel.boardgame.move.Move;
import com.liel.boardgame.move.Movement;
import javafx.beans.property.SimpleBooleanProperty;

public class GoForward extends NegativeEffect {

    private final int steps;
    private final Board board;

    public GoForward(String name, Board board, int steps) {
        super(name);
        this.steps = steps;
        this.board = board;
    }

    @Override
    public void applyEffect(Player player) {

        SimpleBooleanProperty endDelay = player.delay(100);
        endDelay.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                Movement move = new Move(board, player, steps, true);
                move.execute();
            }
        });
    }
}
