package client.gui_controllers;

import common.Response;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class ArgController implements Controller {
    public Button backButton;
    public ImageView imgView;
    public Button commandButton;
    public TextField arg;
    public Text userText;

    private ControllerContext context;
    private String curCommand;
    private ControlManager controlManager;
    private ResourceBundle bundle;

    @Override
    public void initialize(ControllerContext context) {
        this.context = context;
        controlManager = context.getControlManager();
        curCommand = context.getCurrentCommand();
        userText.setText(context.getCurrentUser());
        commandButton.setText(curCommand);
        bundle = context.getBundle();
        localize();
        backButton.setOnAction(actionEvent -> {
            controlManager.showScene((Stage) backButton.getScene().getWindow(), "mainScene.fxml", this);
        });
        commandButton.setOnAction(actionEvent -> {
            if (arg.getText().equals(""))
                displayError("All fields must be filled.");
            else {
                Response response = context.getCommandReader().getResponse(curCommand + " " + arg.getText());
                displayInfo(response);
            }
        });
    }

    private void localize() {
        backButton.setText("BACK");
        arg.setPromptText("ARG");
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

    private void displayInfo(Response response) {
        if (response.isSuccessful())
            displayInfo(response.getMessage());
        else
            displayError(response.getMessage());
    }

    private void displayInfo(String s) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(bundle.getString("SUCCESS"));
        if(s.equals(""))
            s = bundle.getString("SUCCESS");
        alert.setHeaderText(s);
        alert.showAndWait();
    }

    private void goBack() {
        controlManager.showScene((Stage) backButton.getScene().getWindow(), "mainScene.fxml", this);
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
