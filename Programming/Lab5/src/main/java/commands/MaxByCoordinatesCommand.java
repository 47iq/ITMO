package commands;

import main.AbstractTaskManager;
import main.AbstractTicket;

/**
 * Class of max_by_coordinates command
 * @autor 47iq
 * @version 1.0
 */

public class MaxByCoordinatesCommand implements Command {

    AbstractTaskManager taskManager;

    /**
     * Constructor of the max_by_coordinates command
     * @param taskManager {@link AbstractTaskManager}
     */

    public MaxByCoordinatesCommand(AbstractTaskManager taskManager) {
        this.taskManager = taskManager;
        execute();
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
