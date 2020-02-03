package com.liel.boardgame.node;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.liel.boardgame.Main.MAIN_HEIGHT;

public class NodeRectangle extends Rectangle {

    private NodeOrientation orientation;
    private int travelDistance;
    private int defaultWidth = 35;
    private int defaultHeight = (int) MAIN_HEIGHT / 20;

    public NodeRectangle(NodeOrientation orientation) {
        this.orientation = orientation;
        switch (orientation) {
            case CORNER:
                setWidth(defaultHeight * 2);
                setHeight(defaultHeight * 2);
                this.travelDistance = (int) (defaultHeight * 2);
                break;
            case LAYING:
                setWidth(defaultHeight * 2);
                setHeight(defaultHeight);
                this.travelDistance = (int) (defaultHeight * 2);
                break;
            case STANDING:
                setWidth(defaultHeight);
                setHeight(defaultHeight * 2);
                this.travelDistance = (int) defaultHeight;
                break;
        }
        setStrokeWidth(1);
        setStroke(Color.BLACK);
        setFill(Color.WHITESMOKE);
    }

    /**
     * the distance the player would need to advance to cross this rectangle
     *
     * @return distance in px
     */
    public int getTravelDistance() {
        return travelDistance;
    }

    public NodeOrientation getOrientation() {
        return this.orientation;
    }


}
