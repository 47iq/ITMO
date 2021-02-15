package commands;

import exceptions.TicketNotFoundException;
import main.AbstractQueueManager;
import main.CollectionManager;

/**
 * Class of remove_by_id command
 * @autor 47iq
 * @version 1.0
 */

public class RemoveByIdCommand implements Command {

    /**
     * Collection's manager
     */

    private final CollectionManager taskManager;

    /**
     * Id of the ticket we want to remove
     */

    private final int id;

    /**
     * Constructor of the remove_by_id command
     * @param taskManager collection's manager
     * @param id id of the ticket we want to remove
     */

    public RemoveByIdCommand(CollectionManager taskManager, int id) {
        this.taskManager = taskManager;
        this.id = id;
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
