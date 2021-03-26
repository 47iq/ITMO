package server.collection;

import common.Ticket;
import server.ticket.ServerTicket;

import java.io.Reader;
import java.util.Queue;

/**
 * Abstract class implementing {@link CollectionManager} which manages the execution of the application and in particular it manages {@link Queue<Ticket>}.
 *
 * @version 1.0
 * @autor 47iq
 */

public abstract class AbstractQueueManager implements CollectionManager {

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
