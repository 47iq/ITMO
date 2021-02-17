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
            TicketMessenger ticketMessenger = new ENGTicketMessages();
            CommandFactory commandFactory = SingletonCommandFactory.getInstance(getCommands());
            QueueManager queueManager = new QueueManager(new JSONFileTicketsReader(fileName), new JSONFileTicketWriter(fileName), ticketMessenger);
            CommandReader commandReader= new ConsoleCommandReader(commandFactory, queueManager);
            queueManager.parseDataToCollection();
            commandReader.readCommands();
        } catch (Exception e) {
            if(e.getMessage() != null)
                System.err.println(e.getMessage());
            System.err.println("Error got while executing the program.");
        }
    }

    public static Map<String, Class<? extends Command>> getCommands() {
        HashMap<String, Class<? extends Command>> commands = new HashMap<>();
        commands.put("help", HelpCommand.class);
        commands.put("info", InfoCommand.class);
        commands.put("show", ShowCommand.class);
        commands.put("add", AddCommand.class);
        commands.put("add_if_max", AddIfMaxCommand.class);
        commands.put("update", UpdateCommand.class);
        commands.put("save", SaveCommand.class);
        commands.put("remove_by_id", RemoveByIdCommand.class);
        commands.put("remove_greater", RemoveGreaterCommand.class);
        commands.put("remove_first", RemoveFirstCommand.class);
        commands.put("max_by_coordinates", MaxByCoordinatesCommand.class);
        commands.put("filter_greater_than_discount", FilterGreaterThanDiscountCommand.class);
        commands.put("print_field_descending_refundable", PrintFieldDescendingRefundableCommand.class);
        commands.put("exit", ExitCommand.class);
        commands.put("execute_script", ExecuteScriptCommand.class);
        commands.put("clear", ClearCommand.class);
        return commands;
    }
}

