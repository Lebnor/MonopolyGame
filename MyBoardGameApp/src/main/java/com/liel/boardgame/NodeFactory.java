package com.liel.boardgame;

import com.liel.boardgame.node.GameNode;
import com.liel.boardgame.node.Node;
import com.liel.boardgame.node.NodeOrientation;
import com.liel.boardgame.node.Point;
import com.liel.boardgame.ui.VisualNode;

public abstract class NodeFactory {


    public static GameNode getNode(int x, int y, NodeOrientation orientation) {
        Point point = new Point(x, y);
        Node node = new Node(point);
        VisualNode visualNode = new VisualNode(node,orientation);
        return visualNode;
    }
}
