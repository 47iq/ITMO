package commands;

import main.AbstractTicket;
import main.AbstractTaskManager;

/**
 * Class of remove_greater command
 * @autor 47iq
 * @version 1.0
 */

public class RemoveGreaterCommand implements Command {

    AbstractTaskManager taskManager;

    AbstractTicket ticket;

    /**
     * Constructor of the remove_greater command
     * @param taskManager {@link AbstractTaskManager}
     * @param ticket {@link AbstractTicket}
     */

    public RemoveGreaterCommand(AbstractTaskManager taskManager, AbstractTicket ticket) {
        this.taskManager = taskManager;
        this.ticket = ticket;
        execute();
    }

    public void execute() {
        //System.out.println("Removing tickets greater than given has started");
        try {
            if(ticket != null)
                taskManager.removeGreater(ticket);
            else
                throw new IllegalArgumentException();
            //System.out.println("Removing elements has been finished");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error got while removing elements");
        }

    }
}
