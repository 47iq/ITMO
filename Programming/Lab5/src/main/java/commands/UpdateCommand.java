package commands;

import exceptions.TicketNotFoundException;
import main.AbstractTicket;
import main.AbstractCollectionManager;

/**
 * Class of update command
 * @autor 47iq
 * @version 1.0
 */

public class UpdateCommand implements Command {

    /**
     * Collection's manager
     */

    private final AbstractCollectionManager taskManager;

    /**
     * id of the ticket we want to update
     */

    private final int id;

    /**
     * New ticket
     */

    private final AbstractTicket ticket;

    /**
     * Constructor of the update command
     * @param taskManager collection's manager
     * @param id id of the ticket we want to update
     * @param ticket new ticket
     */

    public UpdateCommand(AbstractCollectionManager taskManager, int id, AbstractTicket ticket) {
        this.taskManager = taskManager;
        this.id = id;
        this.ticket = ticket;
    }

    public void execute() {
        //System.out.println("Updating ticket has started");
        if(ticket == null)
            return;
        try {
            if(!taskManager.elementExists(id))
                throw new TicketNotFoundException();
            taskManager.updateId(id, ticket);
            //System.out.println("AbstractTicket has been successfully updated");
        } catch (TicketNotFoundException e) {
            System.err.println(e.getMessage());
        }  catch (Exception e) {
            System.err.println("Error got while updating the ticket");
        }
    }
}
