package com.liel.boardgame.node;

import com.liel.boardgame.ui.VisualNode;

import java.util.HashMap;
import java.util.Map;

public class LinkedListNodeContainer extends NodeContainer {

    private final Map<Point, GameNode> indexNodes;

    public LinkedListNodeContainer() {
        super();
        indexNodes = new HashMap<>();
    }

    public GameNode getNodeByPoint(int x, int y) {
        Point check = new Point(x, y);
        return getNodeByPoint(check);
    }

    public GameNode getNodeByPoint(Point point) {
        return indexNodes.getOrDefault(point, null);
    }

    public boolean containsPoint(Point point) {
        for (GameNode node : indexNodes.values()) {
            if (node.getPoint().equals(point)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean addNext(GameNode newNode) {
        if (newNode == null) {
            return false;
        }
        if (indexNodes.containsKey(newNode.getPoint())) {
            return false;
        }

        if (super.addNext(newNode)) {
            indexNodes.put(newNode.getPoint(), newNode);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "IndexedNodeContainer{" +
                "indexNodes=" + indexNodes +
                '}';
    }

    public static void main(String[] args) {
        LinkedListNodeContainer linkedListNodeContainer = new LinkedListNodeContainer();
        System.out.println(linkedListNodeContainer.getHead().getNextNode());
        System.out.println(linkedListNodeContainer);

        Point bankPoint = new Point(2, 2);
        Node bank = new Node(bankPoint);
        VisualNode node = new VisualNode(bank,NodeOrientation.CORNER);
        linkedListNodeContainer.addNext(node);
        System.out.println(linkedListNodeContainer);
        System.out.println();
        System.out.println(linkedListNodeContainer.getNodeByPoint(0, 0));
        System.out.println(linkedListNodeContainer.getNodeByPoint(3, 3));
        System.out.println(linkedListNodeContainer.getNodeByPoint(2, 2));

    }
}
