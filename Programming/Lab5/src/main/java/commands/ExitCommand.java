package commands;

import main.AbstractQueueManager;
import main.CollectionManager;
import main.CommandFactory;
import main.CommandReader;

/**
 * Class of exit command
 * @autor 47iq
 * @version 1.0
 */

public class ExitCommand implements Command {

    private final CommandReader commandReader;

    public ExitCommand(CollectionManager collectionManager, CommandReader reader, String arg, CommandFactory commandFactory) {
        this.commandReader = reader;
    }

    public void execute() {
        //System.out.println("Exiting program...");
        commandReader.exit();
    }

    public static String strConvert() {
        return "exit: stop the program.";
    }
}
