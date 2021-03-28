package client.gui_controllers;

import client.reader.CommandReader;
import common.Response;
import common.Ticket;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;

public class VisualController implements Controller{

    public Button backButton;
    public Button updateButton;
    public ImageView imgView;
    public Text userText;
    public Text addText;
    public Canvas canvas;
    public AnchorPane anchorPane;
    public Button changeColorButton;


    private List<Ticket> tickets;
    private ControllerContext context;
    private ControlManager controlManager;
    private ResourceBundle bundle;
    private CommandReader reader;
    private final double MAX_X = 600;
    private final double MAX_Y = 300;
    private double xCoefficient;
    private double yCoefficient;
    private double ticketMinX;
    private double ticketMinY;
    private final double TICKET_H = 15;
    private final double TICKET_W = 30;
    private final double H_CONST = 80;
    private final double W_CONST = 80;
    private final Controller controller = this;
    private Map<String, java.awt.Color> colorMap;

    @Override
    public void initialize(ControllerContext context) {
        this.context = context;
        controlManager = context.getControlManager();
        reader = context.getCommandReader();
        bundle = context.getBundle();
        updateCollection();
        visualize();
        localize();
        updateButton.setOnAction(actionEvent -> {
            updateCollection();
            visualize();
        });
        backButton.setOnAction(actionEvent -> {
            controlManager.showScene((Stage) backButton.getScene().getWindow(), "mainScene.fxml", this);
        });
        changeColorButton.setOnAction(event -> {
            colorMap.put(context.getCurrentUser(), reader.getResponse("update_color").getColor());
            visualize();
        });
    }

    private void localize() {
        backButton.setText(bundle.getString("BACK"));
        updateButton.setText(bundle.getString("UPDATE"));
        changeColorButton.setText(bundle.getString("CHANGE_COLOR"));
        userText.setText(context.getCurrentUser());
    }

    private void visualize() {
        colorMap = reader.getResponse("get_colors").getColorMap();
        double ticketMaxX = tickets.stream().map(Ticket::getX).max((x, y) -> (int) ((x) - (y))).get();
        ticketMinX = tickets.stream().map(Ticket::getX).max((x, y) -> (int) ((y) - (x))).get();
        xCoefficient = MAX_X/ (ticketMaxX - ticketMinX + TICKET_W + W_CONST);
        ticketMinY = tickets.stream().map(Ticket::getY).max((x, y) -> ((y) - (x))).get();
        double ticketMaxY = tickets.stream().map(Ticket::getY).max(Comparator.comparingInt(x -> (x))).get();
        yCoefficient = MAX_Y/ (ticketMaxY - ticketMinY + TICKET_H + H_CONST);
        for(Ticket ticket: tickets) {
            Group group = new Group(draw(ticket));
            anchorPane.getChildren().add(group);
        }
    }

    private Rectangle draw(Ticket ticket) {
        String owner = ticket.getOwner();
        Color color = convertColor(owner);
        Rectangle rectangle = new Rectangle();
        double x = (ticket.getX() - ticketMinX) * xCoefficient;
        double y = (ticket.getY() - ticketMinY) * yCoefficient;
        rectangle.setFill(color);
        rectangle.setStroke(Color.BLACK);
        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.setHeight(TICKET_H);
        rectangle.setWidth(TICKET_W);
        rectangle.setOnMouseClicked(event -> {
            if(ticket.getOwner().equals(context.getCurrentUser()))
                displayOwnTicketInfo(ticket);
            else
                displayTicketInfo(ticket);
        });
        return rectangle;
    }

    private void updateCollection() {
        Response response = reader.getResponse("show");
        tickets = response.getCollection();
    }

    private void displayOwnTicketInfo(Ticket ticket) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(bundle.getString("TICKET_INFO"));
        alert.setContentText(bundle.getString("UPDATE_CONF"));
        alert.setHeaderText(controlManager.getTicketString(ticket));
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isEmpty()) {
            alert.close();
        } else if (result.get() == ButtonType.OK) {
            context.setUpdateArg(ticket);
            context.setPrevScene("visual.fxml");
            controlManager.showScene((Stage) backButton.getScene().getWindow(), "update.fxml", controller);
        }else if (result.get() == ButtonType.CANCEL) {
            alert.close();
        }
    }

    private void displayTicketInfo(Ticket ticket) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(bundle.getString("TICKET_INFO"));
        alert.setHeaderText(controlManager.getTicketString(ticket));
        alert.showAndWait();
    }

    private Color convertColor(String owner) {
        java.awt.Color awtColor = colorMap.get(owner);
        double red = awtColor.getRed();
        double green = awtColor.getGreen();
        double blue = awtColor.getBlue();
        return Color.color(red/255, green/255, blue/255);
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
