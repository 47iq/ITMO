package client.controllers;

import client.ClientContext;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UpdateController implements Initializable {
    public Button backButton;

    public void initialize(URL location, ResourceBundle resources) {
        backButton.setOnAction(actionEvent -> {
            try {
                ClientContext.showScene("mainScene.fxml");
                exit();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void exit() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
}
