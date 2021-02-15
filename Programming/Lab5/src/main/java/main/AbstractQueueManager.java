package main;

import java.io.Reader;
import java.util.Queue;

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
}
