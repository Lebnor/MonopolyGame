package com.liel.boardgame.ui;

import javafx.scene.layout.StackPane;

public class CornerVisualNode extends StackPane {

    private VisualNode visualNode;
    private final int WIDTH;
    private final int HEIGHT;

    public CornerVisualNode(VisualNode visualNode) {
        this.visualNode = visualNode;
        HEIGHT = visualNode.getHEIGHT();
        WIDTH = visualNode.getWIDTH() * 2;
    }


}
