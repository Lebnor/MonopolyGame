package com.liel.boardgame;

import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class BasicView extends View {


    public BasicView() {
        getStyleClass().add("mainWindow");

        Platform.runLater(() -> {

            setStyle("-fx-background-color: lightblue");
            try {
                Parent root = FXMLLoader.load(getClass().getResource("board.fxml"));


                setCenter(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> System.out.println("Menu")));
        appBar.setTitleText("Game");
        appBar.getActionItems().add(MaterialDesignIcon.SEARCH.button(e -> System.out.println("Search")));
    }

}
