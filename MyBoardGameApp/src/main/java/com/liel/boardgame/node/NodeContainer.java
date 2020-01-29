package com.liel.boardgame.node;

public class NodeContainer {

    private Node head;
    private Node last;
    private int size = 1;

    public NodeContainer() {
//        last = head;
    }

    public boolean addNext(Node newNode) {
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

    public Node getHead() {
        return this.head;
    }


    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        Node node = this.head;
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
            Node node = new Node(point,NodeOrientation.CORNER);
//            Node visualNode = new Node(node,NodeOrientation.CORNER);
            container.addNext(node);
        }
        return container;
    }

    public static void main(String[] args) {



    }

}
