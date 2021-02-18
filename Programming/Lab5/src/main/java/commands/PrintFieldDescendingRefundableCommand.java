package commands;

import main.CollectionManager;
import main.CommandReader;

import java.util.List;

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

    public PrintFieldDescendingRefundableCommand(CollectionManager collectionManager, CommandReader reader, String arg) {
        this.collectionManager = collectionManager;
    }

    public void execute() {
        //System.out.println("Displaying refundable field of the elements in the descending order");
        try{
            List<Boolean> refundableList = collectionManager.getRefundableList();
            for(Boolean refundable: refundableList) {
                System.out.println(refundable);
            }
            //System.out.println("Refundable fields of the elements had been successfully displayed");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error got while displaying the refundable field");
        }
    }
}
