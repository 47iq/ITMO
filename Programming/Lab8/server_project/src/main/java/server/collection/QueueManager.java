package server.collection;

import common.DefaultTicket;
import common.Ticket;
import common.UpdateData;
import org.apache.logging.log4j.LogManager;
import server.ObjectFactory;
import server.connection.ConnectionManager;
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

/**
 * Class, which is the realization of {@link AbstractQueueManager}
 *
 * @version 1.0
 * @autor 47iq
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

    public QueueManager(DataBaseManager dataBaseManager, ObjectFactory ticketFactory) {
        this.ticketReader = dataBaseManager.getTicketsData();
        this.dataBaseManager = dataBaseManager;

        tickets = new PriorityQueue<>();
        creationDate = ZonedDateTime.now(ZoneId.of("Europe/Moscow"));
        this.ticketFactory = ticketFactory;
    }

    /**
     * Parses data from {@link #dataFileName} to the {@link #tickets} using {@link #reader} if data is a valid JSON
     */

    @Override
    public void parseDataToCollection() {
        try {
            Collection<ServerTicket> tickets = ticketReader.getTickets();
            for (ServerTicket ticket : tickets)
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

    @Override
    public void convertAddTicket(Ticket ticket) {
        try {
            lock();
            ServerTicket serverTicket = ticketFactory.convertTicket(ticket);
            dataBaseManager.getTicketsData().add(serverTicket);
            tickets.add(serverTicket);
            LogManager.getLogger().info("Added ticket {} ", serverTicket.toString());
            unlock();
        } catch (Exception e) {
            e.printStackTrace();
            throw new InvalidTicketException();
        }
    }

    @Override
    public String[] displayInfo() {
        String[] info = new String[3];
        info[0] = (tickets.getClass().toString());
        info[1] = (String.valueOf(tickets.size()));
        info[2] = (creationDate.toString());
        LogManager.getLogger().info("Sent info about collection.");
        return info;
    }

    @Override
    public List<Ticket> displayElements() {
        LogManager.getLogger().info("Sent collection elements.");
        return getTicketsList();
    }

    /**
     * Gets {@link List<Ticket>} containing all of the {@link #tickets}
     *
     * @return ticketsList {@link List<Ticket>}
     */

    private List<Ticket> getTicketsList() {
        return tickets.stream().sorted(Ticket::compareTo).collect(Collectors.toList());
    }

    @Override
    public List<Boolean> getRefundableList() {
        LogManager.getLogger().info("Sent refundable list.");
        return tickets
                .stream()
                .map(Ticket::getRefundable)
                .sorted((o1, o2) -> o1 != null & o2 != null ? -o1.compareTo(o2) : o1 != null ? -1 : 1)
                .collect(Collectors.toList());
    }

    @Override
    public void updateId(int id, Ticket myTicket, String owner, UpdateData updateData) {
        try {
            lock();
            dataBaseManager.getTicketsData().update(ticketFactory.convertTicket(myTicket), id, owner, updateData);
            Ticket ticket = tickets
                    .stream()
                    .filter(x -> x.getId() == id && x.getOwner().equals(owner))
                    .max(Ticket::compareTo).get();
            if (updateData.isNameSelected())
                ticket.setName(myTicket.getName());
            if (updateData.isPriceSelected())
                ticket.setPrice(myTicket.getPrice());
            if (updateData.isDiscountSelected())
                ticket.setDiscount(myTicket.getDiscount());
            if (updateData.isTypeSelected())
                ticket.setType(myTicket.getType());
            if (updateData.isRefundableSelected())
                ticket.setRefundable(myTicket.getRefundable());
            if (updateData.isXSelected())
                ticket.getCoordinates().setX(myTicket.getX());
            if (updateData.isYSelected())
                ticket.getCoordinates().setY(myTicket.getY());
            if (updateData.isWeightSelected())
                ticket.getPerson().setWeight(myTicket.getWeight());
            if (updateData.isEyeColorSelected())
                ticket.getPerson().setEyeColor(myTicket.getEyeColor());
            if (updateData.isHairColorSelected())
                ticket.getPerson().setHairColor(myTicket.getHairColor());
            if (updateData.isCountrySelected())
                ticket.getPerson().setNationality(myTicket.getNationality());
            LogManager.getLogger().info("Updated ticket with id {}.", id);
            unlock();
        } catch (SQLException e) {
            throw new InvalidTicketException();
        }
    }

    @Override
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

    @Override
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

    @Override
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
        while (!tickets.isEmpty()) {
            ticketsSet.add(tickets.poll());
        }
        tickets = new PriorityQueue<>(ticketsSet);
    }

    @Override
    public void addIfMax(Ticket ticket) {
        try {
            lock();
            if (tickets.stream()
                    .max(Ticket::compareTo)
                    .map(x -> x.compareTo(ticket) < 0).isPresent()) {
                convertAddTicket(ticket);
            }
            unlock();
            LogManager.getLogger().info("Add If Max request has been managed.");
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void removeGreater(Ticket ticket, String owner) {
        try {
            lock();
            ArrayList<ServerTicket> ticketList = tickets
                    .stream()
                    .filter(x -> x.compareTo(ticket) > 0 && x.getOwner().equals(owner))
                    .collect(Collectors.toCollection(ArrayList::new));
            for (ServerTicket serverTicket : ticketList)
                dataBaseManager.getTicketsData().remove(serverTicket.getId(), owner);
            tickets.removeAll(ticketList);
            LogManager.getLogger().info("Removed tickets greater than given.");
            unlock();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Ticket maxByCoordinates() {
        LogManager.getLogger().info("Sent max by coordinates ticket.");
        return tickets
                .stream()
                .max((Comparator<Ticket>) (o1, o2) -> o1.getCoordinates().compareTo(o2.getCoordinates()))
                .get();
    }

    @Override
    public List<Ticket> filterDiscount(double discount) {
        LogManager.getLogger().info("Send discount-filtered collection (discount = {})", discount);
        return tickets
                .stream()
                .filter(x -> x.getDiscount() > (discount))
                .collect(Collectors.toList());
    }

    @Override
    public List<Ticket> getTicketList() {
        return tickets.stream().map(DefaultTicket::new).collect(Collectors.toList());
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
