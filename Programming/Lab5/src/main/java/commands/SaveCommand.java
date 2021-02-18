package commands;

import main.CollectionManager;
import main.CommandReader;

/**
 * Class of save command
 * @autor 47iq
 * @version 1.0
 */

public class SaveCommand implements SimpleCommand {

    public SaveCommand() {
    }

    public void execute(CollectionManager collectionManager, CommandReader reader, String arg) {
        //System.out.println("Saving the collection has started");
        try {
            collectionManager.saveData();
            //System.out.println("Collection has been successfully saved");
        } catch (Exception e) {
            System.err.println("Error got while saving the collection");
        }
    }
}
