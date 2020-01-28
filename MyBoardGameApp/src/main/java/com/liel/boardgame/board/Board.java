package com.liel.boardgame.board;

import com.liel.boardgame.Player;
import com.liel.boardgame.node.GameNode;
import com.liel.boardgame.node.Node;
import com.liel.boardgame.ui.VisualNode;

public interface Board {


    GameNode getBottomRight();
    GameNode getBottomLeft();
    GameNode getTopRight();
    GameNode getTopLeft();
    void arrangeNodes();
    void printBoard();
    GameNode getNode(int x, int y);
    GameNode advancePlayer(Player player, int steps);
    GameNode retreatPlayer(Player player, int steps);

}


