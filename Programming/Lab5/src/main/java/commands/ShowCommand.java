package commands;

import main.AbstractCollectionManager;

/**
 * Class of show command
 * @autor 47iq
 * @version 1.0
 */

public class ShowCommand implements Command {

    /**
     * Collection's manager
     */

    private final AbstractCollectionManager taskManager;

    /**
     * Constructor of the show command
     * @param taskManager collection's manager
     */

    public ShowCommand(AbstractCollectionManager taskManager) {
        this.taskManager = taskManager;
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
