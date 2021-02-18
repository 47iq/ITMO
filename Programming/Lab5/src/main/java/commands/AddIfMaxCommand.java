package commands;

import exceptions.InvalidTicketException;
import main.CollectionManager;
import main.CommandReader;
import main.ticket.Ticket;

/**
 * Class of add_if_max command
 * @autor 47iq
 * @version 1.0
 */

public class AddIfMaxCommand implements SimpleCommand {

    public AddIfMaxCommand() {
    }

    public void execute(CollectionManager collectionManager, CommandReader commandReader, String arg) {
        Ticket ticket;
        try{
            ticket = commandReader.readTicket();
        } catch (Exception e) {
            System.err.println(new InvalidTicketException().getMessage());
            return;
        }
        //System.out.println("Trying to add manager.ticket...");
        if(ticket == null)
            return;
        try{
            collectionManager.addIfMax(ticket);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error got while adding the manager.ticket");
        }
    }
}
