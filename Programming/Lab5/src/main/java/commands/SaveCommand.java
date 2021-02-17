package commands;

import manager.CollectionManager;
import manager.CommandFactory;
import manager.CommandReader;

/**
 * Class of save command
 * @autor 47iq
 * @version 1.0
 */

public class SaveCommand implements Command {

    /**
     * Collection's manager
     */

    private final CollectionManager collectionManager;


    public SaveCommand(CollectionManager collectionManager, CommandReader reader, String arg, CommandFactory commandFactory) {
        this.collectionManager = collectionManager;
    }

    public void execute() {
        //System.out.println("Saving the collection has started");
        try {
            collectionManager.saveData();
            //System.out.println("Collection has been successfully saved");
        } catch (Exception e) {
            System.err.println("Error got while saving the collection");
        }
    }

    public static String strConvert() {
        return "save: save the collection into the file.";
    }
}
