package commands;

/**
 * Class created for returning empty commands which do nothing
 */

public class EmptyCommand implements Command{

    /**
     * Constructor of an empty command
     */

    public EmptyCommand() {

    }

    public void execute() {
        //DO NOTHING
    }
}
