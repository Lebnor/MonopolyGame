package com.liel.boardgame.board;

import com.liel.boardgame.Player;
import com.liel.boardgame.node.Node;

public interface Board {

    Node getBottomRight();
    Node getBottomLeft();
    Node getTopRight();
    Node getTopLeft();
    void arrangeNodes();
    void printBoard();
    Node getNode(int x, int y);
    Node placePlayer(Player player, Node where);
    Node advancePlayer(Player player, int steps);
    Node retreatPlayer(Player player, int steps);

}


