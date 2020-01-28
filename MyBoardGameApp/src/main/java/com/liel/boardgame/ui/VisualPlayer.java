package com.liel.boardgame.ui;

import com.liel.boardgame.Player;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class VisualPlayer extends Circle {
    private Player player;
    private int id;

    public VisualPlayer(Player player) {
        this.player = player;
        this.id = player.getPlayerID();
        Color color = null;
        switch (id) {
            case 1:
                color = Color.BLUE;
                break;
            case 2:
                color = Color.RED;
                break;
            case 3:
                color = Color.GREEN;
                break;
            case 4:
                color = Color.YELLOW;
                break;
        }
        assert color != null : "must inititalize Player with proper id";
        setFill(color);
        setRadius(15);
//        setStyle("-fx-border-color: slategray; -fx-border-radius:1px;");

    }
}
