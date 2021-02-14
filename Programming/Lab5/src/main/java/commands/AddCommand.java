package commands;

import main.AbstractTicket;
import main.AbstractTaskManager;

/**
 * Class of add command
 * @autor 47iq
 * @version 1.0
 */

public class AddCommand implements Command {

    /**
     * Task manager to execute command
     */

    AbstractTaskManager taskManager;

    /**
     * Ticket that needs to be added
     */

    AbstractTicket ticket;

    /**
     * Constructor of the add command
     * @param taskManager {@link AbstractTaskManager}
     * @param ticket {@link AbstractTicket}
     */

    public AddCommand(AbstractTaskManager taskManager, AbstractTicket ticket) {
        this.taskManager = taskManager;
        this.ticket = ticket;
        if(ticket != null)
            execute();
    }

    public void execute() {
        //System.out.println("Adding ticket to the collection has started");
        try {
            taskManager.addTicket(ticket);
            //System.out.println("AbstractTicket has been successfully added");
        } catch (Exception e) {
            System.err.println("Error got while adding ticket to the collection");
        }
    }
}
