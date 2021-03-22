package client.controllers;

import client.ClientContext;
import common.Response;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
    public Button commandButton;
    public TextField arg;

    private String curCommand = ClientContext.getCurrentCommand();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        commandButton.setText(curCommand);
        backButton.setOnAction(actionEvent -> {
            try {
                ClientContext.showScene("mainScene.fxml");
                exit();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        commandButton.setOnAction(actionEvent -> {
            if(arg.getText().equals(""))
                displayError("All fields must be filled.");
            else {
                Response response = ClientContext.getCommandReader().getResponse(curCommand + " " + arg.getText());
                displayInfo(response);
            }
        });
    }

    private void displayError(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        //FIXME
        alert.setHeaderText(e.getMessage());
        alert.showAndWait();
    }

    private void displayError(String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        //FIXME
        alert.setHeaderText(s);
        alert.showAndWait();
    }

    private void displayInfo(Response response) {
        if(response.isSuccessful())
            displayInfo(response.getMessage());
        else
            displayError(response.getMessage());
    }

    private void displayInfo(String s) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        //FIXME
        alert.setHeaderText(s);
        alert.showAndWait();
    }

    private void goBack() {
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
