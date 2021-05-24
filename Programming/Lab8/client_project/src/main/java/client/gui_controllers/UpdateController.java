package client.gui_controllers;

import client.reader.CommandReader;
import client.ticket.TicketBuilder;
import common.Response;
import common.Ticket;
import common.TicketType;
import common.UpdateData;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class UpdateController implements Controller {
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
    public Text userText;

    private ControllerContext context;
    private CommandReader reader;
    private TicketBuilder builder;
    private UpdateData updateData;
    private ControlManager controlManager;
    private ResourceBundle bundle;

    public void initialize(ControllerContext context) {
        this.context = context;
        Ticket argument = context.getResetUpdateArg();
        if (argument != null) {
            id.setText(String.valueOf(argument.getId()));
            initFields(argument);
        }
        controlManager = context.getControlManager();
        reader = context.getCommandReader();
        builder = reader.getBuilder();
        updateData = reader.getUpdateData();
        bundle = context.getBundle();
        userText.setText(context.getCurrentUser());
        controlManager.initBoxes(type, eyes, hair, country);
        localize();
        loadTicket();
        backButton.setOnAction(actionEvent -> {
            goBack();
        });
        id.setOnKeyReleased(x -> {
            loadTicket();
        });
        updateButton.setOnAction(actionEvent -> {
            try {
                builder.reset();
                builder.setName(name.getText());
                updateData.setNameSelected();
                builder.setPrice(price.getText());
                updateData.setPriceSelected();
                builder.setDiscount(discount.getText());
                updateData.setDiscountSelected();
                builder.setRefundable(refundable.getText());
                updateData.setRefundableSelected();
                builder.setType(type.getValue());
                updateData.setTypeSelected();
                builder.setX(x.getText());
                updateData.setXSelected();
                builder.setY(y.getText());
                updateData.setYSelected();
                builder.setWeight(weight.getText());
                updateData.setWeightSelected();
                builder.setEyeColor(eyes.getValue());
                updateData.setEyeColorSelected();
                builder.setHairColor(hair.getValue());
                updateData.setHairColorSelected();
                builder.setCountry(country.getValue());
                updateData.setCountrySelected();
                try {
                    int check = Integer.parseInt(id.getText());
                } catch (Exception e) {
                    controlManager.displayError("ERR_INVALID_ID", bundle);
                    return;
                }
                Response response = reader.getResponse("update " + id.getText());
                controlManager.displayInfo(response, bundle);
                goBack();
            } catch (Exception e) {
                controlManager.displayError(e, bundle);
            }
        });
    }

    private void loadTicket() {
        String idText = id.getText();
        try {
            int id = Integer.parseInt(idText);
            Optional<Ticket> ticket = getCollection().stream().filter(y -> y.getId() == id && y.getOwner().equals(context.getCurrentUser())).findAny();
            ticket.ifPresent(this::initFields);
        } catch (Exception ignored) {
        }
    }

    private List<Ticket> getCollection() {
        Response response = reader.getResponse("show");
        return response.getCollection();
    }

    private void localize() {
        backButton.setText(bundle.getString("BACK"));
        name.setPromptText(bundle.getString("NAME"));
        price.setPromptText(bundle.getString("PRICE"));
        discount.setPromptText(bundle.getString("DISCOUNT"));
        refundable.setPromptText(bundle.getString("REFUNDABLE"));
        weight.setPromptText(bundle.getString("WEIGHT"));
        x.setPromptText(bundle.getString("X"));
        y.setPromptText(bundle.getString("Y"));
        updateButton.setText(bundle.getString("UPDATE_FIELDS"));
    }

    private void initFields(Ticket argument) {
        name.setText(argument.getName());
        price.setText(String.valueOf(argument.getPrice()));
        discount.setText(String.valueOf(argument.getDiscount()));
        Boolean refundableVal = argument.getRefundable();
        if (refundableVal == null)
            refundable.setText("");
        else
            refundable.setText(String.valueOf(refundableVal));
        TicketType typeVal = argument.getType();
        if (typeVal == null)
            type.setValue("");
        else
            type.setValue(typeVal.name());
        x.setText(String.valueOf(argument.getCoordinates().getX()));
        y.setText(String.valueOf(argument.getCoordinates().getY()));
        hair.setValue(argument.getPerson().getHairColor().name());
        eyes.setValue(argument.getPerson().getEyeColor().name());
        country.setValue(argument.getPerson().getNationality().name());
        Long weightVal = argument.getWeight();
        if (weightVal == null)
            weight.setText("");
        else
            weight.setText(String.valueOf(weightVal));
    }

    private void goBack() {
        if (context.getPrevScene().equals("table.fxml"))
            context.setCurrentCollection(reader.getResponse("show").getCollection());
        controlManager.showScene((Stage) backButton.getScene().getWindow(), context.getPrevScene(), this);
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
