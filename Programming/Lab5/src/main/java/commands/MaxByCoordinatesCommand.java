package commands;

import main.*;

/**
 * Class of max_by_coordinates command
 * @autor 47iq
 * @version 1.0
 */

public class MaxByCoordinatesCommand implements MessagingCommand {


    public MaxByCoordinatesCommand() {
    }

    public void execute(CollectionManager collectionManager, CommandReader reader, String arg, Messenger messenger) {
        //System.out.println("Getting the max by coordinates manager.ticket");
        try{
            System.out.println(messenger.getTicketMessage(collectionManager.maxByCoordinates()));
            //System.out.println("The max by coordinates manager.ticket has been successfully displayed");
        } catch (Exception e) {
            System.err.println("Error got while displaying the max by coordinates element");
        }
    }
}
