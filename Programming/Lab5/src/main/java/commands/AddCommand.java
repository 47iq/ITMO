package commands;

import exceptions.InvalidTicketException;
import main.CollectionManager;
import main.CommandReader;
import main.ticket.Ticket;

/**
 * Class of add command
 * @autor 47iq
 * @version 1.0
 */

public class AddCommand implements SimpleCommand {

    /**
     * Constructor of the add command
     */

    public AddCommand() {

    }

    public void execute(CollectionManager collectionManager, CommandReader commandReader, String arg) {
        //System.out.println("Adding manager.ticket to the collection has started");
        Ticket ticket;
        try{
            ticket = commandReader.readTicket();
        } catch (Exception e) {
            //e.printStackTrace();
            System.err.println(new InvalidTicketException().getMessage());
            return;
        }
        if(ticket == null)
            return;
        try {
            collectionManager.convertAddTicket(ticket);
            //System.out.println("AbstractTicket has been successfully added");
        } catch (Exception e) {
            System.err.println("Error got while adding manager.ticket to the collection");
        }
    }
}
