package client.gui_controllers;

import client.reader.CommandReader;
import client.ticket.TicketBuilder;
import common.Response;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class AddController implements Controller {
    public Button backButton;
    public Text addText;
    public ImageView imgView;
    public TextField name;
    public Text ticketText;
    public TextField price;
    public TextField discount;
    public TextField refundable;
    public Text coordinateText;
    public TextField x;
    public TextField y;
    public Text personText;
    public TextField weight;
    public ChoiceBox<String> eyes;
    public ChoiceBox<String> hair;
    public ChoiceBox<String> country;
    public ChoiceBox<String> type;
    public Button commandButton;
    public Text nameText;
    public Text priceText;
    public Text discountText;
    public Text refundableText;
    public Text typeText;
    public Text yText;
    public Text weightText;
    public Text eyesText;
    public Text hairText;
    public Text countryText;
    public Text xText;

    private ControllerContext context;
    private CommandReader reader;
    private TicketBuilder builder;
    public Text userText;
    private ControlManager controlManager;
    private ResourceBundle bundle;

    public void initialize(ControllerContext context) {
        this.context = context;
        reader = context.getCommandReader();
        builder = reader.getBuilder();
        controlManager = context.getControlManager();
        userText.setText(context.getCurrentUser());
        bundle = context.getBundle();
        commandButton.setText(context.getCurrentCommand());
        controlManager.initBoxes(type, eyes, hair, country);
        localize();
        backButton.setOnAction(actionEvent -> {
            goBack();
        });
        commandButton.setOnAction(actionEvent -> {
            try {
                builder.setName(name.getText());
                builder.setPrice(price.getText());
                builder.setDiscount(discount.getText());
                builder.setRefundable(refundable.getText());
                builder.setType(type.getValue());
                builder.setX(x.getText());
                builder.setY(y.getText());
                builder.setWeight(weight.getText());
                builder.setEyeColor(eyes.getValue());
                builder.setHairColor(hair.getValue());
                builder.setCountry(country.getValue());
                Response response = reader.getResponse(context.getCurrentCommand());
                displayInfo(response);
                goBack();
            } catch (Exception e) {
                e.printStackTrace();
                displayError(e);
            }
        });
    }

    private void localize() {
        backButton.setText(bundle.getString("BACK"));
        nameText.setText(bundle.getString("NAME"));
        name.setPromptText(bundle.getString("NAME"));
        priceText.setText(bundle.getString("PRICE"));
        price.setPromptText(bundle.getString("PRICE"));
        discountText.setText(bundle.getString("DISCOUNT"));
        discount.setPromptText(bundle.getString("DISCOUNT"));
        weightText.setText(bundle.getString("WEIGHT"));
        weight.setPromptText(bundle.getString("WEIGHT"));
        xText.setText(bundle.getString("X"));
        x.setPromptText(bundle.getString("X"));
        yText.setText(bundle.getString("Y"));
        y.setPromptText(bundle.getString("Y"));
        refundableText.setText(bundle.getString("REFUNDABLE"));
        refundable.setPromptText(bundle.getString("REFUNDABLE"));
        typeText.setText(bundle.getString("TYPE"));
        countryText.setText(bundle.getString("NATIONALITY"));
        hairText.setText(bundle.getString("HAIR"));
        eyesText.setText(bundle.getString("EYES"));
        coordinateText.setText(bundle.getString("COORDINATES"));
        personText.setText(bundle.getString("PERSON"));
        ticketText.setText(bundle.getString("TICKET"));
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
            if (response.getMessage() != null)
                displayInfo(response.getMessage());
            else
                displayInfo(bundle.getString("SUCCESS"));
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
