package client.controllers;

import client.ClientContext;
import common.Response;
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
    public Text userText;

    public void initialize(URL location, ResourceBundle resources) {
        userText.setText(ClientContext.getCurrentUser());
        backButton.setOnAction(actionEvent -> {
            try {
                ClientContext.showScene("welcome.fxml");
                exit();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        updateButton.setOnAction(actionEvent -> {
            try {
                ClientContext.showScene("update.fxml");
                exit();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        removeGreaterButton.setOnAction(actionEvent -> {
            try {
                ClientContext.setCurrentCommand("remove_greater");
                ClientContext.showScene("add.fxml");
                exit();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        addButton.setOnAction(actionEvent -> {
            try {
                ClientContext.setCurrentCommand("add");
                ClientContext.showScene("add.fxml");
                exit();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        addIfMaxButton.setOnAction(actionEvent -> {
            try {
                ClientContext.setCurrentCommand("add_if_max");
                ClientContext.showScene("add.fxml");
                exit();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        removeByIdButton.setOnAction(actionEvent -> {
            try {
                ClientContext.setCurrentCommand("remove_by_id");
                ClientContext.showScene("argCommand.fxml");
                exit();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        execButton.setOnAction(actionEvent -> {
            try {
                ClientContext.setCurrentCommand("execute_script");
                ClientContext.showScene("argCommand.fxml");
                exit();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        helpButton.setOnAction(actionEvent -> {
            execute("help");
        });
        infoButton.setOnAction(actionEvent -> {
            execute("info");
        });
        clearButton.setOnAction(actionEvent -> {
            execute("clear");
        });
        showButton.setOnAction(actionEvent -> {
            try {
                ClientContext.showScene("table.fxml");
                exit();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        removeFirstButton.setOnAction(actionEvent -> {
            execute("remove_first");
        });
        exitButton.setOnAction(actionEvent -> {
            exit();
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

    private void displayInfo(String s) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        //FIXME
        alert.setHeaderText(s);
        alert.showAndWait();
    }

    private void execute(String command) {
        try {
            ClientContext.setCurrentCommand(command);
            Response response = ClientContext.getCommandReader().getResponse(command);
            if(response.isSuccessful())
                displayInfo(response.getMessage());
            else
                displayError(response.getMessage());
        } catch (Exception e) {
            displayError(e);
        }
    }

    private void exit() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
}
