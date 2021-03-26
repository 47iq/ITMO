package client.controllers;

import common.Response;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class MainController implements Controller {


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
    public Text userText;

    private ControllerContext context;
    private ControlManager controlManager;
    private ResourceBundle bundle;

    public void initialize(ControllerContext context) {
        this.context = context;
        controlManager = context.getControlManager();
        userText.setText(context.getCurrentUser());
        bundle = context.getBundle();
        localize();
        backButton.setOnAction(actionEvent -> {
            controlManager.showScene((Stage) backButton.getScene().getWindow(), "welcome.fxml", this);
        });
        updateButton.setOnAction(actionEvent -> {
            context.setPrevScene("mainScene.fxml");
            controlManager.showScene((Stage) backButton.getScene().getWindow(), "update.fxml", this);
        });
        removeGreaterButton.setOnAction(actionEvent -> {
            context.setCurrentCommand("remove_greater");
            controlManager.showScene((Stage) backButton.getScene().getWindow(), "add.fxml", this);
        });
        addButton.setOnAction(actionEvent -> {
            context.setCurrentCommand("add");
            controlManager.showScene((Stage) backButton.getScene().getWindow(), "add.fxml", this);
        });
        addIfMaxButton.setOnAction(actionEvent -> {
            context.setCurrentCommand("add_if_max");
            controlManager.showScene((Stage) backButton.getScene().getWindow(), "add.fxml", this);
        });
        removeByIdButton.setOnAction(actionEvent -> {
            context.setCurrentCommand("remove_by_id");
            controlManager.showScene((Stage) backButton.getScene().getWindow(), "argCommand.fxml", this);
        });
        execButton.setOnAction(actionEvent -> {
            context.setCurrentCommand("execute_script");
            controlManager.showScene((Stage) backButton.getScene().getWindow(), "argCommand.fxml", this);
        });
        helpButton.setOnAction(actionEvent -> {
            execute("help");
        });
        infoButton.setOnAction(actionEvent -> {
            context.setCurrentCommand("info");
            Response response = context.getCommandReader().getResponse("info");
            displayInfo(response);
        });
        clearButton.setOnAction(actionEvent -> {
            execute("clear");
        });
        showButton.setOnAction(actionEvent -> {
            context.setCurrentCollection(context.getCommandReader().getResponse("show").getCollection());
            controlManager.showScene((Stage) backButton.getScene().getWindow(), "table.fxml", this);
        });
        removeFirstButton.setOnAction(actionEvent -> {
            execute("remove_first");
        });
        exitButton.setOnAction(actionEvent -> {
            System.exit(0);
        });
    }

    private void localize() {
        backButton.setText(bundle.getString("LOG_OUT"));
        exitButton.setText(bundle.getString("EXIT"));
        infoText.setText(bundle.getString("INFO_TEXT"));
        deleteText.setText(bundle.getString("DELETE_TEXT"));
        addText.setText(bundle.getString("ADD_TEXT"));
    }

    private void displayError(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(bundle.getString("ERROR"));
        alert.setHeaderText(context.getErrorMessage(e));
        alert.showAndWait();
    }

    private void displayError(String e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(bundle.getString("ERROR"));
        alert.setHeaderText(context.getErrorMessage(e));
        alert.showAndWait();
    }

    private void displayInfo(String s) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(bundle.getString("SUCCESS"));
        alert.setHeaderText(s);
        alert.showAndWait();
    }

    private void displayInfo(Response response) {
        if (response.isSuccessful())
            displayInfo(response.getMessage());
        else
            displayError(response.getMessage());
    }

    private void execute(String command) {
        try {
            context.setCurrentCommand(command);
            Response response = context.getCommandReader().getResponse(command);
            if (response.isSuccessful())
                displayInfo(response.getMessage());
            else
                displayError(response.getMessage());
        } catch (Exception e) {
            displayError(e);
        }
    }


    @Override
    public void setContext(ControllerContext context) {
        this.context = context;
    }

    @Override
    public ControllerContext getContext() {
        return context;
    }
}
