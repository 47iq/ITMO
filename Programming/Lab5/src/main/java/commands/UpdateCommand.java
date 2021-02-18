package commands;

import exceptions.InvalidTicketException;
import exceptions.TicketNotFoundException;
import main.*;
import main.ticket.Ticket;

/**
 * Class of update command
 * @autor 47iq
 * @version 1.0
 */

public class UpdateCommand implements SimpleCommand {

    public UpdateCommand() {

    }

    public void execute(CollectionManager collectionManager, CommandReader commandReader, String arg) {
        //System.out.println("Updating manager.ticket has started");
        Ticket ticket;
        try {
            ticket = commandReader.readTicket();
        } catch (Exception e) {
            System.err.println(new InvalidTicketException().getMessage());
            return;
        }
        int id;
        try {
            id = Integer.parseInt(arg);
        } catch (Exception e) {
            System.err.println("Invalid ID has been entered. ID must be int.");
            return;
        }
        if (ticket == null)
            return;
        try {
            if(!collectionManager.elementExists(id))
                throw new TicketNotFoundException();
            collectionManager.updateId(id, ticket);
            //System.out.println("AbstractTicket has been successfully updated");
        } catch (TicketNotFoundException e) {
            System.err.println(e.getMessage());
        }  catch (Exception e) {
            System.err.println("Error got while updating the manager.ticket");
        }
    }
}
