package commands;

import exceptions.InvalidTicketException;
import main.CollectionManager;
import main.CommandReader;
import main.ticket.Ticket;

/**
 * Class of remove_greater command
 * @autor 47iq
 * @version 1.0
 */

public class RemoveGreaterCommand implements Command {

    /**
     * Collection's manager
     */

    private final CollectionManager collectionManager;

    private final CommandReader commandReader;

    public RemoveGreaterCommand(CollectionManager collectionManager, CommandReader reader, String arg) {
        this.collectionManager = collectionManager;
        this.commandReader = reader;
    }

    public void execute() {
        //System.out.println("Removing tickets greater than given has started");
        /**
         * Ticket we want to compare to
         */
        Ticket ticket;
        try{
            ticket = commandReader.readTicket();
        } catch (Exception e) {
            System.err.println(new InvalidTicketException().getMessage());
            return;
        }
        try {
            if(ticket != null)
                collectionManager.removeGreater(ticket);
            //System.out.println("Removing elements has been finished");
        } catch (Exception e) {
            System.err.println("Error got while removing elements");
        }

    }
}
