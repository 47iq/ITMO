package commands;

import exceptions.InvalidTicketException;
import main.CollectionManager;
import main.CommandFactory;
import main.CommandReader;
import main.Ticket;

/**
 * Class of add_if_max command
 * @autor 47iq
 * @version 1.0
 */

public class AddIfMaxCommand implements Command {

    /**
     * Collection's manager
     */

    private final CollectionManager collectionManager;

    private final CommandReader commandReader;


    public AddIfMaxCommand(CollectionManager collectionManager, CommandReader reader, String arg, CommandFactory commandFactory) {
        this.collectionManager = collectionManager;
        this.commandReader = reader;
    }

    public void execute() {
        /**
         * Ticket we want to add
         */
        Ticket ticket;
        try{
            ticket = commandReader.readTicket();
        } catch (Exception e) {
            System.err.println(new InvalidTicketException().getMessage());
            return;
        }
        //System.out.println("Trying to add ticket...");
        if(ticket == null)
            return;
        try{
            collectionManager.addIfMax(ticket);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error got while adding the ticket");
        }
    }

    public static String strConvert() {
        return "add_if_max {Ticket}: add given ticket if it is bigger than any other ticket of the collection.";
    }
}
