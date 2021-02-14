package commands;

import main.AbstractTaskManager;
import main.AbstractTicket;

/**
 * Class of help command
 * @autor 47iq
 * @version 1.0
 */

public class HelpCommand implements Command {

    AbstractTaskManager taskManager;

    /**
     * Constructor of the help command
     * @param taskManager {@link AbstractTaskManager}
     */

    public HelpCommand(AbstractTaskManager taskManager) {
        this.taskManager = taskManager;
        execute();
    }

    public void execute() {
        System.out.println("The list of existing commands:\n\nhelp : get information about the commands\ninfo : get information about the collection\n" +
                "show: get the collection's elements\n" + "add {AbstractTicket}: add ticket to the collection\n" +
                "update (id) {AbstractTicket}: update ticket with the given id\nremove_by_id (id): remove ticket with the given id\n" +
                "clear: clear the collection\nsave: save the collection into the file\nexecute_script (file_name)" +
                ": execute script from the given file\nexit: stop the program\nremove_first: remove first ticket from the collection\n" +
                "add_if_max {AbstractTicket}: add given ticket if it is bigger than any other ticket of the collection\n" +
                "remove_greater {AbstractTicket}: remove tickets bigger than given\nmax_by coordinates: get the ticket, biggest by coordinates\n" +
                "filter_greater_than_discount (discount): get elements, which have bigger discount than given\n" +
                "print_field_descending_refundable: get refundable fields of the elements sorted in descending order\n\n" +
                "Notice that arguments in round brackets \"( )\" must be entered in the same line as the command!");
    }
}
