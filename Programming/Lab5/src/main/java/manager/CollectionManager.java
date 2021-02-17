package manager;

import ticket.Ticket;

public interface CollectionManager {
    /**
     * Starts the execution of {@link CollectionManager}
     */

    void parseDataToCollection();

    /**
     * Adds {@link Ticket} to the collection
     * @param ticket {@link Ticket}
     */

    void addTicket(Ticket ticket);

    /**
     * Displays info about the current condition of the collection
     */

    void displayInfo();

    /**
     * Updates {@link Ticket} with the id equal to given in collection
     * @param id int
     * @param ticket ticket we want to insert
     */

    void updateId(int id, Ticket ticket);

    /**
     * Removes {@link Ticket} with the id equal to given from collection
     * @param id id of the ticket we want to remove
     */

    void removeById(int id);

    /**
     * Clears collection
     */

    void clear();

    /**
     * Removes the first element from collection
     */

    void removeFirst();

    /**
     * Adds {@link Ticket} to collection if it's bigger than every other ticket
     * @param ticket we want to add
     */

    void addIfMax(Ticket ticket);

    /**
     * Removes all {@link Ticket} greater than given from collcetion
     * @param ticket we want to compare tickets to
     */

    void removeGreater(Ticket ticket);

    /**
     * Displays maximal {@link Ticket} by coordinates
     */

    void maxByCoordinates();

    /**
     * Displays {@link Ticket} with the discount greater than given from collection
     * @param discount discount we want to filter
     */

    void filterDiscount(double discount);

    /**
     * Displays all {@link Ticket} from collection
     */

    void displayElements();

    /**
     * Prints {@link Ticket#getRefundable()} of all of {@link Ticket} from collection in the ticket descending order
     */

    void printRefundable();

    /**
     * Saves data to the file
     */

    void saveData();

    /**
     * Checks if {@link Ticket} with {@link Ticket#getId()} equal to given exists in collection
     * @param id int
     * @return boolean (true if exists, false if not)
     */

    boolean elementExists(int id);
}
