package commands;

import main.AbstractTaskManager;
import main.AbstractTicket;

/**
 * Class of save command
 * @autor 47iq
 * @version 1.0
 */

public class SaveCommand implements Command {

    AbstractTaskManager taskManager;

    /**
     * Constructor of the save command
     * @param taskManager {@link AbstractTaskManager}
     */

    public SaveCommand(AbstractTaskManager taskManager) {
        this.taskManager = taskManager;
        execute();
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
