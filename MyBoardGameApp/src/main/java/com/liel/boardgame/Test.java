package com.liel.boardgame;

import com.liel.boardgame.node.NodeOrientation;
import com.liel.boardgame.node.NodeRectangle;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Test extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        NodeRectangle nodeRectangle = new NodeRectangle(NodeOrientation.CORNER);
        StackPane pane = new StackPane(nodeRectangle);

        System.out.println(nodeRectangle.getTravelDistance());
        BorderPane parent = new BorderPane();
        parent.setCenter(pane);
//        parent.setTop(new Rectangle(5,5));

        primaryStage.setScene(new Scene(parent));

        primaryStage.show();
    }
}
