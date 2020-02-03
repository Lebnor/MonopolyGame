package com.liel.boardgame;

import com.liel.boardgame.board.Board;
import com.liel.boardgame.node.Node;
import com.liel.boardgame.node.Point;
import javafx.animation.TranslateTransition;
import javafx.geometry.Bounds;
import javafx.util.Duration;

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

    public static void movePlayer(Player player, Board board, int steps) throws InterruptedException {
        Point playerPoint = player.getCurrentPosition();

        Node playerOnBoard = board.getNode(playerPoint.getX(), playerPoint.getY());
        Bounds bounds = playerOnBoard.getBoundsInParent();
        Bounds screenBounds = playerOnBoard.localToParent(bounds);
        double x = screenBounds.getMinX();
        double y = screenBounds.getMinY();
//        playerOnBoard.removePlayer(player);
        System.out.println("Moving from " + "\n" + playerOnBoard);
        boolean finished = false;
        for (int i = 0; i < steps; i++) {
            playerOnBoard = playerOnBoard.getNextNode();
            Point currentPlayerAt = playerOnBoard.getPoint();

            TranslateTransition translateTransition = new TranslateTransition(Duration.millis(1000), player);
            int dx = currentPlayerAt.getX() - playerPoint.getX();
            int dy = currentPlayerAt.getY() - playerPoint.getY();
            translateTransition.setByX(dx * 35);
            translateTransition.setByY(dy * 35);
            translateTransition.playFromStart();
            Node finalPlayerOnBoard1 = playerOnBoard;
            translateTransition.setOnFinished(event -> updatePlayer(board, finalPlayerOnBoard1, player));


        }
        System.out.println("\n\n to " + playerOnBoard);
//        Bounds bounds2 = playerOnBoard.getBoundsInParent();
//        Bounds screenBounds2 = playerOnBoard.localToParent(bounds2);
//        double x2 = screenBounds2.getMinX();
//        double y2 = screenBounds2.getMinY();
//        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(1000), player);
//        translateTransition.setByX(x2-x);
//        translateTransition.setByY(y2-y);
//        translateTransition.playFromStart();

//        Point currentPlayerAt = playerOnBoard.getPoint();
//        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(1000), player);
//        int dx = currentPlayerAt.getX() - playerPoint.getX();
//        int dy = currentPlayerAt.getY() - playerPoint.getY();
//        translateTransition.setByX(dx * 30);
//        translateTransition.setByY(dy * 30);
//        translateTransition.playFromStart();

        Node finalPlayerOnBoard = playerOnBoard;
//        translateTransition.setOnFinished(event -> {
//        board.getNode(playerPoint.getX(), playerPoint.getY()).removePlayer(player);
//        finalPlayerOnBoard.addPlayer(player);
//        player.setTranslateX(0);
//        player.setTranslateY(0);
//        });
//        playerOnBoard.addPlayer(player);


    }

    private static void updatePlayer(Board board, Node node, Player player) {
        Point playerPoint = player.getCurrentPosition();
        board.getNode(playerPoint.getX(), playerPoint.getY()).removePlayer(player);
        node.addPlayer(player);
        player.setTranslateX(0);
        player.setTranslateY(0);
    }

    public static List<Player> getBasicList() {
        List<Player> players = new ArrayList<>();
        int numOfPlayers = UserPreferences.getInstance().getNumberOfPlayers();
        for (int i = 0; i < numOfPlayers; i++) {
            players.add(new Player(getRandomName(), i + 1));
        }
//        Player liel = new Player("Player 1", 1);
//        Player enemy = new Player("Player 2", 2);
//        Player yossi = new Player("Player 3", 3);
//        Player random = new Player(4);
//        players.add(liel);
//        players.add(enemy);
//        players.add(yossi);
//        players.add(random);
        return players;
    }

    private static void addPlayer(List<Player> players, int i) {

    }


}
