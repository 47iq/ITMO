package server.collection;

import common.Ticket;
import org.apache.logging.log4j.LogManager;
import server.ObjectFactory;
import server.datawork.DataBaseManager;
import server.datawork.TicketReader;
import server.exceptions.InvalidTicketException;
import server.ticket.ServerDefaultTicket;
import server.ticket.ServerTicket;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class, which is the realization of {@link AbstractQueueManager}
 * @autor 47iq
 * @version 1.0
 */

public class QueueManager extends AbstractQueueManager {

    /**
     * Creation time of {@link #tickets}
     */

    private final ZonedDateTime creationDate;

    private final TicketReader ticketReader;

    private final ObjectFactory ticketFactory;

    private final DataBaseManager dataBaseManager;

    private static final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

    public QueueManager(DataBaseManager dataBaseManager, ObjectFactory ticketFactory){
        this.ticketReader = dataBaseManager.getTicketsData();
        this.dataBaseManager = dataBaseManager;
        tickets = new PriorityQueue<>();
        creationDate = ZonedDateTime.now(ZoneId.of("Europe/Moscow"));
        this.ticketFactory = ticketFactory;
    }

    /**
     * Parses data from {@link #dataFileName} to the {@link #tickets} using {@link #reader} if data is a valid JSON
     */

    public void parseDataToCollection(){
        try {
            Collection<ServerTicket> tickets = ticketReader.getTickets();
            for(ServerTicket ticket: tickets)
                try {
                    addTicket(ticket);
                } catch (Exception e) {
                    LogManager.getLogger().warn("Error got adding manager.ticket (id " + ticket.getId() + "). Ticket won't be added.");
                }
        } catch (Exception e) {
            LogManager.getLogger().error("Error got while parsing data to collection");
        }
    }

    private void addTicket(ServerTicket ticket) {
        tickets.add(ticket);
    }

    public void convertAddTicket(Ticket ticket) {
        try {
            ServerTicket serverTicket = ticketFactory.convertTicket(ticket);
            dataBaseManager.getTicketsData().add(serverTicket);
            tickets.add(serverTicket);
            LogManager.getLogger().info("Added ticket {} ", serverTicket.toString());
        } catch (Exception e) {
            throw new InvalidTicketException();
        }
    }

    public List<String> displayInfo() {
        List<String> info =  new ArrayList<>();
        info.add(tickets.getClass().toString());
        info.add(String.valueOf(tickets.size()));
        info.add(creationDate.toString());
        LogManager.getLogger().info("Sent info about collection.");
        return info;
    }

    public List<Ticket> displayElements() {
        LogManager.getLogger().info("Sent collection elements.");
        return getTicketsList();
    }

    /**
     * Gets {@link List<Ticket>} containing all of the {@link #tickets}
     * @return ticketsList {@link List<Ticket>}
     */

    private List<Ticket> getTicketsList() {
        return tickets.stream().sorted(Ticket::compareTo).collect(Collectors.toList());
    }

    public List<Boolean> getRefundableList() {
        LogManager.getLogger().info("Sent refundable list.");
        return tickets
                .stream()
                .map(Ticket::getRefundable)
                .sorted((o1, o2) -> o1!=null & o2!=null ? -o1.compareTo(o2) : o1!=null ? -1 : 1)
                .collect(Collectors.toList());
    }

    public void updateId(int id, Ticket myTicket, String owner) {
        try {
            lock();
            dataBaseManager.getTicketsData().update(ticketFactory.convertTicket(myTicket), id, owner);
            tickets = Stream
                    .concat(tickets
                                    .stream()
                                    .filter(x -> x.getId() == id && x.getOwner().equals(owner))
                                    .map(x -> x = ServerTicket.copyFromTicket(x, myTicket)),
                            tickets
                                    .stream()
                                    .filter(x -> x.getId() != id || !x.getOwner().equals(owner)))
                    .collect(Collectors.toCollection(PriorityQueue::new));
            LogManager.getLogger().info("Updated ticket with id {}.", id);
            unlock();
        } catch (SQLException e) {
            throw new InvalidTicketException();
        }
    }

    public void removeById(int id, String owner) {
        try {
            lock();
            dataBaseManager.getTicketsData().remove(id, owner);
            tickets = tickets.stream().
                    filter(x -> x.getId() != id || !x.getOwner().equals(owner))
                    .collect(Collectors.toCollection(PriorityQueue::new));
            LogManager.getLogger().info("Removed ticket with id {}.", id);
            unlock();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }


    public void clear(String owner) {
        try {
            lock();
            dataBaseManager.getTicketsData().clear(owner);
            tickets = tickets.stream().filter(x -> !x.getOwner().equals(owner)).collect(Collectors.toCollection(PriorityQueue::new));
            LogManager.getLogger().info("Cleared the collection.");
            unlock();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }


    public void removeFirst(String owner) {
        try {
            lock();
            ServerTicket ticket = tickets.stream().filter(x -> x.getOwner().equals(owner)).max(Ticket::compareTo).get();
            dataBaseManager.getTicketsData().remove(ticket.getId(), owner);
            tickets.remove(ticket);
            LogManager.getLogger().info("Removed first ticket.");
            unlock();
        } catch (NoSuchElementException ignored) {
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    /**
     * Sorts {@link #tickets} using {@link TreeSet<ServerDefaultTicket>}
     */

    public void sort() {
        SortedSet<ServerTicket> ticketsSet = new TreeSet<>();
        while (!tickets.isEmpty()){
            ticketsSet.add(tickets.poll());
        }
        tickets = new PriorityQueue<>(ticketsSet);
    }


    public void addIfMax(Ticket ticket) {
        try {
            if (tickets.stream()
                    .max(Ticket::compareTo)
                    .map(x -> x.compareTo(ticket) < 0).isPresent()) {
                    convertAddTicket(ticket);
            }
            LogManager.getLogger().info("Add If Max request has been managed.");
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void removeGreater(Ticket ticket, String owner) {
        try {
            readWriteLock.writeLock().lock();
            ArrayList<ServerTicket> ticketList = tickets
                    .stream()
                    .filter(x -> x.compareTo(ticket) > 0 && x.getOwner().equals(owner))
                    .collect(Collectors.toCollection(ArrayList::new));
            for (ServerTicket serverTicket : ticketList)
                dataBaseManager.getTicketsData().remove(serverTicket.getId(), owner);
            tickets.removeAll(ticketList);
            LogManager.getLogger().info("Removed tickets greater than given.");
            readWriteLock.writeLock().unlock();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public Ticket maxByCoordinates() {
        LogManager.getLogger().info("Sent max by coordinates ticket.");
        return tickets
                .stream()
                .max((Comparator<Ticket>) (o1, o2) -> o1.getCoordinates().compareTo(o2.getCoordinates()))
                .get();
    }

    public List<Ticket> filterDiscount(double discount) {
        LogManager.getLogger().info("Send discount-filtered collection (discount = {})", discount);
        return tickets
                .stream()
                .filter(x->x.getDiscount()>(discount))
                .collect(Collectors.toList());
    }

    private void lock() {
        readWriteLock.writeLock().lock();
        readWriteLock.readLock().lock();
    }

    private void unlock() {
        readWriteLock.writeLock().unlock();
        readWriteLock.readLock().unlock();
    }
}
