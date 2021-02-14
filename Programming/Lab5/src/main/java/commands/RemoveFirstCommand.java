package commands;

import main.AbstractTaskManager;
import main.AbstractTicket;

/**
 * Class of remove_first command
 * @autor 47iq
 * @version 1.0
 */

public class RemoveFirstCommand implements Command {

    AbstractTaskManager taskManager;

    /**
     * Constructor of the remove_first command
     * @param taskManager {@link AbstractTaskManager}
     */

    public RemoveFirstCommand(AbstractTaskManager taskManager) {
        this.taskManager = taskManager;
        execute();
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
