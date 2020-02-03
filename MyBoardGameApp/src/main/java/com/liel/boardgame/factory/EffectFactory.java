package com.liel.boardgame.factory;

import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.liel.boardgame.board.Board;
import com.liel.boardgame.effects.BaseEffect;
import com.liel.boardgame.effects.concrete_effects.GoForward;
import com.liel.boardgame.node.Node;
import com.liel.boardgame.node.Point;

public abstract class EffectFactory {


    // todo
    // you don't really need the board reference to make a move, change methods structure
    public static BaseEffect goForward(Board board, Node node, int steps) {
        BaseEffect effect = new GoForward("You have done something bad so you must go back 3 tiles", board, steps);
        javafx.scene.Node graphic = decideOrientation(node, board);
        node.getChildren().add(graphic);
        graphic.scaleXProperty().bind(node.widthProperty().divide(50));
        graphic.scaleYProperty().bind(node.widthProperty().divide(50));
        graphic.setStyle("-fx-text-fill: purple");

        return effect;
    }

    private static javafx.scene.Node decideOrientation(Node node, Board board) {
        Point point = node.getPoint();
        int x = point.getX();
        int y = point.getY();

        if (point.equals(board.getBottomLeft().getPoint())) {
            return MaterialDesignIcon.KEYBOARD_ARROW_UP.graphic();
        } else if (point.equals(board.getTopLeft().getPoint())) {
            return MaterialDesignIcon.KEYBOARD_ARROW_RIGHT.graphic();
        } else if (point.equals(board.getTopRight().getPoint())) {
            return MaterialDesignIcon.KEYBOARD_ARROW_RIGHT.graphic();
        } else if (point.equals(board.getBottomRight().getPoint())) {
            return MaterialDesignIcon.KEYBOARD_ARROW_LEFT.graphic();
        } else if (x == 0) {
            return MaterialDesignIcon.KEYBOARD_ARROW_UP.graphic();
        } else if (y == 0) {
            return MaterialDesignIcon.KEYBOARD_ARROW_RIGHT.graphic();
        } else if (x == board.getWidth() - 1) {
            return MaterialDesignIcon.KEYBOARD_ARROW_DOWN.graphic();
        } else if (y == board.getHeight() - 1) {
            return MaterialDesignIcon.KEYBOARD_ARROW_LEFT.graphic();
        }
        return null;
    }
}
