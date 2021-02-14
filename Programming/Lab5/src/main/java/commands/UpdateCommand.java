package commands;

import exceptions.TicketNotFoundException;
import main.AbstractTicket;
import main.AbstractTaskManager;

/**
 * Class of update command
 * @autor 47iq
 * @version 1.0
 */

public class UpdateCommand implements Command {
    AbstractTaskManager taskManager;
    int id;
    AbstractTicket ticket;

    /**
     * Constructor of the update command
     * @param taskManager {@link AbstractTaskManager}
     * @param ticket {@link AbstractTicket}
     */

    public UpdateCommand(AbstractTaskManager taskManager, int id, AbstractTicket ticket) {
        this.taskManager = taskManager;
        this.id = id;
        this.ticket = ticket;
        if(ticket != null)
            execute();
    }

    public void execute() {
        //System.out.println("Updating ticket has started");
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
