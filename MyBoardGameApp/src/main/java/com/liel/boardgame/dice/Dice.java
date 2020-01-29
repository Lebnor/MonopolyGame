package com.liel.boardgame.dice;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.Random;

public class Dice  {

    private Random random;
    int lastRoll = 0;

    public Dice(String text) {
        this.random = new Random();

    }

    public int roll() {
        int roll = random.nextInt(6)+1;
        lastRoll = roll;
        return roll;
    }
    public int getLastRoll(){
    return this.lastRoll;
    }


    public static void main(String[] args) {
        Dice dice = new Dice("");

        final int TRIES = 500000;

        int[] hits = new int[7];

        for (int i = 1; i < TRIES; i++) {
            int n = dice.roll();
//            System.out.println("Dice rolled " + n);
            hits[n]++;
        }
        for (int n : hits){
            System.out.println(n);
    }
}
}
