package com.liel.boardgame.ui;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.liel.boardgame.Player;
import com.liel.boardgame.Utility;
import com.liel.boardgame.board.Board;
import com.liel.boardgame.board.SquareBoard;
import com.liel.boardgame.dice.Dice;
import com.liel.boardgame.game.Game;
import com.liel.boardgame.node.Node;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class BoardController {

    private static final double screenWidth = MobileApplication.getInstance().getScreenHeight();
    private static final double screenHeight = MobileApplication.getInstance().getScreenWidth();

    private static final SimpleDoubleProperty doubleProperty = new SimpleDoubleProperty(screenWidth);

    @FXML
    private BorderPane root;

    @FXML
    private GridPane gameBoard;

    private final int length = 6;

    private Board board = new SquareBoard(length);
    private List<Player> players = Utility.getBasicList();
    private Dice dice = new Dice("abc");
    private Game game = new Game(board, players, dice);
    List<Node> nodes = new ArrayList<>();

    public void initialize() {

        BorderPane.setAlignment(gameBoard, Pos.CENTER);
        gameBoard.setAlignment(Pos.CENTER);
        gameBoard.maxHeightProperty().bind(doubleProperty);
        gameBoard.maxWidthProperty().bind(gameBoard.heightProperty());

        for (Player player : players){
            player.setStyle("-fx-font-family: 'Comic Sans MS'; ");
        }

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                Node visualNode = board.getNode(j, i);
                System.out.println("Creating visualNode " + visualNode);

                if (visualNode == null) {
                    continue;
                }
                gameBoard.add(visualNode, j, i);
                nodes.add(visualNode);
            }
        }

        Button button = new Button("Roll dice");
        HBox hBox = new HBox();
        Label label = new Label();
        hBox.setSpacing(35);
        Label status = new Label();
        hBox.getChildren().addAll(button,label,status);
        BorderPane.setAlignment(hBox, Pos.TOP_CENTER);
        BorderPane.setMargin(hBox, new Insets(50, 0, 0, 0));
        root.setTop(hBox);

        button.setOnMouseClicked(event -> {
            TranslateTransition translateForward = new TranslateTransition(Duration.millis(500), button);
            translateForward.setByY(-30);
            translateForward.play();
            button.setDisable(true);
            TranslateTransition translateBack = new TranslateTransition(Duration.millis(500), button);
            translateBack.setByY(30);
            translateForward.setOnFinished(event1 -> {
                translateBack.play();
            });
            translateBack.setOnFinished(event1 -> button.setDisable(false));

            RotateTransition rotateTransition = new RotateTransition(Duration.millis(1000), button);
            rotateTransition.setByAngle(360);
            rotateTransition.play();
            game.makeMove();
            label.setText(game.getPlayerTurn().getName() + "\nRolled: " + dice.getLastRoll());

            StringBuffer buffer = new StringBuffer();
            for (Player player : players){
                buffer.append(player.getName());
                buffer.append(" - ");
                buffer.append(player.getMoney());
                buffer.append("\n");
            }
            status.setText(buffer.toString());

        });


    }
}
