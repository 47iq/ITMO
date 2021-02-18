package main;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

import exceptions.EmptyTicketsException;
import exceptions.TicketNotFoundException;
import main.ticket.Coordinates;
import main.ticket.ServerDefaultTicket;
import main.ticket.Ticket;

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

    private final TicketWriter saver;

    private final TicketReader getter;

    private final ServerObjectFactory ticketFactory;

    public QueueManager(TicketReader ticketsGetter, TicketWriter ticketSaver, ServerObjectFactory ticketFactory){
        this.getter = ticketsGetter;
        this.saver = ticketSaver;
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
            Collection<Ticket> tickets = getter.getTickets();
            for(Ticket ticket: tickets)
                try {
                    addTicket(ticket);
                } catch (Exception e) {
                    System.err.println("Error got adding manager.ticket (id " + ticket.getId() + "). Ticket won't be added.");
                }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error while parsing data into the collection");
        }
    }

    private void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public void convertAddTicket(Ticket ticket) {
        tickets.add(ticketFactory.convertTicket(ticket));
    }

    public List<String> displayInfo() {
        List<String> info =  new ArrayList<>();
        info.add(tickets.getClass().toString());
        info.add(String.valueOf(tickets.size()));
        info.add(creationDate.toString());
        return info;
    }

    public List<Ticket>  displayElements() {
        return getTicketsList();
    }

    /**
     * Gets {@link List<Ticket>} containing all of the {@link #tickets}
     * @return ticketsList {@link List<Ticket>}
     */

    private List<Ticket> getTicketsList() {
        List<Ticket> ticketsList = new ArrayList<>();
        PriorityQueue<Ticket> ticketsBackup = new PriorityQueue<>();
        int sz = tickets.size();
        for(int i = 0; i < sz; i++){
            Ticket ticket = tickets.poll();
            ticketsList.add(ticket);
            ticketsBackup.add(ticket);
        }
        tickets = ticketsBackup;
        return ticketsList;
    }

    public List<Boolean> getRefundableList() {
        List<Ticket> ticketsList = getTicketsList();
        ticketsList.sort(getRefundableComparator());
        List<Boolean> refundableList = new ArrayList<>();
        for(int i = ticketsList.size() - 1; i >= 0; i--) {
            refundableList.add(ticketsList.get(i).getRefundable());
        }
        return refundableList;
    }


    public void updateId(int id, Ticket myTicket) {
        boolean found = false;
        myTicket = ticketFactory.convertTicket(myTicket);
        for(Ticket ticket: tickets) {
            if(ticket.getId() == id) {
                tickets.remove(ticket);
                tickets.add(myTicket);
                found = true;
                break;
            }
        }
        if(!found)
            throw new TicketNotFoundException();
    }


    public boolean elementExists(int id) {
        boolean found = false;
        for(Ticket ticket: tickets) {
            if(ticket.getId() == id) {
                found = true;
                break;
            }
        }
        return found;
    }


    public void removeById(int id) {
        boolean found = false;
        for(Ticket ticket: tickets) {
            if(ticket.getId() == id) {
                tickets.remove(ticket);
                found = true;
                break;
            }
        }
        if(!found)
            throw new TicketNotFoundException();
    }


    public void clear() {
        tickets = new PriorityQueue<>();
    }


    public void removeFirst() {
        tickets.remove();
    }

    /**
     * Sorts {@link #tickets} using {@link TreeSet<  ServerDefaultTicket  >}
     */

    public void sort() {
        SortedSet<Ticket> ticketsSet = new TreeSet<>();
        while (!tickets.isEmpty()){
            ticketsSet.add(tickets.poll());
        }
        tickets = new PriorityQueue<>(ticketsSet);
    }

    /**
     * Method that get maximal manager.ticket from collection
     * @return maximal manager.ticket
     */

    private Ticket maxTicket() {
        Ticket ticketMax = tickets.peek();
        for(Ticket ticket: tickets) {
            if(ticket.compareTo(ticketMax) > 0)
                ticketMax = ticket;
        }
        return ticketMax;
    }

    public void addIfMax(Ticket ticket) {
        ticket = ticketFactory.convertTicket(ticket);
        if(!tickets.isEmpty() && ticket.compareTo(maxTicket()) > 0)
            convertAddTicket(ticket);
            //System.out.println("Ticket has been successfully added");
    }

    public void removeGreater(Ticket ticket) {
        ticket = ticketFactory.convertTicket(ticket);
        List<Ticket> ticketList = getTicketsList();
        Collections.sort(ticketList);
        for(int i = ticketList.size() - 1; i >= 0; i--) {
            Ticket ticketI = ticketList.get(i);
            if(ticketI.compareTo(ticket) > 0)
                tickets.remove(ticketI);
            else
                break;
        }
    }

    public Ticket  maxByCoordinates() {
        Coordinates maxCoordinates = ticketFactory.getLeastCoordinates();
        Ticket maxTicket = null;
        for(Ticket ticket: tickets) {
            if(maxCoordinates.compareTo(ticket.getCoordinates()) <= 0) {
                maxCoordinates = ticket.getCoordinates();
                maxTicket = ticket;
            }
        }
        if(maxTicket != null)
            return maxTicket;
        else
            throw new EmptyTicketsException();
    }

    public List<Ticket> filterDiscount(double discount) {
        List<Ticket> ticketList = new ArrayList<>();
        for(Ticket ticket: tickets)
            if(discount < ticket.getDiscount())
                ticketList.add(ticket);
        return ticketList;
    }

    public void saveData() {
        saver.saveTickets(tickets);
    }

    /**
     * Method that gets comparator for refundable reverse sort
     * @return comparator for sort
     */

    private Comparator<Ticket> getRefundableComparator() {
        return new Comparator<Ticket>() {
            public int compare(Ticket t1, Ticket t2) {
                if(t2.getRefundable() == null)
                    return 1;
                if(t1.getRefundable() == null || !t1.getRefundable()&&t2.getRefundable() )
                    return -1;
                else if(t1.getRefundable()&&!t2.getRefundable())
                    return 1;
                else
                    return 0;
            }
        };
    }
}
