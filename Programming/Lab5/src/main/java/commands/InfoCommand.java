package commands;

import main.*;

import java.util.List;

/**
 * Class of info command
 * @autor 47iq
 * @version 1.0
 */

public class InfoCommand implements Command {

    /**
     * Collection's manager
     */

    private final CollectionManager collectionManager;

    private final Messenger messenger;


    public InfoCommand(CollectionManager collectionManager, CommandReader reader, String arg, Messenger messenger) {
        this.collectionManager = collectionManager;
        this.messenger = messenger;
    }

    public void execute() {
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
