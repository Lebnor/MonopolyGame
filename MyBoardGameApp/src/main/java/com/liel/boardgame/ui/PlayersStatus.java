package com.liel.boardgame.ui;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.liel.boardgame.Player;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PlayersStatus extends VBox {

    private List<Label> playersDescription;
    private List<Player> playersList;

    public PlayersStatus(List<Player> players) {
        this.playersList = players;
        playersDescription = new ArrayList<>();
        for (Player player : playersList) {
            Label label = new Label();
            Color color = player.getColor();
//            String theColor = "" + color. + color.getGreen() + color.getBlue();
//            System.out.println("Applying color: " + theColor);
//            label.setStyle("-fx-text-fill: " + color..substring(4));
//            label.setStyle("-fx-text-fill: color" + theColor);
            label.setTextFill(color);
            playersDescription.add(label);
//            label.setAlignment(Pos.CENTER);
            getChildren().add(label);

        }
//        setAlignment(Pos.CENTER);
        setSpacing(MobileApplication.getInstance().getScreenHeight() / 100);

    }

    public void update() {
        Iterator<Player> iterator = playersList.iterator();
        for (Label label : playersDescription) {
            Player player = null;
            if (iterator.hasNext()) {
                player = iterator.next();
            }
            assert player != null : "not enough players in the list or too many lables";
            label.setText(player.getName() + " - " + player.getMoney());
        }
    }
}
