package client.gui_controllers;

import client.ClientContext;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;

import java.io.IOException;
import java.util.ResourceBundle;

public class DefaultControlManager implements ControlManager {
    public void showScene(Stage stage, String sceneFile, Controller controller) {
        try {
            FXMLLoader loader = new FXMLLoader(ClientContext.class.getClassLoader().getResource(sceneFile));
            Parent root = loader.load();
            Controller newController = loader.getController();
            ControllerContext context = controller.getContext();
            newController.initialize(context);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            LogManager.getLogger().error("IOException");
        }
    }

    public void showCanvas(Stage stage, String sceneFile, Controller controller) {
        Group root = new Group();
        Circle circle1 = new Circle(50);
        circle1.setStroke(Color.GREEN);
        circle1.setFill(Color.GREEN.deriveColor(1, 1, 1, 0.7));
        circle1.relocate(100, 100);
        Circle circle2 = new Circle(50);
        circle2.setStroke(Color.BLUE);
        circle2.setFill(Color.BLUE.deriveColor(1, 1, 1, 0.7));
        circle2.relocate(200, 200);
        Line line = new Line(circle1.getLayoutX(), circle1.getLayoutY(), circle2.getLayoutX(), circle2.getLayoutY());
        line.setStrokeWidth(20);
        Pane overlay = new Pane();
        overlay.getChildren().addAll(circle1, circle2, line);
        root.getChildren().addAll(overlay);
        stage.setScene(new Scene(root, 700, 400));
        stage.show();
            /*FXMLLoader loader = new FXMLLoader(ClientContext.class.getClassLoader().getResource(sceneFile));
            Parent root = loader.load();
            Controller newController = loader.getController();
            ControllerContext context = controller.getContext();
            newController.initialize(context);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();*/
    }

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

    public String getErrorMessage(Exception e, ResourceBundle bundle) {
        if (bundle.containsKey(e.getMessage()))
            return bundle.getString(e.getMessage());
        else
            return bundle.getString("ERR_UNK");
    }

    public String getErrorMessage(String e, ResourceBundle bundle) {
        if (bundle.containsKey(e))
            return bundle.getString(e);
        else
            return bundle.getString("ERR_UNK");
    }
}
