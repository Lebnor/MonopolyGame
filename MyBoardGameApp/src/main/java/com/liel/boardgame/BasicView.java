package com.liel.boardgame;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.liel.boardgame.board.Board;
import com.liel.boardgame.board.SquareBoard;
import com.liel.boardgame.game.Game;
import com.liel.boardgame.ui.VisualNode;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.io.IOException;
import java.util.List;

public class BasicView extends View {


    public BasicView() {
        getStyleClass().add("mainWindow");

        Platform.runLater(() -> {

            setStyle("-fx-background-color: lightblue");
            try {
                Parent root = FXMLLoader.load(getClass().getResource("board.fxml"));

//                BorderPane.setMargin(root, new Insets(screenHeight / 20, screenWidth / 20, screenHeight / 20, screenWidth / 20));
//            BorderPane.setMargin(root,new Insets(100));
//            getChildren().add(root);
                setCenter(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> System.out.println("Menu")));
        appBar.setTitleText("Basic View");
        appBar.getActionItems().add(MaterialDesignIcon.SEARCH.button(e -> System.out.println("Search")));
    }

}
