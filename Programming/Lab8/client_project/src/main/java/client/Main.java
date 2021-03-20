package client;

import client.command_manager.ClientCommandFactory;
import client.command_manager.CommandFactory;
import client.commands.*;
import client.connection.DefaultConnectionManager;
import client.connection.ConnectionManager;
import client.controllers.MainController;
import client.exceptions.NotEnoughAgrsException;
import client.exceptions.TerminalException;
import client.messages.*;
import common.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import client.exceptions.ClientExceptionMessenger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;


/**
 * Class which starts the program
 * @autor 47iq
 * @version 1.0
 */

public class Main extends Application {

    /**
     * The entry point of the program
     * @param args String[] args
     */

    private static Printer out;

    private static ErrorPrinter err;

    private static ClientExceptionMessenger exceptionMessenger;

    private static void setValidators() {
        DefaultTicket.setValidator(new DefaultTicketValidator());
        DefaultCoordinates.setValidator(new DefaultCoordinatesValidator());
        DefaultPerson.setValidator(new DefaultPersonValidator());
    }

    public void start(Stage primaryStage) throws Exception {
        ObjectFactory objectFactory = new ClientContext(new Locale("en", "EN"));
        ClientContext.setCommandReader(objectFactory.getControllerCommandReader());
        setValidators();
        exceptionMessenger = objectFactory.getLocalErrorHandler();
        out = new DefaultPrinter(System.out);
        err = new DefaultErrorPrinter(System.err);
        runUI(primaryStage);
    }

    public static Printer getOut() {
        return out;
    }

    public static ErrorPrinter getErr() {
        return err;
    }

    public static ClientExceptionMessenger getExceptionMessenger() {
        return exceptionMessenger;
    }

    private void runUI(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/welcome.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root));
        loader.setController(new MainController());
        primaryStage.setTitle("DataBase manager");
        InputStream iconStream = getClass().getResourceAsStream("/icon.png");
        Image image = new Image(iconStream);
        primaryStage.getIcons().add(image);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}

