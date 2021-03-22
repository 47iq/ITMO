package client.controllers;

import client.ClientContext;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeController implements Initializable {
    public Button logInButton;
    public Button registerButton;
    public ChoiceBox<String> language;
    public ImageView imgView;
    public Button exitButton;
    public Text addText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> types = FXCollections.observableArrayList("English", "Russian", "Turkish", "French", "Spanish(Honduras)");
        language.setItems(types);
        language.setValue("Russian");
        logInButton.setOnAction(actionEvent -> {
            try {
                ClientContext.showScene("login.fxml");
                exit();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        registerButton.setOnAction(actionEvent -> {
            try {
                ClientContext.showScene("register.fxml");
                exit();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        exitButton.setOnAction(actionEvent -> {
            exit();
        });
    }

    private void exit() {
        Stage stage = (Stage) registerButton.getScene().getWindow();
        stage.close();
    }
}
