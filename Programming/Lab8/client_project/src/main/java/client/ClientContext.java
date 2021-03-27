package client;

import client.command_manager.ClientCommandFactory;
import client.commands.*;
import client.connection.*;
import client.controllers.ControlManager;
import client.controllers.ControllerContext;
import client.controllers.DefaultControlManager;
import client.controllers.DefaultControllerContext;
import client.exceptions.ClientExceptionMessenger;
import client.exceptions.LocalMessengerException;
import client.messages.*;
import client.reader.CommandReader;
import client.reader.DefaultControllerCommandReader;
import client.reader.FileCommandReader;
import client.ticket.*;
import common.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Class for creation of objects for client application
 */

public class ClientContext implements ObjectFactory {

    private final Locale locale;

    public ClientContext(Locale locale) {
        this.locale = locale;
    }

    public CommandReader getControllerCommandReader() {
        return new DefaultControllerCommandReader(getConnectionManager(), this);
    }

    private ConnectionManager getConnectionManager() {
        try {
            return new DefaultConnectionManager(this, InetAddress.getByName("localhost"), 3110, getCommandFactory(), getMessagingCommands());
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private Map<String, MessagingCommand> getMessagingCommands() {
        Map<String, MessagingCommand> messagingCommands = new HashMap<>();
        messagingCommands.put("info", new ClientInfoCommand());
        messagingCommands.put("show", new ClientShowCommand());
        messagingCommands.put("help", new ClientHelpCommand(getTranslations()));
        return messagingCommands;
    }

    private Map<String, String> getTranslations() {
        Map<String, String> translations = new HashMap<>();
        translations.put("help", "HELP_DESC");
        translations.put("info", "INFO_DESC");
        translations.put("show", "SHOW_DESC");
        translations.put("add", "ADD_DESC");
        translations.put("add_if_max", "ADD_MAX_DESC");
        translations.put("update", "UPDATE_DESC");
        translations.put("remove_by_id", "REMOVE_ID_DESC");
        translations.put("remove_greater", "REMOVE_GREATER_DESC");
        translations.put("remove_first","REMOVE_FIRST");
        translations.put("clear","CLEAR_DESCRIPTION");
        translations.put("visualize","VISUALIZE_DESCRIPTION");
        return translations;
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
        return new DefaultRequest(type, commandName, getLocale());
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

    public UpdateData getDefaultUpdateData() {
        return new DefaultUpdateData(true, true, true, true,
                true, true, true, true, true,
                true, true);
    }

    @Override
    public UpdateData getUpdateData() {
        return new DefaultUpdateData();
    }

    public TicketBuilder getTicketBuilder() {
        return new DefaultTicketBuilder(this);
    }

    @Override
    public ControllerContext getContext() {
        try {
            File file = new File("/");
            URL[] urls = {file.toURI().toURL()};
            ClassLoader loader = new URLClassLoader(urls);
            try {
                Locale locale = Locale.getDefault();
                return new DefaultControllerContext(getControllerCommandReader(), getControllerManager(), ResourceBundle.getBundle("messages", locale, loader));
            } catch (Exception e) {
                Locale locale = Locale.ENGLISH;
                return new DefaultControllerContext(getControllerCommandReader(), getControllerManager(), ResourceBundle.getBundle("messages", locale, loader));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }

    @Override
    public ControlManager getControllerManager() {
        return new DefaultControlManager();
    }
}
