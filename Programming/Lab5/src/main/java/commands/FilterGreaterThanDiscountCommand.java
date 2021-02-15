package commands;

import main.AbstractCollectionManager;

/**
 * Class of filter_greater_than_discount command
 * @autor 47iq
 * @version 1.0
 */

public class FilterGreaterThanDiscountCommand implements Command {

    /**
     * Collection's manager
     */

    private final AbstractCollectionManager taskManager;

    /**
     * Discount we want to filter
     */

    private final double discount;

    /**
     * Constructor of the filter_greater_than_discount command
     * @param taskManager collection's manager
     * @param discount discount we want to filter
     */

    public FilterGreaterThanDiscountCommand(AbstractCollectionManager taskManager, double discount) {
        this.discount = discount;
        this.taskManager = taskManager;
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
