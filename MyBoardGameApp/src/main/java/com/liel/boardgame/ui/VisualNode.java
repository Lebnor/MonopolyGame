package com.liel.boardgame.ui;

import com.liel.boardgame.Player;
import com.liel.boardgame.effects.BaseEffect;
import com.liel.boardgame.node.GameNode;
import com.liel.boardgame.node.Node;
import com.liel.boardgame.node.NodeOrientation;
import com.liel.boardgame.node.Point;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;

import static com.liel.boardgame.Main.MAIN_HEIGHT;

/**
 * represents a visual cell of the game board on which the players are placed
 */
public class VisualNode extends StackPane implements GameNode {


    private NodeOrientation orientation;
    private GameNode node;
    private Rectangle rectangle;
    private final int HEIGHT = (int) (MAIN_HEIGHT / 15);
    private final int WIDTH = HEIGHT;

    /**
     * @param node        the data associated with this cell
     * @param orientation visual cue for which way should the cell face
     */
    public VisualNode(GameNode node, NodeOrientation orientation) {
        this.orientation = orientation;
        this.node = node;
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


    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    @Override
    public void putPlayer(Player player) {
        node.putPlayer(player);
        getChildren().add(this);
    }

    @Override
    public void removePlayer(Player player) {
        node.removePlayer(player);
    }

    @Override
    public int getX() {
        return node.getX();
    }


    @Override
    public int getY() {
        return node.getY();
    }

    @Override
    public void addPlayer(Player player) {
        node.addPlayer(player);
        getChildren().add(this);
    }

    @Override
    public List<Player> getPlayersOnNode() {
        return node.getPlayersOnNode();
    }

    @Override
    public BaseEffect getBaseEffect() {
        return node.getBaseEffect();
    }

    @Override
    public GameNode getNextNode() {
        return node.getNextNode();
    }

    @Override
    public GameNode getPrevNode() {
        return node.getPrevNode();
    }

    @Override
    public Point getPoint() {
        return node.getPoint();
    }

    @Override
    public void setEffect(BaseEffect effect) {
        node.setEffect(effect);
    }

    @Override
    public void setNextNode(GameNode nextNode) {
        node.setNextNode(nextNode);
    }

    @Override
    public void setPrevNode(GameNode prevNode) {
node.setNextNode(prevNode);
    }

    @Override
    public boolean hasPlayers() {
        return node.hasPlayers();
    }

    @Override
    public boolean hasEffect() {
        return node.hasEffect();
    }

    public GameNode getNode() {
        return this.node;
    }

    @Override
    public String toString() {
        return "VisualNode{" +
                "orientation=" + orientation +
                ", node=" + node +
                ", WIDTH=" + WIDTH +
                ", HEIGHT=" + HEIGHT +
                '}';
    }

    public static void main(String[] args) {


    }
}
