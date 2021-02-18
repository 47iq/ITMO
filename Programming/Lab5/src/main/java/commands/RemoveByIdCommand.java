package commands;

import exceptions.TicketNotFoundException;
import main.CollectionManager;
import main.CommandReader;

/**
 * Class of remove_by_id command
 * @autor 47iq
 * @version 1.0
 */

public class RemoveByIdCommand implements SimpleCommand {

    public RemoveByIdCommand() {

    }

    public void execute(CollectionManager collectionManager, CommandReader reader, String arg) {
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
