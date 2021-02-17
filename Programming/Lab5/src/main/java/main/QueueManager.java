package main;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

import exceptions.EmptyTicketsException;
import exceptions.TicketNotFoundException;

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

    private final TicketsParser getter;

    private final TicketMessenger ticketMessenger;

    public QueueManager(TicketsParser ticketsGetter, TicketWriter ticketSaver, TicketMessenger ticketMessenger){
        this.getter = ticketsGetter;
        this.saver = ticketSaver;
        tickets = new PriorityQueue<>();
        creationDate = ZonedDateTime.now(ZoneId.of("Europe/Moscow"));
        this.ticketMessenger = ticketMessenger;
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
                    System.err.println("Error got adding ticket (id " + ticket.getId() + "). Ticket won't be added.");
                }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error while parsing data into the collection");
        }
    }



    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public void displayInfo() {
         System.out.println("Collection type: " + tickets.getClass().toString() +
                 "\nNumber of tickets: " + tickets.size() + "\nCreation time: " + creationDate);
    }

    public void displayElements() {
         for (Ticket ticket: tickets) {
             System.out.println(ticketMessenger.getTicketMessage(ticket));
         }
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

    public void printRefundable() {
        List<Ticket> ticketsList = getTicketsList();
        ticketsList.sort(getRefundableComparator());
        for(int i = ticketsList.size() - 1; i >= 0; i--) {
            System.out.println(ticketsList.get(i).getRefundable());
        }
    }


    public void updateId(int id, Ticket myTicket) {
        boolean found = false;
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
     * Sorts {@link #tickets} using {@link TreeSet< DefaultTicket >}
     */

    public void sort() {
        SortedSet<Ticket> ticketsSet = new TreeSet<>();
        while (!tickets.isEmpty()){
            ticketsSet.add(tickets.poll());
        }
        tickets = new PriorityQueue<>(ticketsSet);
    }

    /**
     * Method that get maximal ticket from collection
     * @return maximal ticket
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
        if(!tickets.isEmpty() && ticket.compareTo(maxTicket()) > 0)
            addTicket(ticket);
            //System.out.println("Ticket has been successfully added");
    }

    public void removeGreater(Ticket ticket) {
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

    public void maxByCoordinates() {
        Coordinates maxCoordinates = ObjectFactory.getLeastCoordinates();
        Ticket maxTicket = null;
        for(Ticket ticket: tickets) {
            if(maxCoordinates.compareTo(ticket.getCoordinates()) <= 0) {
                maxCoordinates = ticket.getCoordinates();
                maxTicket = ticket;
            }
        }
        if(maxTicket != null)
            System.out.println(ticketMessenger.getTicketMessage(maxTicket));
        else
            throw new EmptyTicketsException();
    }

    public void filterDiscount(double discount) {
        for(Ticket ticket: tickets)
            if(discount < ticket.getDiscount())
                System.out.println(ticketMessenger.getTicketMessage(ticket));
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
