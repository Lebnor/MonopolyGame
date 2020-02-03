package com.liel.boardgame;

import com.gluonhq.charm.glisten.animation.BounceInDownTransition;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class BasicView extends View {


    public BasicView() {
        this.showingProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                System.out.println("Switching");
                setShowTransitionFactory(BounceInDownTransition::new);
            }
        });
        getStyleClass().add("mainWindow");

        Platform.runLater(() -> {

            setStyle("-fx-background-color: lightblue");
            try {
                Parent root = null;
                boolean isScrollable = UserPreferences.getInstance().isScrollable();

                if (isScrollable) {
                    root = FXMLLoader.load(getClass().getResource("boardScrollable.fxml"));
                } else {
                    root = FXMLLoader.load(getClass().getResource("board.fxml"));
                }

                setCenter(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    @Override
    protected void updateAppBar(AppBar appBar) {
//        appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> System.out.println("Menu")));
//        appBar.setTitleText("Game");
        appBar.setVisible(false);
    }

}
