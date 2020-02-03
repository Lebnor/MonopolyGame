package com.liel.boardgame.move;

import com.liel.boardgame.Player;
import com.liel.boardgame.board.Board;
import com.liel.boardgame.node.Node;
import com.liel.boardgame.node.NodeOrientation;
import com.liel.boardgame.node.Point;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.util.Duration;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Move extends Movement {

    private Board board;
    private Player player;
    private int steps;
    private boolean forward;

    public Move(Board board, Player player, int steps, boolean forward) {
        this.board = board;
        this.player = player;
        this.steps = steps;
        this.forward = forward;
    }

    /**
     * moves the player the amount of stpes given in the direction given
     *
     * @return SimpleBooleanProperty that sets to true when the animations are finished playing
     */
    @Override
    public SimpleBooleanProperty execute() {
        Point oldPoint = player.getCurrentPosition();
        Node oldNode = board.getNode(oldPoint);

        Queue<TranslateTransition> animation = new LinkedList<>();
        for (int i = 0; i < steps; i++) {
            TranslateTransition transition = new TranslateTransition(Duration.millis(300), player);

            int distance = oldNode.getRectangle().getTravelDistance();
            if (oldNode.getNextNode().getRectangle().getOrientation() == NodeOrientation.CORNER || oldNode.getRectangle().getOrientation() == NodeOrientation.CORNER) {
                distance *= 1.5;
                if (oldNode.getNextNode().getRectangle().getOrientation() == NodeOrientation.STANDING) {
                    distance /= 2;
                }
            }
            if (oldNode.getRectangle().getOrientation() == NodeOrientation.LAYING || oldNode.getNextNode().getRectangle().getOrientation() == NodeOrientation.LAYING) {
                distance /= 2;
            }
            if (oldNode.equals(board.getBottomLeft())) {
                player.setDirection(Direction.UP);
            } else if (oldNode.equals(board.getTopLeft())) {
                player.setDirection(Direction.RIGHT);
            } else if (oldNode.equals(board.getTopRight())) {
                player.setDirection(Direction.DOWN);
            } else if (oldNode.equals(board.getBottomRight())) {
                player.setDirection(Direction.LEFT);
            }


            switch (player.getDirection()) {
                case LEFT:
                    transition.setByX(-distance);
                    if (!forward) {
                        transition.setByX(distance);
                    }
                    break;
                case UP:
                    transition.setByY(-distance);
                    if (!forward) {
                        transition.setByY(distance);
                    }
                    break;
                case RIGHT:
                    transition.setByX(distance);
                    if (!forward) {
                        transition.setByX(-distance);
                    }
                    break;
                case DOWN:
                    transition.setByY(distance);
                    if (!forward) {
                        transition.setByY(-distance);
                    }
                    break;
            }
            animation.add(transition);
            if (forward) {
                oldNode = oldNode.getNextNode();
            } else {
                oldNode = oldNode.getPrevNode();

            }
            oldNode.adjustSpacing(player);
        }
        Iterator<TranslateTransition> iterator = animation.iterator();
        board.getNode(oldPoint).removePlayer(player);
        board.getNode(oldPoint).addPlayer(player);
        SimpleBooleanProperty animationFinished = new SimpleBooleanProperty(false);
        playAnimations(iterator, oldPoint, oldNode, board, player, animationFinished);
        return animationFinished;


    }

    private void swapDirection(Player player) {
        if (player.getDirection() == Direction.LEFT) {
            player.setDirection(Direction.RIGHT);
        } else if (player.getDirection() == Direction.RIGHT) {
            player.setDirection(Direction.LEFT);
        } else if (player.getDirection() == Direction.UP) {
            player.setDirection(Direction.DOWN);
        } else if (player.getDirection() == Direction.DOWN) {
            player.setDirection(Direction.UP);
        }
    }


    @Override
    public void undo() {
// todo
    }
}
