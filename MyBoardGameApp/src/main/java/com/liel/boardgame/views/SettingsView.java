package com.liel.boardgame.views;

import com.gluonhq.charm.glisten.animation.BounceInUpTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.io.IOException;

public class SettingsView extends View {


    public SettingsView() {
        this.showingProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                setShowTransitionFactory(BounceInUpTransition::new);
            }
        });
        Platform.runLater(() -> {

            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/com/liel/boardgame/settings.fxml"));
                setCenter(parent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    @Override
    protected void updateAppBar(AppBar appBar) {
        super.updateAppBar(appBar);
        appBar.setTitleText("Settings");
        Button goBack = MaterialDesignIcon.ARROW_BACK.button();
        appBar.getActionItems().add(goBack);

        goBack.setOnAction(event -> {
            MobileApplication.getInstance().switchView(MobileApplication.HOME_VIEW);
        });
    }
}
