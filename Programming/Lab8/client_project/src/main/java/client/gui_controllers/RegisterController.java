package client.gui_controllers;

import common.Response;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class RegisterController implements Controller {
    public ImageView imgView;
    public Label login;
    public TextField loginField;
    public PasswordField passwordField;
    public Label pass;
    public Button submitButton;
    public Button backButton;
    public PasswordField passwordFieldConf;
    public Label passConf;

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
            String passwordConfirmation = passwordFieldConf.getText();
            if (fieldsFilled(login, password)) {
                if (passwordsMatch(password, passwordConfirmation)) {
                    Response response = context.getCommandReader().getResponse(String.format("register %s %s", login, password));
                    if (response.isSuccessful()) {
                        context.setCurrentUser(login);
                        success();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(response.getMessage());
                        alert.showAndWait();
                    }
                } else
                    displayError("ERR_PASSWORDS_MATCH");
            } else
                displayError("ERR_FIELDS_EMPTY");
        });
        backButton.setOnAction(actionEvent -> {
            context.getControlManager().showScene((Stage) backButton.getScene().getWindow(), "welcome.fxml", this);
        });
    }

    private void localize() {
        backButton.setText(bundle.getString("BACK"));
        submitButton.setText(bundle.getString("SUBMIT"));
        login.setText(bundle.getString("LOGIN"));
        pass.setText(bundle.getString("PASSWORD"));
        loginField.setPromptText(bundle.getString("ENTER_LOGIN"));
        passwordField.setPromptText(bundle.getString("ENTER_PASSWORD"));
        passConf.setText(bundle.getString("PASSWORD_CONF"));
        passwordFieldConf.setPromptText(bundle.getString("CONFIRM_PASSWORD"));
    }

    private boolean fieldsFilled(String login, String password) {
        return !login.equals("") && !password.equals("");
    }

    private boolean passwordsMatch(String passwordConf, String password) {
        return password.equals(passwordConf);
    }

    private void success() {
        controlManager.showScene((Stage) backButton.getScene().getWindow(), "mainScene.fxml", this);
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

    @Override
    public void setContext(ControllerContext context) {
        this.context = context;
    }

    @Override
    public ControllerContext getContext() {
        return context;
    }
}
