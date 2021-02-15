package commands;

import main.AbstractTicket;
import main.AbstractQueueManager;
import main.CollectionManager;

/**
 * Class of remove_greater command
 * @autor 47iq
 * @version 1.0
 */

public class RemoveGreaterCommand implements Command {

    /**
     * Collection's manager
     */

    private final CollectionManager taskManager;

    /**
     * Ticket we want to compare to
     */

    private final AbstractTicket ticket;

    /**
     * Constructor of the remove_greater command
     * @param taskManager collection's manager
     * @param ticket ticket we want to compare to
     */

    public RemoveGreaterCommand(CollectionManager taskManager, AbstractTicket ticket) {
        this.taskManager = taskManager;
        this.ticket = ticket;
    }

    public void execute() {
        //System.out.println("Removing tickets greater than given has started");
        try {
            if(ticket != null)
                taskManager.removeGreater(ticket);
            //System.out.println("Removing elements has been finished");
        } catch (Exception e) {
            System.err.println("Error got while removing elements");
        }

    }
}
