package commands;

import main.*;
import main.ticket.Ticket;

import java.util.List;

/**
 * Class of filter_greater_than_discount command
 * @autor 47iq
 * @version 1.0
 */

public class FilterGreaterThanDiscountCommand implements Command {

    /**
     * Collection's manager
     */

    private final CollectionManager collectionManager;

    private final String arg;

    private final Messenger messenger;


    public FilterGreaterThanDiscountCommand(CollectionManager collectionManager, CommandReader reader, String arg, Messenger messenger) {
        this.collectionManager = collectionManager;
        this.arg = arg;
        this.messenger = messenger;
    }

    public void execute() {
        //System.out.println("Displaying elements with discount greater than given");
        /**
         * Discount we want to filter
         */
        double discount;
        try {
            discount = Double.parseDouble(arg);
        } catch (Exception e) {
            System.err.println("Invalid discount value has been entered. Discount must be double.");
            return;
        }
        try {
            List<Ticket> tickets = collectionManager.filterDiscount(discount);
            for(Ticket ticket: tickets)
                System.out.println(messenger.getTicketMessage(ticket));
            //System.out.println("The elements have been successfully displayed");
        } catch (Exception e) {
            System.err.println("Error got while displaying the elements");
        }
    }
}
