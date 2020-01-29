package com.liel.boardgame.game;

import com.liel.boardgame.Player;
import com.liel.boardgame.Utility;
import com.liel.boardgame.board.Board;
import com.liel.boardgame.board.SquareBoard;
import com.liel.boardgame.dice.Dice;
import com.liel.boardgame.effects.BaseEffect;
import com.liel.boardgame.node.Node;
import com.liel.boardgame.node.Point;
import javafx.animation.TranslateTransition;
import javafx.geometry.Bounds;
import javafx.util.Duration;

import java.util.List;

public class Game {

    private Dice dice;
    private Board board;
    List<Player> players;
    private int playerTurn = 0;

    public int makeMove() {
        Player current = players.get(playerTurn);
        printPlayer(current);
        Bounds bounds = current.getBoundsInLocal();
        Bounds screenBounds = current.localToScreen(bounds);
        int x = (int) screenBounds.getMinX();
        int y = (int) screenBounds.getMinY();
        assert current != null : "Current player is null";
        int roll = dice.roll();
        Node landed = board.advancePlayer(current, roll);
        Bounds bounds2 = current.getBoundsInLocal();
        Bounds screenBounds2 = current.localToScreen(bounds2);
        int x2 = (int) screenBounds2.getMinX();
        int y2 = (int) screenBounds2.getMinY();
//        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(1000), current);
//        translateTransition.setByX(x-x2);
//        translateTransition.setByY(y-y2);
//        translateTransition.play();
//        TranslateTransition translateTransition2 = new TranslateTransition(Duration.millis(1000), current);
//        translateTransition2.setByX(x2-x);
//        translateTransition2.setByY(y2-y);
//        translateTransition.setOnFinished(event -> translateTransition2.play());
        printPlayer(current);
        BaseEffect effect = landed.getBaseEffect();
        if (effect != null) {
            effect.applyEffect(current);
        }
        playerTurn++;
        if (playerTurn == players.size()) {
            playerTurn = 0;
        }
        return roll;
    }

    private void printPlayer(Player player) {
        System.out.println(playerTurn + " moving");
        Bounds bounds = player.getBoundsInLocal();
        Bounds screenBounds = player.localToScreen(bounds);
        int x = (int) screenBounds.getMinX();
        int y = (int) screenBounds.getMinY();
        int width = (int) screenBounds.getWidth();
        int height = (int) screenBounds.getHeight();
        System.out.println(x + " , " + y);
        System.out.println(width + " " + height);
    }

    public Board getBoard() {
        return this.board;
    }

    public Player getPlayerTurn() {
        return players.get(playerTurn);
    }

    public Game(Board board, List<Player> players, Dice dice) {
        this.dice = dice;
        this.players = players;
        this.board = board;
        putPlayers();
    }

    private void putPlayers() {
        for (Player p : players) {
            assert p != null : "null player";
            assert board != null : "null board";
            assert board.getBottomRight() != null : "null board bottom right node";
            try {
                Node node = board.getBottomRight();
                Point point = node.getPoint();
                p.setCurrentPosition(point);
                node.addPlayer(p);
            } catch (NullPointerException e) {

            }
        }
    }

    public void startGame() {


    }


    public static void main(String[] args) {

        Board board = new SquareBoard(9);
//        board.arrangeNodes();
        List<Player> players = Utility.getBasicList();
        Game game = new Game(board, players,null);
        Player player1 = players.get(0);
        for (int i = 0; i < 50; i++) {
            game.makeMove();
//            board.advancePlayer(player1,5);
            board.printBoard();
            try {
                Thread.sleep(750);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        board.printBoard();
    }
}
