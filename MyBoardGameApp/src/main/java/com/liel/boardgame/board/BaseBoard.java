package com.liel.boardgame.board;

import com.liel.boardgame.Player;
import com.liel.boardgame.node.GameNode;
import com.liel.boardgame.node.LinkedListNodeContainer;
import com.liel.boardgame.node.Node;

import java.util.List;

public abstract class BaseBoard {

    private final int WIDTH;
    private final int HEIGHT;

    private LinkedListNodeContainer nodes;

    public BaseBoard(int width, int height) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.nodes = new LinkedListNodeContainer();
    }
    public GameNode getBottomRight() {
        return nodes.getNodeByPoint(WIDTH - 1, HEIGHT - 1);
    }

    public GameNode getBottomLeft() {
        return nodes.getNodeByPoint(0, HEIGHT - 1);
    }
    public GameNode getTopRight() {
        return nodes.getNodeByPoint(WIDTH - 1, 0);
    }

    public GameNode getTopLeft() {
        return nodes.getNodeByPoint(0, 0);
    }
    public abstract void arrangeNodes();

    public abstract void printBoard();

    public abstract Node advancePlayer(Player player, int steps);

    public abstract Node retreatPlayer(Player player, int steps);

}
