package exceptions;

public class EmptyTicketsException extends RuntimeException{
    public EmptyTicketsException() {
        super("Error. The tickets collection is empty");
    }
}
