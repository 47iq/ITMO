package client.controllers;

import client.ClientContext;
import common.Response;
import common.Ticket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class FilterController implements Initializable {

    public Text userText;
    public Button backButton;
    public Text addText;
    public TextField arg;
    public Button higherButton;
    public Button lowerButton;
    public ChoiceBox<String> command;

    private List<Ticket> tickets = ClientContext.getCurrentCollection();

    private enum FilterCondition {
        HIGHER,
        LOWER;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //FIXME
        ObservableList<String> fields = FXCollections.observableArrayList("id", "price", "discount", "type",
                "refundable", "x", "y", "weight", "hair_color", "eye_color", "nationality");
        command.setItems(fields);
        higherButton.setOnAction(actionEvent -> {
            try {
                filter(command.getValue(), FilterCondition.HIGHER, arg.getText());
                success();
            } catch (Exception e) {
                displayError(e);
            }
        });
        lowerButton.setOnAction(actionEvent -> {
            try {
                filter(command.getValue(), FilterCondition.LOWER, arg.getText());
                success();
            } catch (Exception e) {
                displayError(e);
            }
        });
        backButton.setOnAction(actionEvent -> {
            goBack();
        });
    }

    private void success() {
        try {
            displayInfo("Success");
            ClientContext.setCurrentCollection(tickets);
            ClientContext.showScene((Stage) backButton.getScene().getWindow(), "table.fxml");
        } catch (IOException e) {
            //FIXME
            e.printStackTrace();
        }
    }

    private void filter(String field, FilterCondition condition, String argument) {
        int sign = 1;
        if (condition.equals(FilterCondition.LOWER))
            sign *= -1;
        int finalSign = sign;
        switch (field) {
            case "id" -> {
                tickets = tickets.stream().filter(x -> x.getId() * finalSign > Integer.parseInt(argument) * finalSign).collect(Collectors.toList());
            }
            case "price" -> {
                tickets = tickets.stream().filter(x -> x.getPrice() * finalSign > Integer.parseInt(argument) * finalSign).collect(Collectors.toList());
            }
            case "discount" -> {
                tickets = tickets.stream().filter(x -> x.getDiscount() * finalSign > Double.parseDouble(argument) * finalSign).collect(Collectors.toList());
            }
            case "type" -> {
                tickets = tickets.stream().filter(x -> x.getType().toString().compareTo(argument) * finalSign > 0).collect(Collectors.toList());
            }
            case "hair_color" -> {
                tickets = tickets.stream().filter(x -> x.getPerson().getHairColor().toString().compareTo(argument) * finalSign > 0).collect(Collectors.toList());
            }
            case "eye_color" -> {
                tickets = tickets.stream().filter(x -> x.getPerson().getEyeColor().toString().compareTo(argument) * finalSign > 0).collect(Collectors.toList());
            }
            case "nationality" -> {
                tickets = tickets.stream().filter(x -> x.getPerson().getNationality().toString().compareTo(argument) * finalSign > 0).collect(Collectors.toList());
            }
            case "x" -> {
                tickets = tickets.stream().filter(x -> x.getX() * finalSign > Double.parseDouble(argument) * finalSign).collect(Collectors.toList());
            }
            case "y" -> {
                tickets = tickets.stream().filter(x -> x.getY() * finalSign > Integer.parseInt(argument) * finalSign).collect(Collectors.toList());
            }
            case "weight" -> {
                tickets = tickets.stream().filter(x -> x.getWeight() * finalSign > Long.parseLong(argument) * finalSign).collect(Collectors.toList());
            }
            case "refundable" -> {
                tickets = tickets.stream().filter(x -> x.getRefundable().compareTo(Boolean.parseBoolean(argument)) * finalSign > 0).collect(Collectors.toList());
            }
            default -> throw new RuntimeException("Unknown error");
        }
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

    private void goBack() {
        try {
            ClientContext.showScene((Stage) backButton.getScene().getWindow(), "table.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
