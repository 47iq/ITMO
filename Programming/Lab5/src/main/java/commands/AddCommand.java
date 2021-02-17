package commands;

import exceptions.InvalidTicketException;
import manager.CollectionManager;
import manager.CommandFactory;
import manager.CommandReader;
import ticket.Ticket;

/**
 * Class of add command
 * @autor 47iq
 * @version 1.0
 */

public class AddCommand implements Command {

    /**
     * Manager of the ticket collection
     */

    private final CollectionManager collectionManager;

    /**
     * Command reader
     */

    private final CommandReader commandReader;


    /**
     * Constructor of the add command
     */

    public AddCommand(CollectionManager collectionManager, CommandReader reader, String arg, CommandFactory commandFactory) {
        this.collectionManager = collectionManager;
        this.commandReader = reader;
    }

    public void execute() {
        //System.out.println("Adding ticket to the collection has started");
        Ticket ticket;
        try{
            ticket = commandReader.readTicket();
        } catch (Exception e) {
            System.err.println(new InvalidTicketException().getMessage());
            return;
        }
        if(ticket == null)
            return;
        try {
            collectionManager.addTicket(ticket);
            //System.out.println("AbstractTicket has been successfully added");
        } catch (Exception e) {
            System.err.println("Error got while adding ticket to the collection");
        }
    }

    public static String strConvert() {
        return "add {Ticket}: add ticket to the collection.";
    }
}
