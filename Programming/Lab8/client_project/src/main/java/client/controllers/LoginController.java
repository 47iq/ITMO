package client.controllers;

import common.Response;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class LoginController implements Controller {
    public Button submitButton;
    public PasswordField passwordField;
    public Label pass;
    public TextField loginField;
    public Label login;
    public ImageView imgView;
    public Button backButton;

    private ControllerContext context;
    private ControlManager controlManager;
    private ResourceBundle bundle;

    @Override
    public void initialize(ControllerContext context) {
        this.context = context;
        controlManager = context.getControlManager();
        bundle = context.getBundle();
        localize();
        submitButton.setOnAction(actionEvent -> {
            String login = loginField.getText();
            String password = passwordField.getText();
            if (!login.equals("") && !password.equals("")) {
                Response response = context.getCommandReader().getResponse(String.format("login %s %s", login, password));
                if (response.isSuccessful()) {
                    context.setCurrentUser(login);
                    success();
                } else {
                    displayError(response.getMessage());
                }
            } else {
                displayError("ERR_FIELDS_EMPTY");
            }
        });
        backButton.setOnAction(actionEvent -> {
            controlManager.showScene((Stage) backButton.getScene().getWindow(), "welcome.fxml", this);
        });
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

    private void localize() {
        pass.setText(bundle.getString("PASSWORD"));
        login.setText(bundle.getString("LOGIN"));
        loginField.setPromptText(bundle.getString("ENTER_LOGIN"));
        passwordField.setPromptText(bundle.getString("ENTER_PASSWORD"));
        backButton.setText(bundle.getString("BACK"));
        submitButton.setText(bundle.getString("SUBMIT"));
    }

    private void success() {
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
