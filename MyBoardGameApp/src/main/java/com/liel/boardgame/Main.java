package com.liel.boardgame;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.visual.Swatch;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends MobileApplication {

    public static  double MAIN_WIDTH;
    public static  double MAIN_HEIGHT;

    @Override
    public void init() {
        addViewFactory(HOME_VIEW, BasicView::new);
    }

    @Override
    public void postInit(Scene scene) {
        Swatch.BLUE.assignTo(scene);

        MAIN_WIDTH = scene.getWidth();
        MAIN_HEIGHT = scene.getHeight();

        ((Stage) scene.getWindow()).getIcons().add(new Image(Main.class.getResourceAsStream("/icon.png")));
    }
}
