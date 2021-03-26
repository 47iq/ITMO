package client;

import client.controllers.Controller;
import client.controllers.ControllerContext;
import client.exceptions.ClientExceptionMessenger;
import client.messages.DefaultErrorPrinter;
import client.messages.DefaultPrinter;
import client.messages.ErrorPrinter;
import client.messages.Printer;
import common.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;


/**
 * Class which starts the program
 *
 * @version 1.0
 * @autor 47iq
 */

public class Main extends Application {

    /**
     * The entry point of the program
     *
     * @param args String[] args
     */

    private static Printer out;

    private static ErrorPrinter err;

    private static void setValidators() {
        DefaultTicket.setValidator(new DefaultTicketValidator());
        DefaultCoordinates.setValidator(new DefaultCoordinatesValidator());
        DefaultPerson.setValidator(new DefaultPersonValidator());
    }

    public void start(Stage primaryStage) throws Exception {
        setValidators();
        out = new DefaultPrinter(System.out);
        err = new DefaultErrorPrinter(System.err);
        ObjectFactory factory = new ClientContext(Locale.ENGLISH);
        makeStage("welcome.fxml", factory.getContext());
    }

    public void makeStage(String sceneFile, ControllerContext context) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(ClientContext.class.getClassLoader().getResource(sceneFile));
        Parent root = loader.load();
        Controller controller = loader.getController();
        controller.initialize(context);
        Scene scene = new Scene(root);
        stage.setTitle("DB manager");
        InputStream iconStream = ClientContext.class.getResourceAsStream("/icon.png");
        Image image = new Image(iconStream);
        stage.getIcons().add(image);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setMinHeight(400);
        stage.setMinWidth(700);
        stage.show();
    }

    public static Printer getOut() {
        return out;
    }

    public static ErrorPrinter getErr() {
        return err;
    }

    public static ClientExceptionMessenger getExceptionMessenger() {
        //FIXME
        return null;
    }
}

