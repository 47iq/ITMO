package client.controllers;

import client.ClientContext;
import client.reader.CommandReader;
import client.ticket.TicketBuilder;
import common.Response;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class AddController implements Initializable {
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

    private final CommandReader reader = ClientContext.getCommandReader();
    private final TicketBuilder builder = reader.getBuilder();
    public Text userText;

    public void initialize(URL location, ResourceBundle resources) {
        userText.setText(ClientContext.getCurrentUser());
        commandButton.setText(ClientContext.getCurrentCommand());
        ClientContext.initBoxes(type, eyes, hair, country);
        backButton.setOnAction(actionEvent -> {
            goBack();
        });
        commandButton.setOnAction(actionEvent -> {
            //if(counter == CONTROL_SUM) {
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

                Response response = reader.getResponse(ClientContext.getCurrentCommand());
                displayInfo(response);
                goBack();
            } catch (Exception e) {
                e.printStackTrace();
                displayError(e);
            }
            //} else{
            //    //FIXME localize
            //    displayError("Error. You must fill all of the fields.");
            //}
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
            ClientContext.showScene((Stage) backButton.getScene().getWindow(), "mainScene.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
