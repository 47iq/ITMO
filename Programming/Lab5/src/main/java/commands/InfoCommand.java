package commands;

import main.AbstractTaskManager;
import main.AbstractTicket;

/**
 * Class of info command
 * @autor 47iq
 * @version 1.0
 */

public class InfoCommand implements Command {

    AbstractTaskManager taskManager;

    /**
     * Constructor of the info command
     * @param taskManager {@link AbstractTaskManager}
     */

    public InfoCommand(AbstractTaskManager taskManager) {
        this.taskManager = taskManager;
        execute();
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
