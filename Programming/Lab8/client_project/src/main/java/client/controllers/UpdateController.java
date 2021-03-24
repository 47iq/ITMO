package client.controllers;

import client.ClientContext;
import client.reader.CommandReader;
import client.ticket.TicketBuilder;
import common.Response;
import common.UpdateData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UpdateController implements Initializable {
    public Button backButton;

    public TextField name;
    public TextField price;
    public TextField discount;
    public TextField refundable;
    public TextField x;
    public TextField y;
    public TextField weight;
    public ChoiceBox<String> eyes;
    public ChoiceBox<String> hair;
    public ChoiceBox<String> country;
    public ChoiceBox<String> type;
    public TextField id;
    public ImageView imgView;
    public Text addText;
    public Button updateButton;
    public CheckBox countryBox;
    public CheckBox hairBox;
    public CheckBox eyesBox;
    public CheckBox weightBox;
    public CheckBox yBox;
    public CheckBox xBox;
    public CheckBox typeBox;
    public CheckBox refundableBox;
    public CheckBox discountBox;
    public CheckBox priceBox;
    public CheckBox nameBox;

    private final CommandReader reader = ClientContext.getCommandReader();
    private final TicketBuilder builder = reader.getBuilder();
    private final UpdateData updateData = reader.getUpdateData();
    public Text userText;

    public void initialize(URL location, ResourceBundle resources) {
        userText.setText(ClientContext.getCurrentUser());
        ClientContext.initBoxes(type, eyes, hair, country);
        backButton.setOnAction(actionEvent -> {
            goBack();
        });
        updateButton.setOnAction(actionEvent -> {
            try {
                builder.reset();
                if (nameBox.isSelected()) {
                    builder.setName(name.getText());
                    updateData.setNameSelected();
                }
                if (priceBox.isSelected()) {
                    builder.setPrice(price.getText());
                    updateData.setPriceSelected();
                }
                if (discountBox.isSelected()) {
                    builder.setDiscount(discount.getText());
                    updateData.setDiscountSelected();
                }
                if (refundableBox.isSelected()) {
                    builder.setRefundable(refundable.getText());
                    updateData.setRefundableSelected();
                }
                if (typeBox.isSelected()) {
                    builder.setType(type.getValue());
                    updateData.setTypeSelected();
                }
                if (xBox.isSelected()) {
                    builder.setX(x.getText());
                    updateData.setXSelected();
                }
                if (yBox.isSelected()) {
                    builder.setY(y.getText());
                    updateData.setYSelected();
                }
                if (weightBox.isSelected()) {
                    builder.setWeight(weight.getText());
                    updateData.setWeightSelected();
                }
                if (eyesBox.isSelected()) {
                    builder.setEyeColor(eyes.getValue());
                    updateData.setEyeColorSelected();
                }
                if (hairBox.isSelected()) {
                    builder.setHairColor(hair.getValue());
                    updateData.setHairColorSelected();
                }
                if (countryBox.isSelected()) {
                    builder.setCountry(country.getValue());
                    updateData.setCountrySelected();
                }
                try {
                    int check = Integer.parseInt(id.getText());
                } catch (Exception e) {
                    //FIXME
                    displayError("Invalid id type. ID must be int.");
                    return;
                }
                Response response = reader.getResponse("update " + id.getText());
                displayInfo(response);
                goBack();
            } catch (Exception e) {
                displayError(e);
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
            ClientContext.showScene((Stage) backButton.getScene().getWindow(),"mainScene.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
