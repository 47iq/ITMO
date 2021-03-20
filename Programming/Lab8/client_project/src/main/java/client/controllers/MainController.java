package client.controllers;

import client.ClientContext;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {


    public ImageView imgView;
    public Text header;
    public Text infoText;
    public Text deleteText;
    public Text addText;
    public Button backButton;
    public Button showButton;
    public Button infoButton;
    public Button helpButton;
    public Button execButton;
    public Button visualButton;
    public Button removeByIdButton;
    public Button removeGreaterButton;
    public Button removeFirstButton;
    public Button clearButton;
    public Button addIfMaxButton;
    public Button addButton;
    public Button updateButton;
    public Button exitButton;
    public TextField fileNameField;

    public void initialize(URL location, ResourceBundle resources) {
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
        exitButton.setOnAction(actionEvent -> {
            Stage stage = (Stage) exitButton.getScene().getWindow();
            stage.close();
        });
        clearButton.setOnAction(actionEvent -> {
            ClientContext.getCommandReader().getResponse("clear");
        });
        execButton.setOnAction(actionEvent -> {
            String fileName = fileNameField.getText();
            if(fileName != null)
                try {
                    ClientContext.getCommandReader().getResponse("execute_script " + fileName);
                } catch (Exception e) {
                    displayError(e);
                }
            else
                displayNoFileError();
        });
        updateButton.setOnAction(actionEvent -> {
            Stage stage = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("update.fxml"));
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
        addButton.setOnAction(actionEvent -> {
            Stage stage = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("add.fxml"));
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

    private void displayNoFileError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        //FIXME
        alert.setHeaderText("File text field must be filled");
        alert.showAndWait();
    }

    private void displayError(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        //FIXME
        alert.setHeaderText(e.getMessage());
        alert.showAndWait();
    }
}
