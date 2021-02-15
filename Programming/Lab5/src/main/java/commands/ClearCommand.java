package commands;

import main.AbstractCollectionManager;

/**
 * Class of clear command
 * @autor 47iq
 * @version 1.0
 */

public class ClearCommand implements Command {
    /**
     * Collection's manager
     */

    private final AbstractCollectionManager taskManager;

    /**
     * Constructor of the clear command
     * @param taskManager collection's manager
     */

    public ClearCommand(AbstractCollectionManager taskManager) {
        this.taskManager = taskManager;
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
