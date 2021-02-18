package commands;

import main.CollectionManager;
import main.CommandReader;

/**
 * Class of exit command
 * @autor 47iq
 * @version 1.0
 */

public class ExitCommand implements SimpleCommand {

    public ExitCommand() {

    }

    public void execute(CollectionManager collectionManager, CommandReader commandReader, String arg) {
        //System.out.println("Exiting program...");
        commandReader.exit();
    }
}
