package com.liel.boardgame.node;

import com.liel.boardgame.Player;
import com.liel.boardgame.effects.BaseEffect;

import java.util.ArrayList;
import java.util.List;

/**
 * a single square, or cell, of the game board.
 */
public class Node  implements GameNode{


    private final Point point;
    private List<Player> playersOnNode;
    private BaseEffect effect;
    private GameNode nextNode;
    private GameNode prevNode;


    public Node(Point point) {
        this.point = point;
        this.playersOnNode = new ArrayList<>();
    }

    @Override
    public void putPlayer(Player player) {
        playersOnNode.add(player);
        player.setCurrentPosition(this.point);
    }
    @Override
    public void removePlayer(Player player) {
        playersOnNode.remove(player);

    }
    @Override
    public int getX() {
        return point.getX();
    }
    @Override
    public int getY() {
        return point.getY();
    }
    @Override
    public void addPlayer(Player player) {
        this.playersOnNode.add(player);
        player.setCurrentPosition(this.point);
    }
    @Override
    public List<Player> getPlayersOnNode() {
        return playersOnNode;
    }
    @Override
    public BaseEffect getBaseEffect() {
        return effect;
    }
    @Override
    public GameNode getNextNode() {
        return nextNode;
    }
    @Override
    public GameNode getPrevNode() {
        return prevNode;
    }
    @Override
    public Point getPoint() {
        return this.point;
    }
    @Override
    public void setEffect(BaseEffect effect) {
        this.effect = effect;
    }
    @Override
    public void setNextNode(GameNode nextNode) {
        this.nextNode = nextNode;
    }
    @Override
    public void setPrevNode(GameNode prevNode) {
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
    @Override
    public boolean hasPlayers() {
        return !playersOnNode.isEmpty();
    }
    @Override
    public boolean hasEffect() {
        return effect != null;
    }


}
