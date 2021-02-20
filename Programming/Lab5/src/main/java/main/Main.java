package main;

import commands.*;
import exceptions.InputFileNotFoundException;

import java.util.HashMap;
import java.util.Map;

/**
 * Class which starts the program
 * @autor 47iq
 * @version 1.0
 */

public class Main {

    private static String fileName;

    /**
     * The entry point of the program
     * @param args String[] args
     */

    public static void main(String[] args) {
        try {
            if(args.length == 0)
                throw new InputFileNotFoundException();
            fileName = args[0];
            ClientObjectFactory clientObjectFactory= new DefaultClientObjectFactory();
            ServerObjectFactory serverObjectFactory= new DefaultServerObjectFactory();
            Map<String, Command> commands = getCommands();
            Messenger messenger = new ENGMessages(getCommands());
            CommandFactory commandFactory = SingletonCommandFactory.getInstance(commands, clientObjectFactory, messenger);
            QueueManager queueManager = new QueueManager(new JSONFileTicketsReader(fileName, serverObjectFactory), new JSONFileTicketWriter(fileName), serverObjectFactory);
            CommandReader commandReader= new ConsoleCommandReader(commandFactory, queueManager, clientObjectFactory, messenger);
            queueManager.parseDataToCollection();
            commandReader.readCommands();
        } catch (Exception e) {
            if(e.getMessage() != null)
                System.err.println(e.getMessage());
            System.err.println("Error got while executing the program.");
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
        commands.put("save", new SaveCommand());
        commands.put("remove_by_id", new RemoveByIdCommand());
        commands.put("remove_greater", new RemoveGreaterCommand());
        commands.put("remove_first", new RemoveFirstCommand());
        commands.put("max_by_coordinates", new MaxByCoordinatesCommand());
        commands.put("filter_greater_than_discount", new FilterGreaterThanDiscountCommand());
        commands.put("print_field_descending_refundable", new PrintFieldDescendingRefundableCommand());
        commands.put("exit", new ExitCommand());
        commands.put("execute_script", new ExecuteScriptCommand());
        commands.put("clear", new ClearCommand());
        return commands;
    }
}

