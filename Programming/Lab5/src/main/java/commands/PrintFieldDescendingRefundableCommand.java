package commands;

import main.AbstractQueueManager;
import main.CollectionManager;
import main.CommandFactory;
import main.CommandReader;

/**
 * Class of print_field_descending_refundable command
 * @autor 47iq
 * @version 1.0
 */

public class PrintFieldDescendingRefundableCommand implements Command{

    /**
     * Collection's manager
     */

    private final CollectionManager collectionManager;

    public PrintFieldDescendingRefundableCommand(CollectionManager collectionManager, CommandReader reader, String arg, CommandFactory commandFactory) {
        this.collectionManager = collectionManager;
    }

    public void execute() {
        //System.out.println("Displaying refundable field of the elements in the descending order");
        try{
            collectionManager.printRefundable();
            //System.out.println("Refundable fields of the elements had been successfully displayed");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error got while displaying the refundable field");
        }
    }

    public static String strConvert() {
        return "print_field_descending_refundable: get refundable fields of the elements sorted in descending order.";
    }
}
