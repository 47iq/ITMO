package commands;

import main.AbstractTaskManager;
import main.AbstractTicket;

/**
 * Class of print_field_descending_refundable command
 * @autor 47iq
 * @version 1.0
 */

public class PrintFieldDescendingRefundableCommand implements Command{

    AbstractTaskManager taskManager;

    /**
     * Constructor of the print_field_descending_refundable command
     * @param taskManager {@link AbstractTaskManager}
     */

    public PrintFieldDescendingRefundableCommand(AbstractTaskManager taskManager) {
        this.taskManager = taskManager;
        execute();
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
