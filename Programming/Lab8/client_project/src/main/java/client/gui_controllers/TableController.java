package client.gui_controllers;

import common.Ticket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;
import java.util.ResourceBundle;

public class TableController implements Controller {
    public ImageView imgView;
    public Button backButton;
    public TableView<Ticket> table;
    public TableColumn<Ticket, Integer> idCollum;
    public TableColumn<Ticket, String> nameCollum;
    public TableColumn<Ticket, Integer> priceCollum;
    public TableColumn<Ticket, Double> discountCollum;
    public TableColumn<Ticket, Boolean> refundableCollum;
    public TableColumn<Ticket, Double> xCollum;
    public TableColumn<Ticket, Integer> yCollum;
    public TableColumn<Ticket, String> typeCollum;
    public TableColumn<Ticket, Long> weightCollum;
    public TableColumn<Ticket, String> eyesCollum;
    public TableColumn<Ticket, String> hairCollum;
    public TableColumn<Ticket, String> countryCollum;
    public TableColumn<Ticket, String> ownerCollum;
    public TableColumn<Ticket, String> dateCollum;
    public Text userText;
    public Text addText;
    public Button filterButton;
    public Button resetButton;


    private List<Ticket> tickets;
    private ControllerContext context;
    private ControlManager controlManager;
    private ResourceBundle bundle;

    @Override
    public void initialize(ControllerContext context) {
        this.context = context;
        controlManager = context.getControlManager();
        tickets = context.getCurrentCollection();
        userText.setText(context.getCurrentUser());
        bundle = context.getBundle();
        localize();
        idCollum.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCollum.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceCollum.setCellValueFactory(new PropertyValueFactory<>("price"));
        discountCollum.setCellValueFactory(new PropertyValueFactory<>("discount"));
        refundableCollum.setCellValueFactory(new PropertyValueFactory<>("refundable"));
        xCollum.setCellValueFactory(new PropertyValueFactory<>("x"));
        yCollum.setCellValueFactory(new PropertyValueFactory<>("y"));
        typeCollum.setCellValueFactory(new PropertyValueFactory<>("type"));
        weightCollum.setCellValueFactory(new PropertyValueFactory<>("weight"));
        eyesCollum.setCellValueFactory(new PropertyValueFactory<>("eyeColor"));
        hairCollum.setCellValueFactory(new PropertyValueFactory<>("hairColor"));
        countryCollum.setCellValueFactory(new PropertyValueFactory<>("nationality"));
        ownerCollum.setCellValueFactory(new PropertyValueFactory<>("owner"));
        dateCollum.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
        show();
        backButton.setOnAction(actionEvent -> {
            goBack();
        });
        filterButton.setOnAction(actionEvent -> {
            filter();
        });
        resetButton.setOnAction(actionEvent -> {
            tickets = context.getCommandReader().getResponse("show").getCollection();
            show();
        });
    }

    private void localize() {
        backButton.setText(bundle.getString("BACK"));
        filterButton.setText(bundle.getString("FILTER"));
        resetButton.setText(bundle.getString("RESET"));
        idCollum.setText(bundle.getString("ID"));
        nameCollum.setText(bundle.getString("NAME"));
        priceCollum.setText(bundle.getString("PRICE"));
        discountCollum.setText(bundle.getString("DISCOUNT"));
        refundableCollum.setText(bundle.getString("REFUNDABLE"));
        typeCollum.setText(bundle.getString("TYPE"));
        eyesCollum.setText(bundle.getString("EYES"));
        hairCollum.setText(bundle.getString("HAIR"));
        countryCollum.setText(bundle.getString("NATIONALITY"));
        xCollum.setText(bundle.getString("X"));
        yCollum.setText(bundle.getString("Y"));
        ownerCollum.setText(bundle.getString("OWNER"));
        dateCollum.setText(bundle.getString("DATE"));
        weightCollum.setText(bundle.getString("WEIGHT"));
    }

    public void show() {
        ObservableList<Ticket> ticketItems = FXCollections.observableArrayList(tickets);
        table.setItems(ticketItems);
        table.setRowFactory(tv -> {
            TableRow<Ticket> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Ticket ticket = row.getItem();
                    if (ticket.getOwner().equals(context.getCurrentUser())) {
                        context.setUpdateArg(ticket);
                        context.setPrevScene("table.fxml");
                        controlManager.showScene((Stage) backButton.getScene().getWindow(), "update.fxml", this);
                    }
                }
            });
            return row;
        });
    }

    private void filter() {
        context.setCurrentCollection(tickets);
        controlManager.showScene((Stage) backButton.getScene().getWindow(), "filter.fxml", this);
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
