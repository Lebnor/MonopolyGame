package com.liel.boardgame;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.visual.Swatch;
import com.liel.boardgame.views.SettingsView;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends MobileApplication {

    public static double MAIN_WIDTH;
    public static double MAIN_HEIGHT;

    @Override
    public void init() {
        addViewFactory(HOME_VIEW, BasicView::new);
        addViewFactory("SETTINGS", SettingsView::new);


    }

    @Override
    public void stop() throws Exception {
        super.stop();

        UserPreferences.getInstance().saveData();
    }

    @Override
    public void postInit(Scene scene) {
        UserPreferences.getInstance().loadData();
        Swatch.BLUE.assignTo(scene);

        MAIN_WIDTH = scene.getWidth();
        MAIN_HEIGHT = scene.getHeight();

        ((Stage) scene.getWindow()).getIcons().add(new Image(Main.class.getResourceAsStream("/icon.png")));
    }
}
