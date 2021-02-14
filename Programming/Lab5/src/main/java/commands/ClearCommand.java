package commands;

import main.AbstractTaskManager;
import main.AbstractTicket;

/**
 * Class of clear command
 * @autor 47iq
 * @version 1.0
 */

public class ClearCommand implements Command {
    AbstractTaskManager taskManager;

    /**
     * Constructor of the clear command
     * @param taskManager {@link AbstractTaskManager}
     */

    public ClearCommand(AbstractTaskManager taskManager) {
        this.taskManager = taskManager;
        execute();
    }

    public void execute() {
        //System.out.println("Clearing the collection has started");
        try {
            taskManager.clear();
            //System.out.println("Collection has been successfully cleared");
        } catch (Exception e) {
            System.err.println("Error got while clearing collection");
        }
    }
}
