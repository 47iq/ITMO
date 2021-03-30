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
            goBack();
        });
        commandButton.setOnAction(actionEvent -> {
            if (arg.getText().equals(""))
                controlManager.displayError("All fields must be filled.", bundle);
            else {
                Response response = context.getCommandReader().getResponse(curCommand + " " + arg.getText());
                controlManager.displayInfo(response, bundle);
                goBack();
            }
        });
    }

    private void localize() {
        backButton.setText(bundle.getString("BACK"));
        arg.setPromptText(bundle.getString("ARG"));
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
