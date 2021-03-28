package client;

import client.gui_controllers.Controller;
import client.gui_controllers.ControllerContext;
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

    private static void setValidators() {
        DefaultTicket.setValidator(new DefaultTicketValidator());
        DefaultCoordinates.setValidator(new DefaultCoordinatesValidator());
        DefaultPerson.setValidator(new DefaultPersonValidator());
    }

    public void start(Stage primaryStage) throws Exception {
        setValidators();
        ObjectFactory factory = new ClientObjectFactory(Locale.ENGLISH);
        makeStage("welcome.fxml", factory.getContext());
    }

    public void makeStage(String sceneFile, ControllerContext context) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(ClientObjectFactory.class.getClassLoader().getResource(sceneFile));
        Parent root = loader.load();
        Controller controller = loader.getController();
        controller.initialize(context);
        Scene scene = new Scene(root);
        stage.setTitle("DB manager");
        InputStream iconStream = ClientObjectFactory.class.getResourceAsStream("/icon.png");
        Image image = new Image(iconStream);
        stage.getIcons().add(image);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setMinHeight(400);
        stage.setMinWidth(700);
        stage.show();
    }
}

