package commands;

import main.AbstractTaskManager;
import main.AbstractTicket;

/**
 * Class of add_if_max command
 * @autor 47iq
 * @version 1.0
 */

public class AddIfMaxCommand implements Command {

    AbstractTaskManager taskManager;
    AbstractTicket ticket;

    /**
     * Constructor of the add_if_max command
     * @param taskManager {@link AbstractTaskManager}
     * @param ticket {@link AbstractTicket}
     */

    public AddIfMaxCommand(AbstractTaskManager taskManager, AbstractTicket ticket) {
        this.taskManager = taskManager;
        this.ticket = ticket;
        execute();
    }

    public void execute() {
        //System.out.println("Trying to add ticket...");
        try{
            taskManager.addIfMax(ticket);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error got while adding the ticket");
        }
    }
}
