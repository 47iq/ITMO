package main;

import commands.*;

import exceptions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Abstract class which parses commands and creates objects of them ({@link Command})
 * @autor 47iq
 * @version 1.0
 */

public class DefaultCommandFactory implements CommandFactory{

    private final Map<String, Class<? extends Command>> commands;

    public DefaultCommandFactory(Map<String, Class<? extends Command>> commands) {
        this.commands = commands;
    }

    public Command getCommand(String commandName, CommandReader reader, String arg, CollectionManager collectionManager) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        try{
            Class[] params = {CollectionManager.class, CommandReader.class, String.class};
            Constructor<? extends Command> constructor = commands.get(commandName).getConstructor(params);
            return constructor.newInstance(collectionManager, reader, arg);
        } catch (Exception e) {
            throw new UnknownCommandException();
        }
    }

    public Map<String, Class<? extends Command>> getAllCommands() {
        return commands;
    }

    /*public static Command getCommand(String commandsStr) {
        try {
            switch (commandsStr[0]) {
                case "help":
                    return new HelpCommand();
                case "info":
                    return new InfoCommand(taskManager);
                case "show":
                    return new ShowCommand(taskManager);
                case "add": {
                    (inputIsFromFile);
                    if(ticket == null)
                        throw new NullTicketException();
                    return new AddCommand(taskManager, ticket);
                }
                case "update": {

                    (inputIsFromFile);
                    if(ticket == null) {
                        throw new NullTicketException();
                    }
                    ticket.configureId(id);
                    return new UpdateCommand(taskManager, id, ticket);
                }
                case "remove_by_id": {

                    return new RemoveByIdCommand(taskManager, id);
                }
                case "clear": {
                    return new ClearCommand(taskManager);
                }
                case "save":
                    return new SaveCommand(taskManager);
                case "execute_script": {
                    File scriptFile = new File(commandsStr[1]);
                    if(inputIsFromFile && scriptFile.equals(inputFile))
                            throw new FileRecursionException();
                    return new ExecuteScriptCommand(scriptFile);
                }
                case "exit":
                    return new ExitCommand(taskManager);
                case "remove_first":
                    return new RemoveFirstCommand(taskManager);
                case "max_by_coordinates":
                    return new MaxByCoordinatesCommand(taskManager);
                case "filter_greater_than_discount": {

                    return new FilterGreaterThanDiscountCommand(taskManager, discount);
                }
                case "print_field_descending_refundable":
                    return new PrintFieldDescendingRefundableCommand(taskManager);
                case "remove_greater": {
                    (inputIsFromFile);
                    if(ticket == null)
                        throw new NullTicketException();
                    return new RemoveGreaterCommand(taskManager, ticket);
                }
                case "add_if_max" : {
                   (inputIsFromFile);
                    if(ticket == null)
                        throw new NullTicketException();
                    return new AddIfMaxCommand(taskManager, ticket);
                }
                default: {
                    throw new UnknownCommandException();
                }
            }
        } catch (IOException e) {
            System.err.println("Error got trying to get the command.");
            return new EmptyCommand();
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Error. The argument for command hasn't been entered. Check the help command " +
                    "for more information.");
            return new EmptyCommand();
        } catch (Exception e) {
            if(e.getMessage() != null)
                System.err.println(e.getMessage());
            else
                System.out.println("Error got while resolving the command");
            return new EmptyCommand();
        }
    }*/
}
