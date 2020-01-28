package com.liel.boardgame.game;

import com.liel.boardgame.Player;
import com.liel.boardgame.Utility;
import com.liel.boardgame.board.Board;
import com.liel.boardgame.board.SquareBoard;
import com.liel.boardgame.dice.Dice;
import com.liel.boardgame.effects.BaseEffect;
import com.liel.boardgame.node.GameNode;
import com.liel.boardgame.node.Node;

import java.util.List;

public class Game {

    private Dice dice = new Dice();
    private Board board;
    List<Player> players;
    private int playerTurn = 0;

    public int makeMove() {
        Player current = players.get(playerTurn);
        assert current != null : "Current player is null";
        int roll = dice.roll();
        GameNode landed = board.advancePlayer(current, roll);

        BaseEffect effect = landed.getBaseEffect();
        if (effect != null){
            effect.applyEffect(current);
        }
        playerTurn++;
        if (playerTurn == players.size()) {
            playerTurn = 0;
        }
        return roll;
    }
    public Board getBoard(){
        return this.board;
    }

    public Player getPlayerTurn() {
        return players.get(playerTurn);
    }

    public Game(Board board, List<Player> players) {
        this.players = players;
        this.board = board;
        putPlayers();
    }

    private void putPlayers() {
        for (Player p : players) {
            assert p != null : "null player";
            assert board != null : "null board";
            assert board.getBottomRight() != null : "null board bottom right node";
            try{
                p.setCurrentPosition(board.getBottomRight().getPoint());
            }catch (NullPointerException e) {

            }
        }
    }

    public void startGame() {


    }


    public static void main(String[] args) {

        Board board = new SquareBoard(9);
//        board.arrangeNodes();
        List<Player> players = Utility.getBasicList();
        Game game = new Game(board, players);
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
