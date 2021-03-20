package client.controllers;

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
    public Text title2;
    public Text title1;
    public Button logInButton;
    public Button registerButton;
    public ChoiceBox<String> language;
    public ImageView imgView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> types = FXCollections.observableArrayList("English", "Russian", "Turkish", "French", "Spanish(Honduras)");
        language.setItems(types);
        language.setValue("Russian");
        logInButton.setOnAction(actionEvent -> {
            String ll = language.getValue();
            Stage stage = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
                Scene scene = new Scene(root, 700, 400);
                //FIXME
                stage.setTitle("welcome");
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
                stage = (Stage) registerButton.getScene().getWindow();
                stage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        registerButton.setOnAction(actionEvent -> {
            String ll = language.getValue();
            Stage stage = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));
                Scene scene = new Scene(root, 700, 400);
                //FIXME
                stage.setTitle("registration");
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
                stage = (Stage) registerButton.getScene().getWindow();
                stage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
