package client.gui_controllers;

import client.ClientObjectFactory;
import common.Response;
import common.Ticket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.ResourceBundle;

public class DefaultControlManager implements ControlManager {
    @Override
    public void showScene(Stage stage, String sceneFile, Controller controller) {
        try {
            FXMLLoader loader = new FXMLLoader(ClientObjectFactory.class.getClassLoader().getResource(sceneFile));
            Parent root = loader.load();
            Controller newController = loader.getController();
            ControllerContext context = controller.getContext();
            newController.initialize(context);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            LogManager.getLogger().error("Can't show scene.");
        }
    }

    @Override
    public void initBoxes(ChoiceBox<String> type, ChoiceBox<String> eyes, ChoiceBox<String> hair, ChoiceBox<String> country) {
        ObservableList<String> types = FXCollections.observableArrayList("VIP", "CHEAP", "USUAL", "");
        type.setItems(types);
        ObservableList<String> eyeTypes = FXCollections.observableArrayList("BLACK", "BLUE", "YELLOW");
        eyes.setItems(eyeTypes);
        ObservableList<String> hairTypes = FXCollections.observableArrayList("BLACK", "GREEN", "YELLOW", "RED");
        hair.setItems(hairTypes);
        ObservableList<String> countryTypes = FXCollections.observableArrayList("RUSSIA", "CHINA", "SPAIN", "FRANCE");
        country.setItems(countryTypes);
    }

    @Override
    public String getErrorMessage(Exception e, ResourceBundle bundle) {
        if (bundle.containsKey(e.getMessage()))
            return bundle.getString(e.getMessage());
        else
            return bundle.getString("ERR_UNK");
    }

    @Override
    public String getErrorMessage(String e, ResourceBundle bundle) {
        if (bundle.containsKey(e))
            return bundle.getString(e);
        else
            return bundle.getString("ERR_UNK");
    }

    @Override
    public void displayError(Exception e, ResourceBundle bundle) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(bundle.getString("ERROR"));
        alert.setHeaderText(getErrorMessage(e, bundle));
        alert.showAndWait();
    }

    @Override
    public void displayError(String e, ResourceBundle bundle) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(bundle.getString("ERROR"));
        alert.setHeaderText(getErrorMessage(e, bundle));
        alert.showAndWait();
    }

    @Override
    public void displayInfo(Response response, ResourceBundle bundle) {
        if (response.isSuccessful())
            if (response.getMessage() != null)
                displayInfo(response.getMessage(), bundle);
            else
                displayInfo(bundle.getString("SUCCESS"), bundle);
        else
            displayError(response.getMessage(), bundle);
    }

    @Override
    public void displayInfo(String s, ResourceBundle bundle) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(bundle.getString("SUCCESS"));
        if(s.equals(""))
            s = bundle.getString("SUCCESS");
        alert.setHeaderText(s);
        alert.showAndWait();
    }

    @Override
    public String getTicketString(Ticket ticket) {
        String message = "";
        ResourceBundle bundle = null;
        try {
            File file = new File("/");
            URL[] urls = {file.toURI().toURL()};
            ClassLoader loader = new URLClassLoader(urls);
            try {
                Locale locale = Locale.getDefault();
                bundle = ResourceBundle.getBundle("messages", locale, loader);
            } catch (Exception e) {
                Locale locale = new Locale("en", "EN");
                bundle = ResourceBundle.getBundle("messages", locale, loader);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        message += bundle.getString("ID") + ": " + ticket.getId() + "\n" +
                bundle.getString("OWNER") + ": " + ticket.getOwner() + "\n" +
                bundle.getString("NAME") + ": " + ticket.getName() + "\n" +
                bundle.getString("PRICE") + ": " + ticket.getPrice() + "\n" +
                bundle.getString("DISCOUNT") + ": " + ticket.getDiscount() + "\n" +
                bundle.getString("TYPE") + ": " + ticket.getType() + "\n" +
                bundle.getString("DATE") + ": " + ticket.getCreationDate() + "\n" +
                bundle.getString("REFUNDABLE") + ": " + ticket.getRefundable() + "\n" +
                bundle.getString("X") + ": " + ticket.getX() + "\n" +
                bundle.getString("Y") + ": " + ticket.getY() + "\n" +
                bundle.getString("WEIGHT") + ": " + ticket.getWeight() + "\n" +
                bundle.getString("EYES") + ": " + ticket.getEyeColor() + "\n" +
                bundle.getString("HAIR") + ": " + ticket.getHairColor() + "\n" +
                bundle.getString("REFUNDABLE") + ": " + ticket.getRefundable() + "\n" +
                bundle.getString("NATIONALITY") + ": " + ticket.getNationality() + "\n\n";
        return message;
    }
}
