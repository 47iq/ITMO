package main;

import exceptions.InvalidTicketFieldException;
import main.ticket.*;

import java.io.*;

/**
 * Class that is used for reading commands from console
 */

public class ConsoleCommandReader extends AbstractCommandReader {

    /**
     * Constructor for console reader
     * @param commandFactory factory to resolve commands
     * @param manager ticket collection's manager
     * @param ticketFactory object factory
     * @param messenger class that provides local messaging
     */

    public ConsoleCommandReader(CommandFactory commandFactory, CollectionManager manager, ClientObjectFactory ticketFactory, Messenger messenger) {
        reader = new BufferedReader(new InputStreamReader(System.in));
        super.ticketFactory = ticketFactory;
        super.commandFactory = commandFactory;
        super.manager = manager;
        super.messenger = messenger;
    }

    public Ticket readTicket() throws IOException {
        DefaultTicket ticket = ticketFactory.getDefaultTicket();
        DefaultCoordinates coordinates = ticketFactory.getDefaultCoordinates();
        DefaultPerson person = ticketFactory.getDefaultPerson();
        System.out.println(messenger.getNameInputInvitationMessage());
        catchSetName(reader.readLine(), ticket);
        System.out.println(messenger.getXInputInvitationMessage());
        catchSetX(reader.readLine().trim(), coordinates);
        System.out.println(messenger.getYInputInvitationMessage());
        catchSetY(reader.readLine().trim(), coordinates);
        System.out.println(messenger.getDiscountInputInvitationMessage());
        catchSetDiscount(reader.readLine().trim(), ticket);
        System.out.println(messenger.getPriceInputInvitationMessage());
        catchSetPrice(reader.readLine().trim(), ticket);
        System.out.println(messenger.getRefundableInputInvitationMessage());
        catchSetRefundable(reader.readLine().trim(), ticket);
        System.out.println(messenger.getTicketTypeInputInvitationMessage());
        catchSetType(reader.readLine().trim(), ticket);
        System.out.println(messenger.getWeightInputInvitationMessage());
        catchSetWeight(reader.readLine().trim(), person);
        System.out.println(messenger.getEyeColorInputInvitationMessage());
        catchSetEyesColor(reader.readLine().trim(), person);
        System.out.println(messenger.getHairColorInputInvitationMessage());
        catchSetHairColor(reader.readLine().trim(), person);
        System.out.println(messenger.getCountryInputInvitationMessage());
        catchSetCountry(reader.readLine().trim(), person);
        ticket.setCoordinates(coordinates);
        ticket.setPerson(person);
        return ticket;
    }

    private void catchSetName(String str, DefaultTicket ticket) throws IOException {
        try{
            ticket.setNameStr(str);
        } catch (Exception e) {
            repeatInput(e);
            catchSetName(reader.readLine(), ticket);
        }
    }

    private void catchSetX(String str, DefaultCoordinates coordinates) throws IOException {
        try{
            coordinates.setXStr(str);
        } catch (Exception e) {
            repeatInput(e);
            catchSetX(reader.readLine(), coordinates);
        }
    }

    private void catchSetY(String str, DefaultCoordinates coordinates) throws IOException {
        try{
            coordinates.setYStr(str);
        } catch (Exception e) {
            repeatInput(e);
            catchSetY(reader.readLine(), coordinates);
        }
    }

    private void catchSetDiscount(String str, DefaultTicket ticket) throws IOException {
        try{
            ticket.setDiscountStr(str);
        } catch (Exception e) {
            repeatInput(e);
            catchSetDiscount(reader.readLine(), ticket);
        }
    }

    private void catchSetPrice(String str, DefaultTicket ticket) throws IOException {
        try{
            ticket.setPriceStr(str);
        } catch (Exception e) {
            repeatInput(e);
            catchSetPrice(reader.readLine(), ticket);
        }
    }

    private void catchSetType(String str, DefaultTicket ticket) throws IOException {
        try{
            ticket.setTypeStr(str);
        } catch (Exception e) {
            repeatInput(e);
            catchSetType(reader.readLine(), ticket);
        }
    }

    private void catchSetRefundable(String str, DefaultTicket ticket) throws IOException {
        try{
            ticket.setRefundableStr(str);
        } catch (Exception e) {
            repeatInput(e);
            catchSetRefundable(reader.readLine(), ticket);
        }
    }

    private void catchSetCountry(String str, DefaultPerson person) throws IOException {
        try{
            person.setNationalityStr(str);
        } catch (Exception e) {
            repeatInput(e);
            catchSetCountry(reader.readLine(), person);
        }
    }

    private void catchSetWeight(String str, DefaultPerson person) throws IOException {
        try{
            person.setWeightStr(str);
        } catch (Exception e) {
            repeatInput(e);
            catchSetWeight(reader.readLine(), person);
        }
    }

    private void catchSetEyesColor(String str, DefaultPerson person) throws IOException {
        try{
            person.setEyeColorStr(str);
        } catch (Exception e) {
            repeatInput(e);
            catchSetEyesColor(reader.readLine(), person);
        }
    }

    private void catchSetHairColor(String str, DefaultPerson person) throws IOException {
        try{
            person.setHairColorStr(str);
        } catch (Exception e) {
            repeatInput(e);
            catchSetHairColor(reader.readLine(), person);
        }
    }

    /**
     * Displays input error and offers to repeat the input of the value
     */

    private void repeatInput(Exception e) {
        if(e instanceof InvalidTicketFieldException)
            System.err.print(e.getMessage());
        else if(e instanceof NumberFormatException)
            System.err.print("Number format error got.");
        else
            System.err.print("Error.");
        System.err.println(" Try to enter the field again:");
    }

    protected boolean readyForInput() throws IOException {
        return true;
    }
}