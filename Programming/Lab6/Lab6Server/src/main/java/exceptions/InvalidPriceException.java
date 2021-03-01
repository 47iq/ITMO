package exceptions;

public class InvalidPriceException extends InvalidTicketFieldException{
    public InvalidPriceException() {
        super("Invalid price has been entered. Price must be int > 0.");
    }
}
