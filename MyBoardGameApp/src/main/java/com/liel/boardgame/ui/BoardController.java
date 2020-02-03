package com.liel.boardgame.ui;

import com.gluonhq.charm.down.Platform;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.liel.boardgame.Player;
import com.liel.boardgame.UserPreferences;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class BoardController {

    private static final double screenWidth = MobileApplication.getInstance().getScreenHeight();
    private static final double screenHeight = MobileApplication.getInstance().getScreenWidth();

    private static final SimpleDoubleProperty doubleProperty = new SimpleDoubleProperty(screenWidth);

    private final int length;

    @FXML
    private BorderPane root;

    @FXML
    private GridPane gameBoard;
    int playerTurn = 0;
    @FXML
    private ScrollPane scrollPane;

    private Board board = new SquareBoard(length);
    private List<Player> players = Utility.getBasicList();
    private Dice dice = new Dice("abc");
    private Game game = new Game(board, players, dice);
    List<Node> nodes = new ArrayList<>();

    {
        String[] data = UserPreferences.getInstance().getBoardLength().split(" ");
        String sLength = data[0];
        length = Integer.parseInt(sLength);
    }

    public void initialize() {
//        handleScrollPane();
        BorderPane.setAlignment(gameBoard, Pos.CENTER);
        gameBoard.setAlignment(Pos.CENTER);
        gameBoard.maxHeightProperty().bind(doubleProperty);
        gameBoard.maxWidthProperty().bind(gameBoard.heightProperty());

        for (Player player : players) {
            player.setStyle("-fx-font-family: " + "Comic Sans MS");
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

        Button button = new Button("");
        button.setStyle("-fx-background-color: white; -fx-border-radius: 50%; ");
        try {
            Image image = new Image(getClass().getResourceAsStream("/dice-icon.jpg"));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(50);
//            imageView.setFitHeight(20);
            imageView.setPreserveRatio(true);
            button.setGraphic(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
        VBox hBox = new VBox();
        Label label = new Label();
        hBox.setSpacing(35);
        PlayersStatus status = new PlayersStatus(this.players);
//        status.setAlignment(Pos.CENTER);
//        status.setSpacing(15);
        hBox.getChildren().addAll(button, label, status);
        BorderPane.setAlignment(hBox, Pos.CENTER_LEFT);
        BorderPane.setMargin(hBox, new Insets(50, 5, 0, 15));
        root.setRight(hBox);

        Button settings = MaterialDesignIcon.SETTINGS.button();
        settings.setStyle("-fx-text-fill: blue");
        settings.setAlignment(Pos.CENTER_LEFT);
        hBox.getChildren().add(settings);
        settings.setOnAction(event -> {
            MobileApplication.getInstance().switchView("SETTINGS");
        });

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
            status.update();
            int roll = dice.roll();


        });
    }

    private void handleScrollPane() {
        if (!Platform.isDesktop()) {
            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        }
    }
}
