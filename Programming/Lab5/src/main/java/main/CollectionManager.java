package main;

public interface CollectionManager {
    /**
     * Starts the execution of {@link CollectionManager}
     */

    void start();

    /**
     * Adds {@link AbstractTicket} to the collection
     * @param ticket {@link AbstractTicket}
     */

    void addTicket(AbstractTicket ticket);

    /**
     * Displays info about the current condition of the collection
     */

    void displayInfo();

    /**
     * Updates {@link AbstractTicket} with the id equal to given in collection
     * @param id int
     * @param ticket ticket we want to insert
     */

    void updateId(int id, AbstractTicket ticket);

    /**
     * Removes {@link AbstractTicket} with the id equal to given from {@link #tickets}
     * @param id id of the ticket we want to remove
     */

    void removeById(int id);

    /**
     * Clears collection
     */

    void clear();

    /**
     * Ends the execution of {@link CollectionManager}
     */

    void exit();

    /**
     * Removes the first element from collection
     */

    void removeFirst();

    /**
     * Adds {@link AbstractTicket} to collection if it's bigger than every other ticket
     * @param ticket we want to add
     */

    void addIfMax(AbstractTicket ticket);

    /**
     * Removes all {@link AbstractTicket} greater than given from collcetion
     * @param ticket we want to compare tickets to
     */

    void removeGreater(AbstractTicket ticket);

    /**
     * Displays maximal {@link AbstractTicket} by {@link AbstractTicket#getCoordinates()}
     */

    void maxByCoordinates();

    /**
     * Displays {@link AbstractTicket} with the {@link AbstractTicket#getDiscount()} greater than given from collection
     * @param discount discount we want to filter
     */

    void filterDiscount(double discount);

    /**
     * Displays all {@link AbstractTicket} from collection
     */

    void displayElements();

    /**
     * Prints {@link AbstractTicket#getRefundable()} of all of {@link AbstractTicket} from collection in the ticket descending order
     */

    void printRefundable();

    /**
     * Saves data to the file
     */

    public abstract void saveDataToFile();

    /**
     * Checks if {@link AbstractTicket} with {@link AbstractTicket#getId()} equal to given exists in {@link #tickets}
     * @param id int
     * @return boolean (true if exists, false if not)
     */

    public abstract boolean elementExists(int id);
}
