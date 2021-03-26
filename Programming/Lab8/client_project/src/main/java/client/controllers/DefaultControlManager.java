package client.controllers;

import client.ClientContext;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;

import java.io.IOException;
import java.util.ResourceBundle;

public class DefaultControlManager implements ControlManager {
    public void showScene(Stage stage, String sceneFile, Controller controller) {
        try {
            FXMLLoader loader = new FXMLLoader(ClientContext.class.getClassLoader().getResource(sceneFile));
            Parent root = loader.load();
            Controller newController = loader.getController();
            ControllerContext context = controller.getContext();
            newController.initialize(context);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            LogManager.getLogger().error("IOException");
        }
    }

    public void initBoxes(ChoiceBox<String> type, ChoiceBox<String> eyes, ChoiceBox<String> hair, ChoiceBox<String> country) {
        ObservableList<String> types = FXCollections.observableArrayList("VIP", "CHEAP", "USUAL", "");
        type.setItems(types);
        ObservableList<String> eyeTypes = FXCollections.observableArrayList("BLACK", "BLUE", "YELLOW");
        eyes.setItems(eyeTypes);
        ObservableList<String> hairTypes = FXCollections.observableArrayList("BLACK", "GREEN", "YELLOW", "RED");
        hair.setItems(hairTypes);
        ObservableList<String> countryTypes = FXCollections.observableArrayList("RUSSIA", "CHINA", "SPAIN", "FRANCE");
        country.setItems(countryTypes);
    }

    public String getErrorMessage(Exception e, ResourceBundle bundle) {
        if (bundle.containsKey(e.getMessage()))
            return bundle.getString(e.getMessage());
        else
            return bundle.getString("ERR_UNK");
    }

    public String getErrorMessage(String e, ResourceBundle bundle) {
        if (bundle.containsKey(e))
            return bundle.getString(e);
        else
            return bundle.getString("ERR_UNK");
    }
}
