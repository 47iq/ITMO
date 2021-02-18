package main;

import exceptions.InvalidIdException;
import main.ticket.Ticket;

import java.io.Reader;
import java.util.*;

/**
 * Abstract class implementing {@link CollectionManager} which manages the execution of the application and in particular it manages {@link Queue<Ticket>}.
 * @autor 47iq
 * @version 1.0
 */

public abstract class AbstractQueueManager implements CollectionManager{

    /**
     * {@link Ticket} collection
     */

    protected Queue<Ticket> tickets;

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

    public abstract boolean elementExists(int id);

    /**
     * Adds manager.ticket ID to {@link #idSet}
     * @param id id we want to add
     */

    public static void addID(int id) {
        if(idSet.contains(id))
            throw new InvalidIdException();
        idSet.add(id);
    }

    /**
     * Removes ID from {@link #idSet}
     * @param id id we want to remove
     */

    public static void removeID(int id) {
        idSet.remove(id);
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
        idSet = new HashSet<Integer>();
    }
}
