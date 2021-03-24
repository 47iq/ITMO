package client.controllers;

import client.ClientContext;
import common.Response;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    public ImageView imgView;
    public Label login;
    public TextField loginField;
    public PasswordField passwordField;
    public Label pass;
    public Text warningText1;
    public Button submitButton;
    public Button backButton;
    public PasswordField passwordFieldConf;
    public Label passConf;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        submitButton.setOnAction(actionEvent -> {
            String login = loginField.getText();
            String password = passwordField.getText();
            String passwordConfirmation = passwordFieldConf.getText();
            if (fieldsFilled(login, password)) {
                if(passwordsMatch(password, passwordConfirmation)) {
                    Response response = ClientContext.getCommandReader().getResponse(String.format("register %s %s", login, password));
                    if (response.isSuccessful()) {
                        ClientContext.setCurrentUser(login);
                        success();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(response.getMessage());
                        alert.showAndWait();
                    }
                } else
                   matchingAlert();
            } else
                fieldsAlert();
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

    private boolean fieldsFilled(String login, String password) {
        return !login.equals("") && !password.equals("");
    }

    private boolean passwordsMatch(String passwordConf, String password) {
        return password.equals(passwordConf);
    }

    private void success() {
        try {
            ClientContext.showScene((Stage) backButton.getScene().getWindow(),"mainScene.fxml");
            Stage stage = (Stage) submitButton.getScene().getWindow();
            stage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fieldsAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        //FIXME
        alert.setTitle("Error");
        alert.setHeaderText("Error! Fields must be filled.");
        alert.showAndWait();
    }

    private void matchingAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        //FIXME
        alert.setTitle("Error");
        alert.setHeaderText("Error! Passwords don't match.");
        alert.showAndWait();
    }
}
