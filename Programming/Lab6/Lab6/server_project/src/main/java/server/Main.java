package server;

import exceptions.InputFileNotFoundException;
import org.apache.logging.log4j.LogManager;
import server.collection.QueueManager;
import server.command_manager.CommandFactory;
import server.command_manager.CommandVisitor;
import server.command_manager.ServerCommandFactory;
import server.command_manager.Visitor;
import server.commands.*;
import server.connection.*;
import server.filework.JSONFileTicketWriter;
import server.filework.JSONFileTicketsReader;
import server.messages.MessagesRU;
import server.messages.Messenger;

import java.util.*;


/**
 * Class which starts the program
 * @autor 47iq
 * @version 1.0
 */


public class Main {

    public static void main(String[] args) {
        try {
            int port = 3110;
            if(args.length == 0)
                throw new InputFileNotFoundException();
            String fileName = args[0];
            ObjectFactory serverObjectFactory= new ServerObjectFactory();
            Map<String, Command> commands = getCommands();
            Set<String> ticketCommands = getTicketCommands();
            Messenger messenger = new MessagesRU();
            RequestReader requestReader = new DefaultRequestReader();
            ResponseSender responseSender = new DefaultResponseSender();
            QueueManager queueManager = new QueueManager(new JSONFileTicketsReader(fileName, serverObjectFactory), new JSONFileTicketWriter(fileName), serverObjectFactory);
            Visitor visitor = new CommandVisitor();
            CommandFactory commandFactory = ServerCommandFactory.getInstance(commands, serverObjectFactory, messenger, queueManager, visitor);
            ConnectionManager connectionWorker = new DefaultConnectionManager(queueManager, commandFactory, ticketCommands, responseSender,
                    serverObjectFactory, requestReader);
            ConnectionBuilder connectionBuilder = new DefaultConnectionBuilder(port);
            Server server= new DefaultServer(connectionWorker, connectionBuilder, port);
            LogManager.getLogger().info("Server started at port: {}, resource file for collection: {}", port, fileName);
            commandFactory.setServerCommands(getServerCommands(server));
            queueManager.parseDataToCollection();
            server.readCommands();
        } catch (NoSuchElementException e) {
            LogManager.getLogger().info("Program has been exited.");
        }catch (Exception e) {
            LogManager.getLogger().error("{} got while executing the program.", e.getClass());
        }
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
        serverCommands.put("exit", new ExitCommand(server));
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

