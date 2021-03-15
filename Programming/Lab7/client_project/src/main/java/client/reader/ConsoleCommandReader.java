package client.reader;

import client.Main;
import client.ObjectFactory;
import client.connection.ConnectionManager;
import client.exceptions.FieldInputException;
import client.exceptions.FieldNumberFormatException;
import client.messages.Messenger;
import common.*;
import server.exceptions.CommonException;
import server.exceptions.InvalidTicketFieldException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class that is used for reading commands from console
 */

public class ConsoleCommandReader extends AbstractCommandReader {

    /**
     * Constructor for console reader
     * @param commandFactory factory to resolve commands
     * @param ticketFactory object factory
     * @param messenger class that provides local messaging
     */

    public ConsoleCommandReader(ConnectionManager commandFactory, ObjectFactory ticketFactory, Messenger messenger) {
        reader = new BufferedReader(new InputStreamReader(System.in));
        super.ticketFactory = ticketFactory;
        super.commandFactory = commandFactory;
        super.messenger = messenger;
    }

    public Ticket readTicket() throws IOException {
        DefaultTicket ticket = ticketFactory.getDefaultTicket();
        DefaultCoordinates coordinates = ticketFactory.getDefaultCoordinates();
        DefaultPerson person = ticketFactory.getDefaultPerson();
        Main.getOut().println(messenger.getNameInputInvitationMessage());
        catchSetName(reader.readLine(), ticket);
        Main.getOut().println(messenger.getXInputInvitationMessage());
        catchSetX(reader.readLine().trim(), coordinates);
        Main.getOut().println(messenger.getYInputInvitationMessage());
        catchSetY(reader.readLine().trim(), coordinates);
        Main.getOut().println(messenger.getDiscountInputInvitationMessage());
        catchSetDiscount(reader.readLine().trim(), ticket);
        Main.getOut().println(messenger.getPriceInputInvitationMessage());
        catchSetPrice(reader.readLine().trim(), ticket);
        Main.getOut().println(messenger.getRefundableInputInvitationMessage());
        catchSetRefundable(reader.readLine().trim(), ticket);
        Main.getOut().println(messenger.getTicketTypeInputInvitationMessage());
        catchSetType(reader.readLine().trim(), ticket);
        Main.getOut().println(messenger.getWeightInputInvitationMessage());
        catchSetWeight(reader.readLine().trim(), person);
        Main.getOut().println(messenger.getEyeColorInputInvitationMessage());
        catchSetEyesColor(reader.readLine().trim(), person);
        Main.getOut().println(messenger.getHairColorInputInvitationMessage());
        catchSetHairColor(reader.readLine().trim(), person);
        Main.getOut().println(messenger.getCountryInputInvitationMessage());
        catchSetCountry(reader.readLine().trim(), person);
        ticket.setCoordinates(coordinates);
        ticket.setPerson(person);
        return ticket;
    }

    public User readUser() throws IOException {
        Main.getOut().println(messenger.getLoginInputMessage());
        String login = reader.readLine();
        Main.getOut().println(messenger.getPasswordInputMessage());
        String password = reader.readLine();
        return ticketFactory.getUser(login, password);
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
            Main.getErr().print(((CommonException) e).accept(Main.getExceptionMessenger()));
        else
            Main.getErr().print(new FieldNumberFormatException().accept(Main.getExceptionMessenger()));
        Main.getErr().println(new FieldInputException().accept(Main.getExceptionMessenger()));
    }

    protected boolean readyForInput() throws IOException {
        return true;
    }

    protected void printInputInvitation() {
        Main.getOut().print("\n>> ");
    }
}