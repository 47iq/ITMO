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
    public Button nameButton;
    public Text ticketText;
    public TextField price;
    public Button priceButton;
    public TextField discount;
    public Button discountButton;
    public TextField refundable;
    public Button refundableButton;
    public Text coordinateText;
    public TextField x;
    public Button xButton;
    public TextField y;
    public Button yButton;
    public Text personText;
    public TextField weight;
    public Button weightButton;
    public ChoiceBox<String> eyes;
    public Button eyesButton;
    public ChoiceBox<String> hair;
    public Button hairButton;
    public ChoiceBox<String> country;
    public Button countryButton;
    public ChoiceBox<String> type;
    public Button typeButton;
    public Button commandButton;

    private int counter = 0;
    private final CommandReader reader = ClientContext.getCommandReader();
    private final TicketBuilder builder = reader.getBuilder();
    private final int CONTROL_SUM = 11;

    public void initialize(URL location, ResourceBundle resources) {
        commandButton.setText(ClientContext.getCurrentCommand());
        ObservableList<String> types = FXCollections.observableArrayList("VIP", "CHEAP", "USUAL", "");
        type.setItems(types);
        ObservableList<String> eyeTypes = FXCollections.observableArrayList("BLACK", "BLUE", "YELLOW");
        eyes.setItems(eyeTypes);
        ObservableList<String> hairTypes = FXCollections.observableArrayList("BLACK", "GREEN", "YELLOW", "RED");
        hair.setItems(hairTypes);
        ObservableList<String> countryTypes = FXCollections.observableArrayList("RUSSIA", "CHINA", "SPAIN", "FRANCE");
        country.setItems(countryTypes);
        backButton.setOnAction(actionEvent -> {
            goBack();
        });
        nameButton.setOnAction(actionEvent -> {
            try {
                builder.setName(name.getText());
                name.setEditable(false);
                submitSuccess(nameButton);
            } catch (Exception e) {
                displayError(e);
            }
        });
        xButton.setOnAction(actionEvent -> {
            try {
                builder.setX(x.getText());
                x.setEditable(false);
                submitSuccess(xButton);
            } catch (Exception e) {
                displayError(e);
            }
        });
        yButton.setOnAction(actionEvent -> {
            try {
                builder.setY(y.getText());
                y.setEditable(false);
                submitSuccess(yButton);
            } catch (Exception e) {
                displayError(e);
            }
        });
        priceButton.setOnAction(actionEvent -> {
            try {
                builder.setPrice(price.getText());
                price.setEditable(false);
                submitSuccess(priceButton);
            } catch (Exception e) {
                displayError(e);
            }
        });
        discountButton.setOnAction(actionEvent -> {
            try {
                builder.setDiscount(discount.getText());
                discount.setEditable(false);
                submitSuccess(discountButton);
            } catch (Exception e) {
                displayError(e);
            }
        });
        refundableButton.setOnAction(actionEvent -> {
            try {
                builder.setRefundable(refundable.getText());
                refundable.setEditable(false);
                submitSuccess(refundableButton);
            } catch (Exception e) {
                displayError(e);
            }
        });
        typeButton.setOnAction(actionEvent -> {
            try {
                builder.setType(type.getValue());
                //TODO: type.setEditable false
                submitSuccess(typeButton);
            } catch (Exception e) {
                displayError(e);
            }
        });
        weightButton.setOnAction(actionEvent -> {
            try {
                builder.setWeight(weight.getText());
                weight.setEditable(false);
                submitSuccess(weightButton);
            } catch (Exception e) {
                displayError(e);
            }
        });
        eyesButton.setOnAction(actionEvent -> {
            try {
                builder.setEyeColor(eyes.getValue());
                submitSuccess(eyesButton);
            } catch (Exception e) {
                displayError(e);
            }
        });
        hairButton.setOnAction(actionEvent -> {
            try {
                builder.setHairColor(hair.getValue());
                submitSuccess(hairButton);
            } catch (Exception e) {
                displayError(e);
            }
        });
        countryButton.setOnAction(actionEvent -> {
            try {
                builder.setCountry(country.getValue());
                submitSuccess(countryButton);
            } catch (Exception e) {
                displayError(e);
            }
        });
        commandButton.setOnAction(actionEvent -> {
            if(counter == CONTROL_SUM) {
                Response response = reader.getResponse(ClientContext.getCurrentCommand());
                displayInfo(response);
                goBack();
            } else{
                //FIXME localize
                displayError("Error. You must fill all of the fields.");
            }
        });
    }

    private void submitSuccess(Button button) {
        button.setVisible(false);
        counter++;
    }

    private void exit() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
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
}
