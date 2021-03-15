package server;

import common.*;
import server.commands.Command;
import server.exceptions.InputFileNotFoundException;
import org.apache.logging.log4j.LogManager;
import server.collection.CollectionManager;
import server.collection.QueueManager;
import server.command_manager.CommandFactory;
import server.command_manager.ServerCommandFactory;
import server.commands.*;
import server.connection.*;
import server.datawork.DataBaseManager;
import server.datawork.PostgresDataBaseManager;

import java.nio.file.NoSuchFileException;
import java.util.*;


/**
 * Class which starts the program
 * @autor 47iq
 * @version 1.0
 */


public class Main {

    private static final String DB_URL = "jdbc:postgresql://localhost/tickets";

    private static final String USER = System.getenv("LOGIN");

    private static final String PASS = System.getenv("PASS");

    public static void main(String[] args) {
        ObjectFactory serverObjectFactory = new ServerObjectFactory();
        Map<String, Command> commands = getCommands();
        Set<String> ticketCommands = getTicketCommands();
        try {
            int port = 3110;
            setValidators();
            DataBaseManager dataBaseManager = new PostgresDataBaseManager(serverObjectFactory);
            dataBaseManager.start(DB_URL, USER, PASS);
            RequestReader requestReader = new DefaultRequestReader();
            ResponseSender responseSender = new DefaultResponseSender();
            CollectionManager queueManager = new QueueManager(dataBaseManager, serverObjectFactory);
            CommandFactory commandFactory = ServerCommandFactory.getInstance(commands, serverObjectFactory, queueManager);
            ConnectionManager connectionWorker = new DefaultConnectionManager(commandFactory, ticketCommands, responseSender,
                    serverObjectFactory, requestReader, dataBaseManager.getUsersData());
            ConnectionBuilder connectionBuilder = new DefaultConnectionBuilder(port);
            Server server = new DefaultServer(connectionWorker, connectionBuilder, port);
            LogManager.getLogger().info("Server started at port: {}", port);
            commandFactory.setServerCommands(getServerCommands(server));
            queueManager.parseDataToCollection();
            server.start();
        } catch (NoSuchElementException e) {
            LogManager.getLogger().info("Program has been exited.");
        } catch (Exception e) {
            LogManager.getLogger().error("{} got while executing the Main.java .", e.getClass());
            System.exit(1);
        }
    }

    private static void setValidators() {
        DefaultTicket.setValidator(new DefaultTicketValidator());
        DefaultCoordinates.setValidator(new DefaultCoordinatesValidator());
        DefaultPerson.setValidator(new DefaultPersonValidator());
    }

    public static Map<String, Command> getCommands() {
        HashMap<String, Command> commands = new HashMap<>();
        commands.put("help", new HelpCommand());
        commands.put("info", new InfoCommand());
        commands.put("show", new ShowCommand());
        commands.put("add", new AddCommand());
        commands.put("add_if_max", new AddIfMaxCommand());
        commands.put("update", new UpdateCommand());
        commands.put("remove_by_id", new RemoveByIdCommand());
        commands.put("remove_greater", new RemoveGreaterCommand());
        commands.put("remove_first", new RemoveFirstCommand());
        commands.put("max_by_coordinates", new MaxByCoordinatesCommand());
        commands.put("filter_greater_than_discount", new FilterGreaterThanDiscountCommand());
        commands.put("print_field_descending_refundable", new PrintFieldDescendingRefundableCommand());
        commands.put("clear", new ClearCommand());
        return commands;
    }

    public static Map<String, Command> getServerCommands(Server server) {
        HashMap<String, Command> serverCommands = new HashMap<>();
        serverCommands.put("shut_down", new ExitCommand(server));
        serverCommands.put("exit_no_save", new ExitNoSaveCommand());
        serverCommands.put("save", new SaveCommand());
        return serverCommands;
    }

    public static Set<String> getTicketCommands() {
            HashSet<String> commands = new HashSet<>();
            commands.add("add");
            commands.add("add_if_max");
            commands.add("update");
            commands.add("remove_greater");
            return commands;
    }
}

