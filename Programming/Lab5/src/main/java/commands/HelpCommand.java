package commands;

import main.*;

/**
 * Class of help command
 * @autor 47iq
 * @version 1.0
 */

public class HelpCommand implements MessagingCommand {

    public HelpCommand() {
    }

    public void execute(CollectionManager collectionManager, CommandReader reader, String arg, Messenger messenger) {
        System.out.println(messenger.getCommandsMessages());
    }
}
