package server.collection;

import common.Ticket;
import common.UpdateData;
import server.connection.ConnectionManager;

import java.util.List;

/**
 * Interface of the ticket collection manager
 */

public interface CollectionManager {
    /**
     * Starts the execution of {@link CollectionManager}
     */

    void parseDataToCollection();

    /**
     * Adds {@link Ticket} to the collection
     *
     * @param ticket {@link Ticket}
     */

    void convertAddTicket(Ticket ticket);

    /**
     * Displays info about the current condition of the collection
     */

    String[] displayInfo();

    /**
     * Updates {@link Ticket} with the id equal to given in collection
     *
     * @param id         int
     * @param ticket     manager.ticket we want to insert
     * @param updateData
     */

    void updateId(int id, Ticket ticket, String owner, UpdateData updateData);

    /**
     * Removes {@link Ticket} with the id equal to given from collection
     *
     * @param id id of the manager.ticket we want to remove
     */

    void removeById(int id, String owner);

    /**
     * Clears collection
     */

    void clear(String owner);

    /**
     * Removes the first element from collection
     */

    void removeFirst(String owner);

    /**
     * Adds {@link Ticket} to collection if it's bigger than every other manager.ticket
     *
     * @param ticket we want to add
     */

    void addIfMax(Ticket ticket);

    /**
     * Removes all {@link Ticket} greater than given from collcetion
     *
     * @param ticket we want to compare tickets to
     */

    void removeGreater(Ticket ticket, String owner);

    /**
     * Displays maximal {@link Ticket} by coordinates
     */

    Ticket maxByCoordinates();

    /**
     * Displays {@link Ticket} with the discount greater than given from collection
     *
     * @param discount discount we want to filter
     */

    List<Ticket> filterDiscount(double discount);

    /**
     * Displays all {@link Ticket} from collection
     */

    List<Ticket> displayElements();

    /**
     * Prints {@link Ticket#getRefundable()} of all of {@link Ticket} from collection in the manager.ticket descending order
     */

    List<Boolean> getRefundableList();

    List<Ticket> getTicketList();
}
