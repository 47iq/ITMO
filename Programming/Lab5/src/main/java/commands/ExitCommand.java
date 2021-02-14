package commands;

import main.AbstractTaskManager;
import main.AbstractTicket;

/**
 * Class of exit command
 * @autor 47iq
 * @version 1.0
 */

public class ExitCommand implements Command {

    AbstractTaskManager taskManager;

    /**
     * Constructor of the exit command
     * @param taskManager {@link AbstractTaskManager}
     */

    public ExitCommand(AbstractTaskManager taskManager) {
        this.taskManager = taskManager;
        execute();
    }

    public void execute() {
        //System.out.println("Exiting program...");
        taskManager.exit();
    }
}
