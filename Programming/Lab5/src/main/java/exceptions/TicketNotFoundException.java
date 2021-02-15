package exceptions;

import java.util.NoSuchElementException;

public class TicketNotFoundException extends NoSuchElementException{
    public TicketNotFoundException() {
        super("Error. There is no ticket with such id.");
    }
}
