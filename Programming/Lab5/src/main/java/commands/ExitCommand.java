package commands;

import main.AbstractCollectionManager;

/**
 * Class of exit command
 * @autor 47iq
 * @version 1.0
 */

public class ExitCommand implements Command {

    /**
     * Collection's manager
     */

    private final AbstractCollectionManager taskManager;

    /**
     * Constructor of the exit command
     * @param taskManager collection's manager
     */

    public ExitCommand(AbstractCollectionManager taskManager) {
        this.taskManager = taskManager;
    }

    public void execute() {
        //System.out.println("Exiting program...");
        taskManager.exit();
    }
}
