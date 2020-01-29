package com.liel.boardgame.board;

import com.liel.boardgame.Player;
import com.liel.boardgame.Utility;
import com.liel.boardgame.effects.concrete_effects.GoBackwards;
import com.liel.boardgame.effects.concrete_effects.GrantMoney;
import com.liel.boardgame.node.LinkedListNodeContainer;
import com.liel.boardgame.node.Node;
import com.liel.boardgame.node.NodeOrientation;
import com.liel.boardgame.node.Point;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SquareBoard implements Board {

    private final int WIDTH;
    private final int HEIGHT;
    private LinkedListNodeContainer nodes;

    public SquareBoard(int length) {
        WIDTH = length;
        HEIGHT = length;
        nodes = new LinkedListNodeContainer();
        arrangeNodes();
    }

    private boolean isCornerNode(Point point) {
        boolean a = point.equals(new Point(0, 0));
        boolean b = point.equals(new Point(WIDTH - 1, 0));
        boolean c = point.equals(new Point(WIDTH - 1, HEIGHT - 1));
        boolean d = point.equals(new Point(0, HEIGHT - 1));
        return a || b || c || d;
    }

    @Override
    public Node getNode(int x, int y) {
        Point check = new Point(x, y);
        Node node = nodes.getNodeByPoint(check);
        if (node == null) {
            return null;
        }
        NodeOrientation orientation = checkOrientation(check);
        return node;
    }

    private NodeOrientation checkOrientation(Point point) {
        if (isCornerNode(point)) {
            return NodeOrientation.CORNER;
        }
        int y = point.getY();
        if (y == 0 || y == HEIGHT - 1) {
            return NodeOrientation.STANDING;
        }
        return NodeOrientation.LAYING;

    }

    public void movePlayer(Player player, Point to) {
        this.placePlayer(player, nodes.getNodeByPoint(to));
    }


    @Override
    public Node placePlayer(Player player, Node where) {
        Point playersPoint = player.getCurrentPosition();
        Node playersNode = nodes.getNodeByPoint(playersPoint);
        playersNode.removePlayer(player);

            where.addPlayer(player);

        return playersNode;
    }

    @Override
    public Node advancePlayer(Player player, int steps) {
        Point playersPoint = player.getCurrentPosition();
        Node playersNode = nodes.getNodeByPoint(playersPoint);
        assert playersNode != null : " player is inside a null node";
        Node to = playersNode;
        for (int i = 0; i < steps; i++) {
            to = to.getNextNode();
            placePlayer(player, to);

        }
        return to;
    }

    @Override
    public Node retreatPlayer(Player player, int steps) {
        Point playersPoint = player.getCurrentPosition();
        Node playersNode = nodes.getNodeByPoint(playersPoint);
        assert playersNode != null : " player is inside a null node ";
        Node to = playersNode;
        for (int i = 0; i < steps; i++) {
            to = to.getPrevNode();
            placePlayer(player, to);
        }
        return to;
    }

    @Override
    public Node getBottomRight() {
        return nodes.getNodeByPoint(WIDTH - 1, HEIGHT - 1);
    }

    @Override
    public Node getBottomLeft() {
        return nodes.getNodeByPoint(0, HEIGHT - 1);
    }

    @Override
    public Node getTopRight() {
        return nodes.getNodeByPoint(WIDTH - 1, 0);
    }

    @Override
    public Node getTopLeft() {
        return nodes.getNodeByPoint(0, 0);
    }

    @Override
    public void arrangeNodes() {
        // down
        for (int i = WIDTH - 1; i >= 0; i--) {
            Point point = new Point(i, HEIGHT - 1);
            Node node = new Node(point, checkOrientation(point));
            nodes.addNext(node);
//            VisualNode node = NodeFactory.getNode(i,HEIGHT-1)
        }
//         left
        for (int i = HEIGHT - 2; i >= 0; i--) {
            Point point = new Point(0, i);
            Node node = new Node(point, checkOrientation(point));
            nodes.addNext(node);
        }
        // up
        for (int i = 0; i < WIDTH; i++) {
            Point point = new Point(i, 0);
            Node node = new Node(point, checkOrientation(point));
            node.setEffect(new GrantMoney("You got some money", new Random().nextInt(99)));
            node.getChildren().add(new Label("$"));
            nodes.addNext(node);
        }
        // right
        for (int i = 0; i < HEIGHT - 1; i++) {
            Point point = new Point(WIDTH - 1, i);
            Node node = new Node(point, checkOrientation(point));
            nodes.addNext(node);
        }
        Node goBackwards = nodes.getNodeByPoint(0,3);
        goBackwards.setEffect(new GoBackwards("You have done something bad so you must go back 3 tiles",this,3));
        goBackwards.getChildren().add(new Label("down"));

    }


    @Override
    public void printBoard() {
        for (int i = 0; i < HEIGHT; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < WIDTH; j++) {
                Point check = new Point(j, i);
                Node node = nodes.getNodeByPoint(check);
                if (node == null) {
                    System.out.print(Utility.EMPTY_ROOM);
                } else if (!node.getPlayersOnNode().isEmpty()) {
                    System.out.print(Utility.PLAYER_IN_ROOM);
                } else if (node.hasEffect()) {
                    System.out.print(Utility.EFFECT_IN_ROOM);
                } else {
                    System.out.print(Utility.EMPTY_ROOM);
                }
            }
            System.out.println();
        }
        System.out.println("\n board size: " + WIDTH + " X " + HEIGHT);
    }

    public static void main(String[] args) {
        SquareBoard squareBoard = new SquareBoard(9);
        squareBoard.arrangeNodes();
        List<Player> players = new ArrayList<>();
        Player liel = new Player("liel", 1);
        Player enemy = new Player("enemy", 2);
        players.add(liel);
//        players.add(enemy);

        for (Player player : players) {
            squareBoard.placePlayer(player, squareBoard.getBottomRight());
        }
        squareBoard.printBoard();

        final int TEST_MOVES = 30;
        for (int i = 0; i < TEST_MOVES; i++) {
            squareBoard.advancePlayer(liel, 2);
            squareBoard.printBoard();
            System.out.println(liel);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < TEST_MOVES; i++) {
            squareBoard.retreatPlayer(liel, 1);
            squareBoard.printBoard();
            System.out.println(liel);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
