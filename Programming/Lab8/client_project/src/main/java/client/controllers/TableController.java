package client.controllers;

import client.ClientContext;
import common.Ticket;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.control.TableView.CONSTRAINED_RESIZE_POLICY;

public class TableController implements Initializable {
    public ImageView imgView;
    public Button backButton;
    public AnchorPane tablePane;
    public GridPane gridPane;
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
    public TableColumn<Ticket, String>hairCollum;
    public TableColumn<Ticket, String> countryCollum;
    public TableColumn<Ticket, String> ownerCollum;
    public Text userText;
    public Text addText;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userText.setText(ClientContext.getCurrentUser());
        show();
        backButton.setOnAction(actionEvent -> {
            goBack();
        });
    }

    public void show() {
        System.out.println(ClientContext.getCommandReader().getResponse("show").getCollection());
        ObservableList<Ticket> tickets = FXCollections.observableArrayList(
                ClientContext.getCommandReader().getResponse("show").getCollection()
        );
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
        table.setItems(tickets);
    }


    private void goBack() {
        try {
            ClientContext.showScene("mainScene.fxml");
            exit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exit() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
}
