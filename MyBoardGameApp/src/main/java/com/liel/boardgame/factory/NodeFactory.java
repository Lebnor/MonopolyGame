package com.liel.boardgame.factory;

import com.liel.boardgame.node.Node;
import com.liel.boardgame.node.NodeOrientation;
import com.liel.boardgame.node.Point;

public abstract class NodeFactory {


    public static Node getNode(int x, int y, NodeOrientation orientation) {
        Point point = new Point(x, y);
        Node node = new Node(point,orientation);
//        VisualNode visualNode = new VisualNode(node,orientation);
        return node;
    }
}
