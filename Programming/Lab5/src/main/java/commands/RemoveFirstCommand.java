package commands;

import main.AbstractQueueManager;
import main.CollectionManager;

/**
 * Class of remove_first command
 * @autor 47iq
 * @version 1.0
 */

public class RemoveFirstCommand implements Command {

    /**
     * Collection's manager
     */

    private final CollectionManager taskManager;

    /**
     * Constructor of the remove_first command
     * @param taskManager collection's manager
     */

    public RemoveFirstCommand(CollectionManager taskManager) {
        this.taskManager = taskManager;
    }

    public void execute() {
        //System.out.println("Removing the first element has started");
        try {
            taskManager.removeFirst();
            //System.out.println("The first element has been successfully removed");
        } catch (Exception e) {
            System.err.println("Error got while removing the first element");
        }
    }
}
