package commands;

import main.*;
import main.ticket.Ticket;

import java.util.List;

/**
 * Class of show command
 * @autor 47iq
 * @version 1.0
 */

public class ShowCommand implements Command {

    /**
     * Collection's manager
     */

    private final CollectionManager collectionManager;

    private final Messenger messenger;


    public ShowCommand(CollectionManager collectionManager, CommandReader reader, String arg, Messenger messenger) {
        this.collectionManager = collectionManager;
        this.messenger = messenger;
    }

    public void execute() {
        //System.out.println("Displaying tickets");
        try {
            List<Ticket> ticketList = collectionManager.displayElements();
            for (Ticket ticket: ticketList) {
                System.out.println(messenger.getTicketMessage(ticket));
            }
            //System.out.println("Tickets have been successfully displayed");
        } catch (Exception e) {
            System.err.println("Error got while displaying tickets");
        }
    }
}
