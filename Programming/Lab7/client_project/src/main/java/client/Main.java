package client;

import client.command_manager.ClientCommandFactory;
import client.command_manager.CommandFactory;
import client.commands.*;
import client.connection.DefaultConnectionManager;
import client.connection.ConnectionManager;
import client.exceptions.NotEnoughAgrsException;
import client.exceptions.TerminalException;
import client.messages.*;
import client.reader.CommandReader;
import client.reader.ConsoleCommandReader;
import common.*;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import client.exceptions.ClientExceptionMessenger;
import org.apache.logging.log4j.LogManager;

/**
 * Class which starts the program
 * @autor 47iq
 * @version 1.0
 */

public class Main {

    /**
     * The entry point of the program
     * @param args String[] args
     */

    private static Printer out;

    private static ErrorPrinter err;

    private static ClientExceptionMessenger exceptionMessenger;

    public static void main(String[] args) {
        ObjectFactory objectFactory = new ClientObjectFactory(new Locale("ru", "RU"));
        Messenger messenger = objectFactory.getLocalMessenger();
        exceptionMessenger = objectFactory.getLocalErrorHandler();
        out = new DefaultPrinter(System.out);
        err = new DefaultErrorPrinter(System.err);
        try {
            InetAddress address = InetAddress.getByName(args[0]);
            int port = Integer.parseInt(args[1]);
            setValidators();
            Client client = new DefaultClient();
            CommandFactory commandFactory = new ClientCommandFactory(client, getClientCommands(), objectFactory, messenger);
            ConnectionManager connectionManager = DefaultConnectionManager.getInstance(objectFactory, address, port, commandFactory);
            CommandReader commandReader = new ConsoleCommandReader(connectionManager, objectFactory, messenger);
            client.start(commandReader);
        } catch (IndexOutOfBoundsException e) {
            Main.getErr().println(new NotEnoughAgrsException().accept(Main.getExceptionMessenger()));
            System.exit(1);
        } catch (Exception e) {
            Main.getErr().println(new TerminalException().accept(Main.getExceptionMessenger()));
            LogManager.getLogger().error("Terminal error: {}", e.getClass());
            System.exit(1);
        }
    }

    private static void setValidators() {
        DefaultTicket.setValidator(new DefaultTicketValidator());
        DefaultCoordinates.setValidator(new DefaultCoordinatesValidator());
        DefaultPerson.setValidator(new DefaultPersonValidator());
    }

    public static Map<String, Command> getClientCommands() {
        HashMap<String, Command> commands = new HashMap<>();
        commands.put("execute_script", new ExecuteScriptCommand());
        commands.put("help", new ClientHelpCommand());
        commands.put("exit", new DefaultExitCommand());
        commands.put("login", new LoginCommand());
        commands.put("register", new RegisterCommand());
        return commands;
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
}

