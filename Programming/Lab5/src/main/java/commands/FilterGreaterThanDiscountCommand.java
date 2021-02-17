package commands;

import manager.CollectionManager;
import manager.CommandFactory;
import manager.CommandReader;

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

    /**
     * Discount we want to filter
     */

    private double discount;

    private String arg;


    public FilterGreaterThanDiscountCommand(CollectionManager collectionManager, CommandReader reader, String arg, CommandFactory commandFactory) {
        this.collectionManager = collectionManager;
    }

    public void execute() {
        //System.out.println("Displaying elements with discount greater than given");
        try{
            discount = Double.parseDouble(arg);
        } catch (Exception e) {
            System.err.println("Invalid discount value has been entered. Discount must be double.");
            return;
        }
        try{
            collectionManager.filterDiscount(discount);
            //System.out.println("The elements have been successfully displayed");
        } catch (Exception e) {
            System.err.println("Error got while displaying the elements");
        }
    }

    public static String strConvert() {
        return "filter_greater_than_discount (discount): get elements, which have bigger discount than given.";
    }
}
