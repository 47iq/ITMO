package commands;

import main.*;

import java.util.List;

/**
 * Class of info command
 * @autor 47iq
 * @version 1.0
 */

public class InfoCommand implements MessagingCommand {

    public InfoCommand() {

    }

    public void execute(CollectionManager collectionManager, CommandReader reader, String arg, Messenger messenger) {
        //System.out.println("Info displaying has started.");
        try {
            List<String> info = collectionManager.displayInfo();
            System.out.println(messenger.getCollectionMessage(info.get(0), info.get(1), info.get(2)));
            //System.out.println("Info displaying has been completed");
        } catch (Exception e) {
            System.err.println("Error got while displaying the info");
        }
    }
}
