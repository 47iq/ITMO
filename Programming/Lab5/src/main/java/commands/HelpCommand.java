package commands;

import manager.CollectionManager;
import manager.CommandFactory;
import manager.CommandReader;

import java.util.Map;
import java.util.Set;

/**
 * Class of help command
 * @autor 47iq
 * @version 1.0
 */

public class HelpCommand implements Command {

    /**
     * Constructor for help command
     */

    private CommandReader commandReader;

    private CommandFactory commandFactory;

    public HelpCommand(CollectionManager collectionManager, CommandReader reader, String arg, CommandFactory commandFactory) {
        this.commandReader = reader;
        this.commandFactory = commandFactory;
    }

    public void execute() {
        Map<String, Class<? extends Command>> commands = commandFactory.getAllCommands();
        Set<String> keys = commands.keySet();
        System.out.println("The list of existing commands:\n");
        try {
            for (String key : keys) {
                System.out.println(commands.get(key).getMethod("strConvert").invoke(null));
            }
        } catch (Exception e) {
            System.err.println("Not every command has been displayed due to programmer's error. Please, contact the developer.");
        }
        System.out.println("\nNotice that arguments in round brackets \"( )\" must be entered in the same line as the command!");
    }

    public static String strConvert() {
        return "help : get information about the commands.";
    }
}
