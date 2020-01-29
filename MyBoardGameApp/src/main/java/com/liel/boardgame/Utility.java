package com.liel.boardgame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Utility {

    public static final String EMPTY_ROOM = "[ ] ";
    public static final String PLAYER_IN_ROOM = "[*] ";
    public static final String EFFECT_IN_ROOM = "[e] ";

    private static final String[] names = {"Sophia", "Liam", "Olivia", "Jackson", "Emma	    ", "Noah", "Ava	    ", "Aiden", "Aria   	", "Grayson", "Isabella	", "Caden", "Amelia	    ", "Lucas", "Mia	    ", "Elijah", "Riley	    ", "Oliver", "Aaliyah	", "Muhammad", "Layla	    ", "Mason", "Zoe	    ", "Carter", "Charlotte	", "Mateo", "Mila	    ", "Jayden", "Harper	    ", "Ethan", "Evelyn	    ", "Logan", "Camilla	", "Sebastian", "Eliana	    ", "James", "Chloe	    ", "Alexander", "Lily	    ", "Benjamin", "Avery	    ", "Jacob", "Luna	    ", "Leo", "Ella	    ", "Josiah", "Adalyn	    ", "Daniel", "Everly	    ", "Julian", "Leah	    ", "Michael", "Scarlett	", "Jack", "Aubrey 	", "Ryan", "Emily	    ", "Levi", "Maya	    ", "Jayce", "Nora	    ", "Wyatt", "Ellie	    ", "Luke", "Madison	", "Owen", "Abigail	", "Henry", "Kinsley	", "Caleb", "Isla	    ", "Gabriel", "Hannah	    ", "William", "Arianna	", "Isaiah", "Madelyn	", "Isaac", "Paisley	", "David", "Elena	    ", "Matthew", "Sarah	    ", "John", "Charlie	", "Luca", "Brooklyn	", "Lincoln", "Penelope	", "Asher", "Hailey	    ", "Cameron", "Lila	    ", "Ezra", "Callie	    ", "Nicholas", "Grace	    ", "Connor", "Emery	    ", "Colton"};
    private static final Random random = new Random();


    public static String getRandomName() {
        int max = names.length - 1;
        int randomNum = random.nextInt(max);
        return names[randomNum];
    }

    public static List<Player> getBasicList() {
        Player liel = new Player("Player 1",1);
        Player enemy = new Player("Player 2",2);
        Player yossi = new Player("Player 3",3);
        Player random = new Player(4);
        List<Player> players = new ArrayList<>();
        players.add(liel);
        players.add(enemy);
        players.add(yossi);
        players.add(random);
        return players;
    }



}
