package com.liel.boardgame.ui;

import com.liel.boardgame.UserPreferences;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class SettingsController {


    @FXML
    private HBox items3;
    @FXML
    private ToggleSwitch isScrollBarToggleSwitch;
    @FXML
    private BorderPane mainRoot;
    @FXML
    private BorderPane root;
    @FXML
    private ComboBox<String> boardSizeComboBox;


    public void initialize() {
        setUpItems3();

        mainRoot.getStyleClass().add("root");
        boardSizeComboBox.getStyleClass().add("combo-box");

        boardSizeComboBox.getItems().add("5 x 5");
        boardSizeComboBox.getItems().add("6 x 6");
        boardSizeComboBox.getItems().add("7 x 7 ");
        boardSizeComboBox.getItems().add("8 x 8 ");
        boardSizeComboBox.getItems().add("9 x 9");
        boardSizeComboBox.getItems().add("10 x 10");
        boardSizeComboBox.getItems().add("11 x 11");
        boardSizeComboBox.getItems().add("12 x 12");

        boardSizeComboBox.getSelectionModel().select(UserPreferences.getInstance().getBoardLength());

        isScrollBarToggleSwitch.switchOnProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                System.out.println("Switch is on");
                UserPreferences.getInstance().setScrollable(true);
            } else {
                System.out.println("Switch is off");
                UserPreferences.getInstance().setScrollable(false);
            }
        });
        boolean a = UserPreferences.getInstance().isScrollable();
        if (a) {
            isScrollBarToggleSwitch.switchOn();
        } else {
            isScrollBarToggleSwitch.switchOff();
        }


        boardSizeComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            UserPreferences.getInstance().setBoardLength(newValue);
        });
    }

    private void setUpItems3() {
        items3.setSpacing(25);
        items3.setAlignment(Pos.CENTER);

//        JFXButton button = new JFXButton();
//        button.setText("test!");

    }
}
