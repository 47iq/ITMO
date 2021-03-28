package client.gui_controllers;

import common.Ticket;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.ResourceBundle;

public interface ControlManager {
    void showScene(Stage stage, String sceneFile, Controller controller);

    void initBoxes(ChoiceBox<String> type, ChoiceBox<String> eyes, ChoiceBox<String> hair, ChoiceBox<String> country);

    String getErrorMessage(Exception e, ResourceBundle bundle);

    String getErrorMessage(String e, ResourceBundle bundle);

    default String getTicketString(Ticket ticket) {
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
