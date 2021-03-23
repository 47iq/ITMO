package client.controllers;

import client.ClientContext;
import common.Response;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public Button submitButton;
    public PasswordField passwordField;
    public Label pass;
    public TextField loginField;
    public Label login;
    public ImageView imgView;
    public Button backButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        submitButton.setOnAction(actionEvent -> {
            String login = loginField.getText();
            String password = passwordField.getText();
            if (!login.equals("") && !password.equals("")) {
                Response response = ClientContext.getCommandReader().getResponse(String.format("login %s %s", login, password));
                if (response.isSuccessful()) {
                    ClientContext.setCurrentUser(login);
                    success();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(response.getMessage());
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Fields must be filled");
                alert.showAndWait();
            }
        });
        backButton.setOnAction(actionEvent -> {
            try {
                ClientContext.showScene("welcome.fxml");
                exit();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void success() {
            try {
                ClientContext.showScene("mainScene.fxml");
                exit();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    private void exit() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
}
