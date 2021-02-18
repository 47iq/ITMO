package commands;

import main.*;

/**
 * Class of help command
 * @autor 47iq
 * @version 1.0
 */

public class HelpCommand implements Command {

    private final Messenger messenger;

    public HelpCommand(CollectionManager collectionManager, CommandReader reader, String arg, Messenger messenger) {
        this.messenger = messenger;
    }

    public void execute() {
        System.out.println(messenger.getCommandsMessages());
    }
}
