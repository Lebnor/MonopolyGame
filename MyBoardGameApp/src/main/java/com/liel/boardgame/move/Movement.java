package com.liel.boardgame.move;

import com.liel.boardgame.Player;
import com.liel.boardgame.board.Board;
import com.liel.boardgame.effects.BaseEffect;
import com.liel.boardgame.node.Node;
import com.liel.boardgame.node.Point;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.Iterator;

public abstract class Movement {


    public static void playAnimations(Iterator<TranslateTransition> iterator, Point oldPoint, Node oldNode, Board board, Player player, SimpleBooleanProperty booleanProperty) {

        TranslateTransition next = iterator.next();
        next.play();
        next.setOnFinished(event -> {
            if (iterator.hasNext()) {
                System.out.println("\n\nFinished animation");
                playAnimations(iterator, oldPoint, oldNode, board, player, booleanProperty);

            } else {
                board.getNode(oldPoint).removePlayer(player);
                oldNode.addPlayer(player);
                player.setTranslateY(0);
                player.setTranslateX(0);
                oldNode.adjustSpacing(player);
                BaseEffect effect = oldNode.getBaseEffect();
                if (effect != null) {
                    effect.applyEffect(player);
                }
                booleanProperty.set(true);
            }
        });

    }


//    public abstract int calculate();

    public abstract SimpleBooleanProperty execute();

    public abstract void undo();
}
