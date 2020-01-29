package com.liel.boardgame;

import com.liel.boardgame.effects.BaseEffect;
import com.liel.boardgame.node.Point;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player extends Circle {

    private String name;
    private List<BaseEffect> currentEffects;
    private Point currentPosition;
    private int playerID;
    private int money = 0;


    public Player(int ID) {
        this(Utility.getRandomName(),ID);
    }

    public Player(String name, int ID) {
        this.currentEffects = new ArrayList<>();
        this.name = name;
        this.playerID = ID;
        Color color = null;
        switch (playerID) {
            case 1:
                color = Color.BLUE;
                break;
            case 2:
                color = Color.RED;
                break;
            case 3:
                color = Color.GREEN;
                break;
            case 4:
                color = Color.YELLOW;
                break;
        }
        assert color != null : "must inititalize Player with proper id";
        setFill(color);
        setRadius(15);
    }
    public String getName(){
        return this.name;
    }
    public int getPlayerID(){
        return playerID;
    }

    public void addEffect(BaseEffect effect) {
        currentEffects.add(effect);
    }

    public void removeEffect(BaseEffect effect) {
        this.currentEffects.remove(effect);
    }

    public void setCurrentPosition(Point currentPosition) {
        this.currentPosition = currentPosition;
    }

    public Point getCurrentPosition() {
        return this.currentPosition;
    }

    public List<BaseEffect> getCurrentEffects() {
        return currentEffects;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", currentEffects=" + currentEffects +
                ", currentPosition=" + currentPosition +
                ", playerID=" + playerID +
                ", money=" + money +
                '}';
    }

    public Point getStartingPoint() {
        return new Point(0, 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return name.equals(player.name) &&
                currentPosition.equals(player.currentPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, currentPosition);
    }

    public void grantMoney(int howMuch) {
        this.money += howMuch;
        System.out.println(">>inside Player grantMoney(int howMuch)");
        System.out.println(this + " got $" + howMuch);
    }

    public String getMoney() {
        return "$" + this.money;
    }
}
