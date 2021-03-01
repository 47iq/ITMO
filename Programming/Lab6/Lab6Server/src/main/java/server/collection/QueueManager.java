package server.collection;

import server.ObjectFactory;
import server.filework.TicketReader;
import server.filework.TicketWriter;
import common.Ticket;
import org.apache.logging.log4j.LogManager;
import server.ticket.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
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

    private final TicketWriter ticketWriter;

    private final TicketReader ticketReader;

    private final ObjectFactory ticketFactory;

    public QueueManager(TicketReader ticketsGetter, TicketWriter ticketSaver, ObjectFactory ticketFactory){
        this.ticketReader = ticketsGetter;
        this.ticketWriter = ticketSaver;
        tickets = new PriorityQueue<>();
        creationDate = ZonedDateTime.now(ZoneId.of("Europe/Moscow"));
        this.ticketFactory = ticketFactory;
        createSet();
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
        ServerTicket serverTicket = ticketFactory.convertTicket(ticket);
        tickets.add(serverTicket);
        LogManager.getLogger().info("Added ticket {} ", serverTicket.toString());
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

    public void updateId(int id, Ticket myTicket) {
        tickets = Stream
                .concat(tickets
                                .stream()
                                .filter(x -> x.getId() == id)
                                .map(x -> x = ServerTicket.copyFromTicket(x, myTicket)),
                        tickets
                                .stream()
                                .filter(x -> x.getId() != id))
                .collect(Collectors.toCollection(PriorityQueue::new));
        LogManager.getLogger().info("Updated ticket with id {}.", id);
    }

    public void removeById(int id) {
        tickets = tickets.stream().
                filter(x -> x.getId() != id)
                .collect(Collectors.toCollection(PriorityQueue::new));
        LogManager.getLogger().info("Removed ticket with id {}.", id);
    }


    public void clear() {
        tickets = new PriorityQueue<>();
        LogManager.getLogger().info("Cleared the collection.");
    }


    public void removeFirst() {
        try {
            ServerTicket ticket = tickets.poll();
            AbstractQueueManager.removeID(ticket.getId());
            LogManager.getLogger().info("Cleared the collection.");
        } catch (Exception e) {
            throw new RuntimeException("Error. Ticket queue is already empty.");
        }
    }

    /**
     * Sorts {@link #tickets} using {@link TreeSet< ServerDefaultTicket >}
     */

    public void sort() {
        SortedSet<ServerTicket> ticketsSet = new TreeSet<>();
        while (!tickets.isEmpty()){
            ticketsSet.add(tickets.poll());
        }
        tickets = new PriorityQueue<>(ticketsSet);
    }


    public void addIfMax(Ticket ticket) {
        if (tickets.stream()
                .max(Ticket::compareTo)
                .map(x -> x.compareTo(ticket) < 0).isPresent()) {
            convertAddTicket(ticket);
        }
        LogManager.getLogger().info("Add If Max request has been managed.");
    }

    public void removeGreater(Ticket ticket) {
        tickets = tickets
                .stream()
                .filter(x->x.compareTo(ticket) < 0)
                .collect(Collectors.toCollection(PriorityQueue::new));
        LogManager.getLogger().info("Removed tickets greater than given.");
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

    public void saveData() {
        ticketWriter.saveTickets(tickets);
    }
}
