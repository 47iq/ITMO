package main;

import main.ticket.*;

import java.io.*;

/**
 * Class that is used for reading commands(scripts) from file
 */

public class FileCommandReader extends AbstractCommandReader {

    /**
     * Constructor for file command reader
     * @param commandFactory factory to resolve commands
     * @param manager ticket collection manager
     * @param file file we want to read from
     * @param ticketFactory object factory
     * @throws FileNotFoundException if file we want to read from doesn't exist
     */

    public FileCommandReader(CommandFactory commandFactory, CollectionManager manager, File file, ClientObjectFactory ticketFactory) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(file));
        super.ticketFactory = ticketFactory;
        super.commandFactory = commandFactory;
        super.manager = manager;
    }

    public Ticket readTicket() throws IOException {
        DefaultTicket ticket = ticketFactory.getDefaultTicket();
        DefaultCoordinates coordinates = ticketFactory.getDefaultCoordinates();
        DefaultPerson person = ticketFactory.getDefaultPerson();
        ticket.setNameStr(reader.readLine());
        coordinates.setXStr(reader.readLine().trim());
        coordinates.setYStr(reader.readLine().trim());
        ticket.setDiscountStr(reader.readLine().trim());
        ticket.setPriceStr(reader.readLine().trim());
        ticket.setRefundableStr(reader.readLine().trim());
        ticket.setTypeStr(reader.readLine().trim());
        person.setWeightStr(reader.readLine().trim());
        person.setEyeColorStr(reader.readLine().trim());
        person.setHairColorStr(reader.readLine().trim());
        person.setNationalityStr(reader.readLine().trim());
        ticket.setCoordinates(coordinates);
        ticket.setPerson(person);
        return ticket;
    }

    protected boolean readyForInput() throws IOException {
        return reader.ready();
    }
}
