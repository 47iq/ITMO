package exceptions;

import java.util.NoSuchElementException;

public class TicketNotFoundException extends NoSuchElementException{
    public TicketNotFoundException() {
        super("Error. There is no manager.ticket with such id.");
    }
}
