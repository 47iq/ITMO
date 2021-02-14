package exceptions;

import java.util.NoSuchElementException;

public class TicketNotFoundException extends NoSuchElementException{
    public TicketNotFoundException() {
        super("There is no ticket with such id");
    }
}
