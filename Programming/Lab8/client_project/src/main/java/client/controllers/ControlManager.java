package client.controllers;

import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public interface ControlManager {
    void showScene(Stage stage, String sceneFile, Controller controller);

    void initBoxes(ChoiceBox<String> type, ChoiceBox<String> eyes, ChoiceBox<String> hair, ChoiceBox<String> country);

    String getErrorMessage(Exception e, ResourceBundle bundle);

    String getErrorMessage(String e, ResourceBundle bundle);
}
