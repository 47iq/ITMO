package commands;

import main.AbstractQueueManager;
import main.CollectionManager;

/**
 * Class of save command
 * @autor 47iq
 * @version 1.0
 */

public class SaveCommand implements Command {

    /**
     * Collection's manager
     */

    private final CollectionManager taskManager;

    /**
     * Constructor of the save command
     * @param taskManager collection's manager
     */

    public SaveCommand(CollectionManager taskManager) {
        this.taskManager = taskManager;
    }

    public void execute() {
        //System.out.println("Saving the collection has started");
        try {
            taskManager.saveDataToFile();
            //System.out.println("Collection has been successfully saved");
        } catch (Exception e) {
            System.err.println("Error got while saving the collection");
        }
    }
}
