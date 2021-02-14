package commands;

import main.AbstractTaskManager;
import main.AbstractTicket;

/**
 * Class of show command
 * @autor 47iq
 * @version 1.0
 */

public class ShowCommand implements Command {

    AbstractTaskManager taskManager;

    /**
     * Constructor of the show command
     * @param taskManager {@link AbstractTaskManager}
     */

    public ShowCommand(AbstractTaskManager taskManager) {
        this.taskManager = taskManager;
        execute();
    }

    public void execute() {
        //System.out.println("Displaying tickets");
        try {
            taskManager.displayElements();
            //System.out.println("Tickets have been successfully displayed");
        } catch (Exception e) {
            System.err.println("Error got while displaying tickets");
        }
    }
}
