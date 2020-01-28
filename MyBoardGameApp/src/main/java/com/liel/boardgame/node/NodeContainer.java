package com.liel.boardgame.node;

import com.liel.boardgame.ui.VisualNode;

public class NodeContainer {

    private GameNode head;
    private GameNode last;
    private int size = 1;

    public NodeContainer() {
//        last = head;
    }

    public boolean addNext(GameNode newNode) {
        if (newNode == null) {
            return false;
        }
        if (head == null) {
            head = newNode;
        }
        if (head.getNextNode() == null) {
            // only head is in the list
            head.setNextNode(newNode);
            newNode.setPrevNode(head);
            newNode.setNextNode(null);
            last = newNode;
        } else {
            last.setNextNode(newNode);
            newNode.setPrevNode(last);
            last = newNode;
        }
        last.setNextNode(head);
        head.setPrevNode(last);
        size++;
        return true;
    }

    public int getSize() {
        return size;
    }

    public GameNode getHead() {
        return this.head;
    }


    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        GameNode node = this.head;
        for (int i = 0; i < size; i++) {
            sb.append(node.toString() + "\n");
            node = node.getNextNode();
        }
        return sb.toString();
    }

    public static NodeContainer getContainerBySize(int size) {
        NodeContainer container = new NodeContainer();
        for (int i = 0; i < size; i++) {
            Point point = new Point(i + 1, i + 1);
            Node node = new Node(point);
            VisualNode visualNode = new VisualNode(node,NodeOrientation.CORNER);
            container.addNext(visualNode);
        }
        return container;
    }

    public static void main(String[] args) {



    }

}
