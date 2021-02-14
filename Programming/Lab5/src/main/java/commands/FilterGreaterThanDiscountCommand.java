package commands;

import main.AbstractTaskManager;
import main.AbstractTicket;

/**
 * Class of filter_greater_than_discount command
 * @autor 47iq
 * @version 1.0
 */

public class FilterGreaterThanDiscountCommand implements Command {

    AbstractTaskManager taskManager;

    double discount;

    /**
     * Constructor of the filter_greater_than_discount command
     * @param taskManager {@link AbstractTaskManager}
     * @param discount double
     */

    public FilterGreaterThanDiscountCommand(AbstractTaskManager taskManager, double discount) {
        this.discount = discount;
        this.taskManager = taskManager;
        execute();
    }

    public void execute() {
        //System.out.println("Displaying elements with discount greater than given");
        try{
            taskManager.filterDiscount(discount);
            //System.out.println("The elements have been successfully displayed");
        } catch (Exception e) {
            System.err.println("Error got while displaying the elements");
        }
    }
}
