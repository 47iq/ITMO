package commands;

import main.AbstractQueueManager;
import main.AbstractTicket;
import main.CollectionManager;

/**
 * Class of add_if_max command
 * @autor 47iq
 * @version 1.0
 */

public class AddIfMaxCommand implements Command {

    /**
     * Collection's manager
     */

    private final CollectionManager taskManager;

    /**
     * Ticket we want to add
     */

    private final AbstractTicket ticket;

    /**
     * Constructor of the add_if_max command
     * @param taskManager collection's manager
     * @param ticket ticket we want to add
     */

    public AddIfMaxCommand(CollectionManager taskManager, AbstractTicket ticket) {
        this.taskManager = taskManager;
        this.ticket = ticket;
    }

    public void execute() {
        //System.out.println("Trying to add ticket...");
        if(ticket == null)
            return;
        try{
            taskManager.addIfMax(ticket);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error got while adding the ticket");
        }
    }
}
