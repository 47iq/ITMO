package commands;

import exceptions.TicketNotFoundException;
import main.AbstractTaskManager;
import main.AbstractTicket;

/**
 * Class of remove_by_id command
 * @autor 47iq
 * @version 1.0
 */

public class RemoveByIdCommand implements Command {
    AbstractTaskManager taskManager;
    int id;

    /**
     * Constructor of the remove_by_id command
     * @param taskManager {@link AbstractTaskManager}
     * @param id int
     */

    public RemoveByIdCommand(AbstractTaskManager taskManager, int id) {
        this.taskManager = taskManager;
        this.id = id;
        execute();
    }

    public void execute() {
        //System.out.println("Trying to remove ticket by id.");
        try {
            if(!taskManager.elementExists(id))
                throw new TicketNotFoundException();
            taskManager.removeById(id);
            //System.out.println("AbstractTicket has been successfully removed.");
        } catch (TicketNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Error got  while removing the ticket");
        }
    }
}
