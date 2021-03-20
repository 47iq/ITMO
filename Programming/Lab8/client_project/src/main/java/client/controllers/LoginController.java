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
                if(response.isSuccessful())
                    success();
                else {
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
            Stage stage = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("welcome.fxml"));
                Scene scene = new Scene(root, 700, 400);
                stage.setTitle("Authorization");
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
                stage = (Stage) backButton.getScene().getWindow();
                stage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void success() {
        Stage stage = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("mainScene.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Database manager");
            stage.setScene(scene);
            stage.show();
            stage.setMinHeight(400);
            stage.setMinWidth(700);
            stage = (Stage) submitButton.getScene().getWindow();
            stage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
