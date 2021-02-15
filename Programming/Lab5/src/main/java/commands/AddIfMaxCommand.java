package commands;

import main.AbstractCollectionManager;
import main.AbstractTicket;

/**
 * Class of add_if_max command
 * @autor 47iq
 * @version 1.0
 */

public class AddIfMaxCommand implements Command {

    /**
     * Collection's manager
     */

    private final AbstractCollectionManager taskManager;

    /**
     * Ticket we want to add
     */

    private final AbstractTicket ticket;

    /**
     * Constructor of the add_if_max command
     * @param taskManager collection's manager
     * @param ticket ticket we want to add
     */

    public AddIfMaxCommand(AbstractCollectionManager taskManager, AbstractTicket ticket) {
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
