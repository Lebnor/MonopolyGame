package com.liel.boardgame.game;

import com.liel.boardgame.Player;
import com.liel.boardgame.Utility;
import com.liel.boardgame.board.Board;
import com.liel.boardgame.board.SquareBoard;
import com.liel.boardgame.dice.Dice;
import com.liel.boardgame.move.Move;
import com.liel.boardgame.move.Movement;
import com.liel.boardgame.node.Node;
import com.liel.boardgame.node.Point;
import javafx.geometry.Bounds;

import java.util.List;

public class Game {

    private Dice dice;
    private Board board;
    List<Player> players;
    private int playerTurn = 0;

    public static void main(String[] args) {

        Board board = new SquareBoard(9);
//        board.arrangeNodes();
        List<Player> players = Utility.getBasicList();
        Game game = new Game(board, players, null);
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

    public void makeMove() {
        Player currentPlayer = players.get(playerTurn);
        printPlayer(currentPlayer);
        int roll = dice.roll();
//        int roll = 1;

        Movement movement = new Move(board, currentPlayer, roll, true);
        movement.execute();

        playerTurn++;
        if (playerTurn == players.size()) {
            playerTurn = 0;
        }


    }

    public void startGame() {


    }

    private void putPlayers() {
        for (Player p : players) {
            assert p != null : "null player";
            assert board != null : "null board";
            assert board.getBottomRight() != null : "null board bottom right node";

            Node node = board.getBottomRight();
            Point point = node.getPoint();
            p.setCurrentPosition(point);
            node.addPlayer(p);
        }
    }
}
