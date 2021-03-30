package client.gui_controllers;

import common.Response;
import common.Ticket;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.ResourceBundle;

public interface ControlManager {
    void showScene(Stage stage, String sceneFile, Controller controller);

    void initBoxes(ChoiceBox<String> type, ChoiceBox<String> eyes, ChoiceBox<String> hair, ChoiceBox<String> country);

    String getErrorMessage(Exception e, ResourceBundle bundle);

    String getErrorMessage(String e, ResourceBundle bundle);

    String getTicketString(Ticket ticket);

    void displayError(Exception e, ResourceBundle bundle);

    void displayError(String e, ResourceBundle bundle);

    void displayInfo(Response response, ResourceBundle bundle);

    void displayInfo(String s, ResourceBundle bundle);
}
