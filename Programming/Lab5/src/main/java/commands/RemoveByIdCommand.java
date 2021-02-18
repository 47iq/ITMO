package commands;

import exceptions.TicketNotFoundException;
import main.CollectionManager;
import main.CommandReader;

/**
 * Class of remove_by_id command
 * @autor 47iq
 * @version 1.0
 */

public class RemoveByIdCommand implements Command {

    /**
     * Collection's manager
     */

    private final CollectionManager collectionManager;

    private final String arg;


    public RemoveByIdCommand(CollectionManager collectionManager, CommandReader reader, String arg) {
        this.collectionManager = collectionManager;
        this.arg = arg;
    }

    public void execute() {
        //System.out.println("Trying to remove manager.ticket by id.");
        /**
         * Id of the manager.ticket we want to remove
         */
        int id;
        try{
            id = Integer.parseInt(arg);
        } catch (Exception e) {
            System.err.println("Error. ID must be int.");
            return;
        }
        try {
            if(!collectionManager.elementExists(id))
                throw new TicketNotFoundException();
            collectionManager.removeById(id);
            //System.out.println("AbstractTicket has been successfully removed.");
        } catch (TicketNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Error got  while removing the manager.ticket");
        }
    }
}
