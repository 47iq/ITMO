package client.controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ArgController implements Initializable {
    public Button backButton;
    public ImageView imgView;
    public Text addText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        backButton.setOnAction(actionEvent -> {
            Stage stage = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("mainScene.fxml"));
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
}
