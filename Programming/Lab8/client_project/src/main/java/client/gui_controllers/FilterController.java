package client.gui_controllers;

import common.Ticket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class FilterController implements Controller {

    public Text userText;
    public Button backButton;
    public Text addText;
    public TextField arg;
    public Button higherButton;
    public Button lowerButton;
    public ChoiceBox<String> command;
    public Text typeText;

    private ControllerContext context;
    private List<Ticket> tickets;
    private ControlManager controlManager;
    private ResourceBundle bundle;
    private final Map<String, String> fieldMap = new HashMap<>();

    private enum FilterCondition {
        HIGHER,
        LOWER;
    }

    @Override
    public void initialize(ControllerContext context) {
        this.context = context;
        controlManager = context.getControlManager();
        tickets = context.getCurrentCollection();
        bundle = context.getBundle();
        localize();
        userText.setText(context.getCurrentUser());
        ObservableList<String> fields = FXCollections.observableArrayList(bundle.getString("ID"),
                bundle.getString("PRICE"), bundle.getString("DISCOUNT"), bundle.getString("TYPE"),
                bundle.getString("REFUNDABLE"), bundle.getString("X"), bundle.getString("Y"),
                bundle.getString("WEIGHT"), bundle.getString("HAIR"), bundle.getString("EYES"),
                bundle.getString("NATIONALITY"), bundle.getString("OWNER"));
        command.setItems(fields);
        higherButton.setOnAction(actionEvent -> {
            try {
                filter(fieldMap.get(command.getValue()), FilterCondition.HIGHER, arg.getText());
                success();
            } catch (Exception e) {
                controlManager.displayError(e, bundle);
            }
        });
        lowerButton.setOnAction(actionEvent -> {
            try {
                filter(fieldMap.get(command.getValue()), FilterCondition.LOWER, arg.getText());
                success();
            } catch (Exception e) {
                controlManager.displayError(e, bundle);
            }
        });
        backButton.setOnAction(actionEvent -> {
            goBack();
        });
    }

    private void localize() {
        backButton.setText(bundle.getString("BACK"));
        arg.setPromptText(bundle.getString("ARG"));
        higherButton.setText(bundle.getString("HIGHER"));
        lowerButton.setText(bundle.getString("LOWER"));
        typeText.setText(bundle.getString("FILTER_TYPE"));
        fieldMap.put(bundle.getString("ID"), "id");
        fieldMap.put(bundle.getString("NAME"), "name");
        fieldMap.put(bundle.getString("PRICE"), "price");
        fieldMap.put(bundle.getString("DISCOUNT"), "discount");
        fieldMap.put(bundle.getString("REFUNDABLE"), "refundable");
        fieldMap.put(bundle.getString("TYPE"), "type");
        fieldMap.put(bundle.getString("HAIR"), "hair_color");
        fieldMap.put(bundle.getString("EYES"), "eye_color");
        fieldMap.put(bundle.getString("NATIONALITY"), "nationality");
        fieldMap.put(bundle.getString("OWNER"), "owner");
        fieldMap.put(bundle.getString("WEIGHT"), "weight");
        fieldMap.put(bundle.getString("X"), "x");
        fieldMap.put(bundle.getString("Y"), "y");
    }

    private void success() {
        controlManager.displayInfo(bundle.getString("SUCCESS"), bundle);
        context.setCurrentCollection(tickets);
        controlManager.showScene((Stage) backButton.getScene().getWindow(), "table.fxml", this);
    }

    private void filter(String field, FilterCondition condition, String argument) {
        int sign = 1;
        if (condition.equals(FilterCondition.LOWER))
            sign *= -1;
        int finalSign = sign;
        switch (field) {
            case "id" -> {
                tickets = tickets.stream().filter(x -> x.getId() * finalSign >= Integer.parseInt(argument) * finalSign).collect(Collectors.toList());
            }
            case "price" -> {
                tickets = tickets.stream().filter(x -> x.getPrice() * finalSign >= Integer.parseInt(argument) * finalSign).collect(Collectors.toList());
            }
            case "discount" -> {
                tickets = tickets.stream().filter(x -> x.getDiscount() * finalSign >= Double.parseDouble(argument) * finalSign).collect(Collectors.toList());
            }
            case "type" -> {
                tickets = tickets.stream().filter(x -> x.getType().toString().compareTo(argument) * finalSign >= 0).collect(Collectors.toList());
            }
            case "hair_color" -> {
                tickets = tickets.stream().filter(x -> x.getPerson().getHairColor().toString().compareTo(argument) * finalSign >= 0).collect(Collectors.toList());
            }
            case "eye_color" -> {
                tickets = tickets.stream().filter(x -> x.getPerson().getEyeColor().toString().compareTo(argument) * finalSign >= 0).collect(Collectors.toList());
            }
            case "nationality" -> {
                tickets = tickets.stream().filter(x -> x.getPerson().getNationality().toString().compareTo(argument) * finalSign >= 0).collect(Collectors.toList());
            }
            case "x" -> {
                tickets = tickets.stream().filter(x -> x.getX() * finalSign >= Double.parseDouble(argument) * finalSign).collect(Collectors.toList());
            }
            case "y" -> {
                tickets = tickets.stream().filter(x -> x.getY() * finalSign >= Integer.parseInt(argument) * finalSign).collect(Collectors.toList());
            }
            case "weight" -> {
                tickets = tickets.stream().filter(x -> x.getWeight() * finalSign >= Long.parseLong(argument) * finalSign).collect(Collectors.toList());
            }
            case "refundable" -> {
                tickets = tickets.stream().filter(x -> x.getRefundable().compareTo(Boolean.parseBoolean(argument)) * finalSign >= 0).collect(Collectors.toList());
            }
            case "owner" -> {
                tickets = tickets.stream().filter(x -> x.getOwner().compareTo(argument) * finalSign >= 0).collect(Collectors.toList());
            }
            default -> throw new RuntimeException("Unknown error");
        }
    }

    private void goBack() {
        controlManager.showScene((Stage) backButton.getScene().getWindow(), "table.fxml", this);
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
