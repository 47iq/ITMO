package commands;

import main.AbstractQueueManager;
import main.CollectionManager;

/**
 * Class of info command
 * @autor 47iq
 * @version 1.0
 */

public class InfoCommand implements Command {

    /**
     * Collection's manager
     */

    private final CollectionManager taskManager;

    /**
     * Constructor of the info command
     * @param taskManager collection's manager
     */

    public InfoCommand(CollectionManager taskManager) {
        this.taskManager = taskManager;
    }

    public void execute() {
        //System.out.println("Info displaying has started.");
        try {
            taskManager.displayInfo();
            //System.out.println("Info displaying has been completed");
        } catch (Exception e) {
            System.err.println("Error got while displaying the info");
        }
    }
}
