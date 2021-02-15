package main;

import exceptions.InvalidIdException;

import java.io.Reader;
import java.util.*;

/**
 * Abstract class implementing {@link CollectionManager} which manages the execution of the application and in particular it manages {@link Queue<AbstractTicket>}.
 * @autor 47iq
 * @version 1.0
 */

public abstract class AbstractQueueManager implements CollectionManager{

    /**
     * {@link AbstractTicket} collection
     */

    protected Queue<AbstractTicket> tickets;

    /**
     * Input stream of the {@link AbstractQueueManager}
     */

    protected Reader reader;

    /**
     * Name of the data file
     */

    protected String dataFileName;

    /**
     * Set of used id's
     */

    private static Set<Integer> idSet;

    public abstract void start();

    public abstract void addTicket(AbstractTicket ticket);

    public abstract void displayInfo();

    public abstract void updateId(int id, AbstractTicket ticket);

    public abstract void removeById(int id);

    public abstract void clear();

    public abstract void exit();

    public abstract void removeFirst();

    public abstract void addIfMax(AbstractTicket ticket);

    public abstract void removeGreater(AbstractTicket ticket);

    public abstract void maxByCoordinates();

    public abstract void filterDiscount(double discount);

    public abstract void displayElements();

    public abstract void printRefundable();

    public abstract void saveDataToFile();

    public abstract boolean elementExists(int id);

    /**
     * Adds ticket ID to {@link #idSet}
     * @param id id we want to add
     */

    public static void addID(int id) {
        if(idSet.contains(id))
            throw new InvalidIdException();
        idSet.add(id);
    }

    /**
     * Gets an unique id
     * @return unique id
     */

    public static int getID() {
        int id;
        if(idSet.size() == Integer.MAX_VALUE) {
            System.err.println("Error. Maximal number of IDs has been reached");
            System.exit(1);
        }
        while (true) {
            id = (int) (Math.random()*Integer.MAX_VALUE);
            if(!idExists(id))
                return id;
        }
    }

    /**
     * Checks if id is in {@link #idSet}
     * @param id id we want tot check
     * @return true if exists, false if not
     */

    public static boolean idExists(int id) {
        return idSet.contains(id);
    }

    /**
     * Method to initialize {@link #idSet}
     */

    public static void createSet() {
        idSet = new HashSet<>();
    }
}
