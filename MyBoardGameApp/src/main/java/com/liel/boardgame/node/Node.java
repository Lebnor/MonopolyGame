package com.liel.boardgame.node;

import com.liel.boardgame.Player;
import com.liel.boardgame.effects.BaseEffect;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

import static com.liel.boardgame.Main.*;

/**
 * a single square, or cell, of the game board.
 */
public class Node extends StackPane {


    private final Point point;
    private List<Player> playersOnNode;
    private BaseEffect effect;
    private Node nextNode;
    private Node prevNode;
    private Rectangle rectangle;
    private final int HEIGHT = (int) (MAIN_HEIGHT / 15);
    private final int WIDTH = HEIGHT;


    public Node(Point point, NodeOrientation orientation) {
        this.point = point;
        this.playersOnNode = new ArrayList<>();
        this.rectangle = new Rectangle();
        switch (orientation) {
            case CORNER:
                rectangle.setWidth(WIDTH * 2);
                rectangle.setHeight(HEIGHT * 2);
                break;
            case LAYING:
                rectangle.setWidth(WIDTH * 2);
                rectangle.setHeight(HEIGHT);
                break;
            case STANDING:
                rectangle.setWidth(WIDTH);
                rectangle.setHeight(HEIGHT * 2);
        }
        rectangle.setStrokeWidth(1);
        rectangle.setStroke(Color.BLACK);
        rectangle.setFill(Color.WHITESMOKE);
        getChildren().add(rectangle);
    }

    //    @Override
    public void putPlayer(Player player) {
        playersOnNode.add(player);
        player.setCurrentPosition(this.point);
    }

    //    @Override
    public void removePlayer(Player player) {
        playersOnNode.remove(player);
        getChildren().remove(player);
    }

    //    @Override
    public int getX() {
        return point.getX();
    }

    //    @Override
    public int getY() {
        return point.getY();
    }

    //    @Override
    public void addPlayer(Player player) {
        System.out.println(player + " moved ");
        this.playersOnNode.add(player);
        player.setCurrentPosition(this.point);
        getChildren().add(player);
        int playersOccupy = playersOnNode.size();
        player.setTranslateY(playersOccupy * 10);

        if (playersOccupy == 1) {
            player.setTranslateY(0);
            setAlignment(player, Pos.CENTER);
        }


//        }

    }

    //    @Override
    public List<Player> getPlayersOnNode() {
        return playersOnNode;
    }

    //    @Override
    public BaseEffect getBaseEffect() {
        return effect;
    }

    //    @Override
    public Node getNextNode() {
        return nextNode;
    }

    //    @Override
    public Node getPrevNode() {
        return prevNode;
    }

    //    @Override
    public Point getPoint() {
        return this.point;
    }

    //    @Override
    public void setEffect(BaseEffect effect) {
        this.effect = effect;
    }

    //    @Override
    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    //    @Override
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
