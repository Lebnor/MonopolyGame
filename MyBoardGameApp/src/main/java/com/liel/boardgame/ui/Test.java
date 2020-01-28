package com.liel.boardgame.ui;

import com.liel.boardgame.NodeFactory;
import com.liel.boardgame.Player;
import com.liel.boardgame.node.GameNode;
import com.liel.boardgame.node.NodeOrientation;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Test extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        GameNode visualNode = NodeFactory.getNode(0, 0, NodeOrientation.CORNER);

        Player player = new Player("liel",1);
        VisualPlayer visualPlayer = new VisualPlayer(player);
//        Pane pane = new Pane(visualPlayer);
        Parent parent = new BorderPane(visualPlayer);

        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
