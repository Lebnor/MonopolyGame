package com.liel.boardgame.node;

import com.liel.boardgame.Player;
import com.liel.boardgame.effects.BaseEffect;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.List;

/**
 * a single square, or cell, of the game board.
 */
public class Node extends StackPane {


    private final Point point;
    private List<Player> playersOnNode;
    private BaseEffect effect;
    private Node nextNode;
    private Node prevNode;
    //    private final int HEIGHT = (int) (MAIN_HEIGHT / 15);
//    private final int WIDTH = HEIGHT;
    private NodeRectangle rectangle;


    public Node(Point point, NodeOrientation orientation) {
        this.point = point;
        this.playersOnNode = new ArrayList<>();
        this.rectangle = new NodeRectangle(orientation);
        getChildren().add(rectangle);

        // for debugging
        this.setOnMouseClicked(event -> {
            System.out.println("\n" + this);
        });
    }

    public NodeRectangle getRectangle() {
        return this.rectangle;
    }

    public int getX() {
        return point.getX();
    }

    public int getY() {
        return point.getY();
    }

    public void removePlayer(Player player) {
        playersOnNode.remove(player);
        getChildren().remove(player);
    }

    public void putPlayer(Player player) {
        playersOnNode.add(player);
        player.setCurrentPosition(this.point);
        adjustSpacing(player);
    }

    public void addPlayer(Player player) {
        System.out.println(player + " moved ");
        this.playersOnNode.add(player);
        player.setCurrentPosition(this.point);
        getChildren().add(player);
        adjustSpacing(player);
        toFront();
    }

    public void adjustSpacing(Player player) {
        int playersOccupy = playersOnNode.size();
        player.setTranslateY(playersOccupy * 10);

        if (playersOccupy == 1) {
            player.setTranslateY(0);
            setAlignment(player, Pos.CENTER);
        }
    }


    public List<Player> getPlayersOnNode() {
        return playersOnNode;
    }

    public BaseEffect getBaseEffect() {
        return effect;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public Node getPrevNode() {
        return prevNode;
    }

    public Point getPoint() {
        return this.point;
    }

    public void setEffect(BaseEffect effect) {
        this.effect = effect;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    public void setPrevNode(Node prevNode) {
        this.prevNode = prevNode;
    }

    @Override
    public String toString() {
        return
                "x=" + getX() +
                        ", y=" + getY() +
                        ", playersOnNode=" + playersOnNode +
                        ", effect=" + effect +
                        '}';
    }

    //    @Override
    public boolean hasPlayers() {
        return !playersOnNode.isEmpty();
    }

    //    @Override
    public boolean hasEffect() {
        return effect != null;
    }


}
