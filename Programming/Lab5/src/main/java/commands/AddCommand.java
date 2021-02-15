package commands;

import main.AbstractTicket;
import main.AbstractQueueManager;
import main.CollectionManager;

/**
 * Class of add command
 * @autor 47iq
 * @version 1.0
 */

public class AddCommand implements Command {

    /**
     * Task manager to execute command
     */

    private final CollectionManager taskManager;

    /**
     * Ticket that needs to be added
     */

    private final AbstractTicket ticket;

    /**
     * Constructor of the add command
     * @param taskManager collection's manager
     * @param ticket ticket we want to add
     */

    public AddCommand(CollectionManager taskManager, AbstractTicket ticket) {
        this.taskManager = taskManager;
        this.ticket = ticket;
    }

    public void execute() {
        //System.out.println("Adding ticket to the collection has started");
        if(ticket == null)
            return;
        try {
            taskManager.addTicket(ticket);
            //System.out.println("AbstractTicket has been successfully added");
        } catch (Exception e) {
            System.err.println("Error got while adding ticket to the collection");
        }
    }
}
