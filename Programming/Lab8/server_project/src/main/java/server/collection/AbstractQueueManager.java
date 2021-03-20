package server.collection;

import common.Ticket;
import server.exceptions.InvalidIdException;
import org.apache.logging.log4j.LogManager;
import server.ticket.ServerTicket;

import java.io.Reader;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * Abstract class implementing {@link CollectionManager} which manages the execution of the application and in particular it manages {@link Queue<Ticket>}.
 * @autor 47iq
 * @version 1.0
 */

public abstract class AbstractQueueManager implements CollectionManager{

    /**
     * {@link Ticket} collection
     */

    protected Queue<ServerTicket> tickets;

    /**
     * Input stream of the {@link AbstractQueueManager}
     */

    protected Reader reader;

    /**
     * Name of the data file
     */

    protected String dataFileName;
}
