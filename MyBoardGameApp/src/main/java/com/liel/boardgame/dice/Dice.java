package com.liel.boardgame.dice;

import java.util.Random;

public class Dice {

    private Random random;

    public Dice() {
        this.random = new Random();
    }

    public int roll() {
        return random.nextInt(6)+1;
    }


    public static void main(String[] args) {
        Dice dice = new Dice();

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
