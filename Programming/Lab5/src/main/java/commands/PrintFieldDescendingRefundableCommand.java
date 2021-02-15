package commands;

import main.AbstractQueueManager;
import main.CollectionManager;

/**
 * Class of print_field_descending_refundable command
 * @autor 47iq
 * @version 1.0
 */

public class PrintFieldDescendingRefundableCommand implements Command{

    /**
     * Collection's manager
     */

    private final CollectionManager taskManager;

    /**
     * Constructor of the print_field_descending_refundable command
     * @param taskManager collection's manager
     */

    public PrintFieldDescendingRefundableCommand(CollectionManager taskManager) {
        this.taskManager = taskManager;
    }

    public void execute() {
        //System.out.println("Displaying refundable field of the elements in the descending order");
        try{
            taskManager.printRefundable();
            //System.out.println("Refundable fields of the elements had been successfully displayed");
        } catch (Exception e) {
            System.err.println("Error got while displaying the refundable field");
        }
    }
}
