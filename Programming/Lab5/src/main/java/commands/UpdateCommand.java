package commands;

import exceptions.InvalidTicketException;
import exceptions.TicketNotFoundException;
import main.AbstractTicket;
import main.CollectionManager;
import main.CommandReader;
import main.Ticket;

/**
 * Class of update command
 * @autor 47iq
 * @version 1.0
 */

public class UpdateCommand implements Command {

    /**
     * Collection's manager
     */

    private final CollectionManager taskManager;

    /**
     * id of the ticket we want to update
     */

    private int id;

    /**
     * New ticket
     */

    private Ticket ticket;

    private CommandReader commandReader;

    private String arg;


    public UpdateCommand(CollectionManager collectionManager, CommandReader reader, String arg) {
        this.taskManager = collectionManager;
    }

    public void execute() {
        //System.out.println("Updating ticket has started");
        try {
            ticket = commandReader.readTicket();
        } catch (Exception e) {
            System.err.println(new InvalidTicketException().getMessage());
            return;
        }
        try {
            id = Integer.parseInt(arg);
        } catch (Exception e) {
            System.err.println("Invalid ID has been entered. ID must be int.");
            return;
        }
        if (ticket == null)
            return;
        try {
            if(!taskManager.elementExists(id))
                throw new TicketNotFoundException();
            taskManager.updateId(id, ticket);
            //System.out.println("AbstractTicket has been successfully updated");
        } catch (TicketNotFoundException e) {
            System.err.println(e.getMessage());
        }  catch (Exception e) {
            System.err.println("Error got while updating the ticket");
        }
    }

    public static String strConvert() {
        return "update (id) {AbstractTicket}: update ticket with the given id.";
    }
}
