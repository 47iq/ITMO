package server.exceptions;

public class InvalidPriceException extends InvalidTicketFieldException implements CommonException {

    public InvalidPriceException() {
        super("Invalid price has been entered. Price must be int > 0.");
    }

    public String accept(ExceptionMessenger visitor) {
        return visitor.doForPrice();
    }
}
