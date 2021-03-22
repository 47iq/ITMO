package client;

import client.command_manager.ClientCommandFactory;
import client.commands.*;
import client.connection.*;
import client.exceptions.LocalMessengerException;
import client.messages.*;
import client.reader.CommandReader;
import client.reader.DefaultControllerCommandReader;
import client.reader.FileCommandReader;
import client.ticket.*;
import common.*;
import client.exceptions.ClientExceptionMessenger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Class for creation of objects for client application
 */

public class ClientContext implements ObjectFactory {

    private final Locale locale;

    private static CommandReader commandReader;

    private static String currentCommand;

    public static String getCurrentCommand() {
        return currentCommand;
    }

    public static void setCurrentCommand(String currentCommand) {
        ClientContext.currentCommand = currentCommand;
    }

    public static void setCommandReader(CommandReader commandReader) {
        ClientContext.commandReader = commandReader;
    }

    public static CommandReader getCommandReader() {
        return commandReader;
    }

    public ClientContext(Locale locale) {
        this.locale = locale;
    }

    public CommandReader getControllerCommandReader() {
        return new DefaultControllerCommandReader(getConnectionManager(), this);
    }

    private ConnectionManager getConnectionManager() {
        try {
            return new DefaultConnectionManager(this, InetAddress.getByName("localhost"), 3110, getCommandFactory());
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }


    public CommandReader getFileReader(ConnectionManager commandFactory, File file) throws FileNotFoundException {
        return new FileCommandReader(commandFactory, file, this);
    }

    public DefaultTicket getDefaultTicket() {
        return new ClientTemplateTicket();
    }

    public DefaultCoordinates getDefaultCoordinates() {
        return new ClientTemplateCoordinates();
    }

    public DefaultPerson getDefaultPerson() {
        return new ClientTemplatePerson();
    }

    public Request getRequest(RequestType type, String commandName) {
            return  new DefaultRequest(type, commandName, getLocale());
    }

    public RequestSender getRequestSender(SocketChannel channel) {
        return new DefaultRequestSender(channel);
    }

    public ResponseReader getResponseReader(SocketChannel channel) {
        return new DefaultResponseReader(channel);
    }

    public Messenger getLocalMessenger() {
        Locale localeRu = new Locale("ru", "RU");
        if (locale.getLanguage().equals(localeRu.getLanguage()))
            return new MessengerRU();
        else if (locale.getLanguage().equals(Locale.ENGLISH.getLanguage()))
            return new MessengerENG();
        else {
            Main.getErr().println(new LocalMessengerException().accept(Main.getExceptionMessenger()));
            return new MessengerENG();
        }
    }

    public ClientExceptionMessenger getLocalErrorHandler() {
        Locale localeRu = new Locale("ru", "RU");
        if (locale.getLanguage().equals(localeRu.getLanguage()))
            return new ExceptionMessengerRU();
        else
            return new ExceptionMessengerENG();
    }

    public ClientCommandFactory getCommandFactory() {
        return new ClientCommandFactory(getClient(), getCommands(), this, getLocalMessenger());
    }

    public Client getClient() {
        return new DefaultClient();
    }

    public Map<String, Command> getCommands() {
        HashMap<String, Command> commands = new HashMap<>();
        commands.put("execute_script", new ExecuteScriptCommand());
        commands.put("help", new ClientHelpCommand());
        commands.put("exit", new DefaultExitCommand());
        commands.put("login", new LoginCommand());
        commands.put("register", new RegisterCommand());
        return commands;
    }

    public Locale getLocale() {
        return locale;
    }

    public User getUser(String login, String password) {
        return new DefaultUser(login, password);
    }

    public Response getResponse(boolean successful, String message) {
        return new DefaultResponse(successful, message);
    }

    public static void showScene(String sceneFile) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(ClientContext.class.getClassLoader().getResource(sceneFile));
        Scene scene = new Scene(root);
        stage.setTitle("Database manager");
        InputStream iconStream = ClientContext.class.getResourceAsStream("/icon.png");
        Image image = new Image(iconStream);
        stage.getIcons().add(image);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setMinHeight(400);
        stage.setMinWidth(700);
        stage.show();
    }

    public TicketBuilder getTicketBuilder() {
        return new DefaultTicketBuilder(this);
    }
}
