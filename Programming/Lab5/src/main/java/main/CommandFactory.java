package main;

import commands.*;

import exceptions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

/**
 * Abstract class which parses commands and creates objects of them ({@link Command})
 * @autor 47iq
 * @version 1.0
 */

public abstract class CommandFactory {

    /**
     * Task manager to setup commands
     */

    private static CollectionManager taskManager;

    /**
     * Input stream
     */

    private static BufferedReader input;

    /**
     * Flag for reading from file
     */

    private static boolean inputIsFromFile = false;

    /**
     * Input {@link File} (if exists)
     */

    private static File inputFile = null;

    /**
     * Setter for {@link #taskManager}
     * @param manager collection manager
     */

    public static void setTaskManager(CollectionManager manager) {
        taskManager = manager;
    }

    /**
     * Setter for {@link #input}
     * @param reader input reader
     */

    public static void setInput(BufferedReader reader) {
        input = reader;
    }

    /**
     * Getter for {@link #input}
     * @return input {@link #input}
     */

    public static BufferedReader getInput() {
        return input;
    }

    /**
     * Reads and executes next command
     * @throws IOException in case of some {@link #input} error
     */

    public static void executeNextCommand() throws IOException{
        String[] commands = input.readLine().split(" ");
        Command command = getCommand(commands);
        command.execute();
    }

    /**
     * Method to execute commands without printing tips for user
     */

    public static void executeCommandsFromFile(File file){
        try {
            inputIsFromFile = true;
            inputFile = file;
            while (input.ready()) {
                String[] commands = input.readLine().split(" ");
                getCommand(commands).execute();
            }
        } catch (IOException | NullPointerException e) {
            //System.out.println("All commands have been executed.");
        } catch (Exception e) {
            System.err.println("Something went wrong executing the commands.");
        } finally {
            inputIsFromFile = false;
            inputFile = null;
        }
    }

    /**
     * Method to resolve commands
     * @param commandsStr String[] of command and its arguments
     * @return command command we want to be executed
     */

    public static Command getCommand(String[] commandsStr) {
        try {
            switch (commandsStr[0]) {
                case "help":
                    return new HelpCommand();
                case "info":
                    return new InfoCommand(taskManager);
                case "show":
                    return new ShowCommand(taskManager);
                case "add": {
                    AbstractTicket ticket = TicketParser.parseTicket(inputIsFromFile);
                    if(ticket == null)
                        throw new NullTicketException();
                    return new AddCommand(taskManager, ticket);
                }
                case "update": {
                    int id = TicketParser.parseId(commandsStr[1]);
                    AbstractTicket ticket = TicketParser.parseTicket(inputIsFromFile);
                    if(ticket == null) {
                        throw new NullTicketException();
                    }
                    ticket.configureId(id);
                    return new UpdateCommand(taskManager, id, ticket);
                }
                case "remove_by_id": {
                    int id = TicketParser.parseId(commandsStr[1]);
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
                    double discount = TicketParser.parseDiscount(commandsStr[1]);
                    return new FilterGreaterThanDiscountCommand(taskManager, discount);
                }
                case "print_field_descending_refundable":
                    return new PrintFieldDescendingRefundableCommand(taskManager);
                case "remove_greater": {
                    AbstractTicket ticket = TicketParser.parseTicket(inputIsFromFile);
                    if(ticket == null)
                        throw new NullTicketException();
                    return new RemoveGreaterCommand(taskManager, ticket);
                }
                case "add_if_max" : {
                    AbstractTicket ticket = TicketParser.parseTicket(inputIsFromFile);
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
    }
}
