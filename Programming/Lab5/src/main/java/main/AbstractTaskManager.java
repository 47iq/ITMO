package main;

import java.io.Reader;
import java.util.Queue;

/**
 * Abstract class which manages the execution of the application and in particular it manages {@link Queue<AbstractTicket>}.
 * @autor 47iq
 * @version 1.0
 */

public abstract class AbstractTaskManager {

    /**
     * {@link AbstractTicket} collection
     */

    protected Queue<AbstractTicket> tickets;

    /**
     * Input stream of the {@link AbstractTaskManager}
     */

    protected Reader reader;

    /**
     * Name of the data file
     */

    protected String dataFileName;

    /**
     * Starts the execution of {@link AbstractTaskManager}
     */

    public abstract void start();

    /**
     * Adds {@link AbstractTicket} to the {@link #tickets}
     * @param ticket {@link AbstractTicket}
     */

    public abstract void addTicket(AbstractTicket ticket);

    /**
     * Displays info about the current condition of the {@link #tickets}
     */

    public abstract void displayInfo();

    /**
     * Updates {@link AbstractTicket} with the id equal to given in {@link #tickets}
     * @param id int
     * @param ticket {@link AbstractTicket}
     */

    public abstract void updateId(int id, AbstractTicket ticket);

    /**
     * Removes {@link AbstractTicket} with the id equal to given from {@link #tickets}
     * @param id int
     */

    public abstract void removeById(int id);

    /**
     * Clears {@link #tickets}
     */

    public abstract void clear();

    /**
     * Ends the execution of {@link AbstractTaskManager}
     */

    public abstract void exit();

    /**
     * Removes the first element from {@link #tickets}
     */

    public abstract void removeFirst();

    /**
     * Adds {@link AbstractTicket} to {@link #tickets} if it's bigger than every other ticket
     * @param ticket {@link AbstractTicket}
     */

    public abstract void addIfMax(AbstractTicket ticket);

    /**
     * Removes all {@link AbstractTicket} greater than given from {@link #tickets}
     * @param ticket {@link AbstractTicket}
     */

    public abstract void removeGreater(AbstractTicket ticket);

    /**
     * Displays maximal {@link AbstractTicket} by {@link AbstractTicket#getCoordinates()}
     */

    public abstract void maxByCoordinates();

    /**
     * Displays {@link AbstractTicket} with the {@link AbstractTicket#getDiscount()} greater than given from {@link #tickets}
     * @param discount {@link AbstractTicket#getDiscount()}
     */

    public abstract void filterDiscount(double discount);

    /**
     * Displays all {@link AbstractTicket} from {@link #tickets}
     */

    public abstract void displayElements();

    /**
     * Prints {@link AbstractTicket#getRefundable()} of all of {@link AbstractTicket} from {@link #tickets} in the ticket descending order
     */

    public abstract void printRefundable();

    /**
     * Saves data to the {@link #dataFileName}
     */

    public abstract void saveDataToFile();

    /**
     * Checks if {@link AbstractTicket} with {@link AbstractTicket#getId()} equal to given exists in {@link #tickets}
     * @param id int
     * @return boolean (true if exists, false if not)
     */

    public abstract boolean elementExists(int id);
}
