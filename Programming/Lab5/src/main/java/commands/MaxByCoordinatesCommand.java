package commands;

import main.AbstractQueueManager;
import main.CollectionManager;

/**
 * Class of max_by_coordinates command
 * @autor 47iq
 * @version 1.0
 */

public class MaxByCoordinatesCommand implements Command {

    /**
     * Collection's manager
     */

    private final CollectionManager taskManager;

    /**
     * Constructor of the max_by_coordinates command
     * @param taskManager collection's manager
     */

    public MaxByCoordinatesCommand(CollectionManager taskManager) {
        this.taskManager = taskManager;
    }

    public void execute() {
        //System.out.println("Getting the max by coordinates ticket");
        try{
            taskManager.maxByCoordinates();
            //System.out.println("The max by coordinates ticket has been successfully displayed");
        } catch (Exception e) {
            System.err.println("Error got while displaying the max by coordinates element");
        }
    }
}
