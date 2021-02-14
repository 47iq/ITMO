package exceptions;

public class InvalidPriceException extends IllegalArgumentException{
    public InvalidPriceException() {
        super("Invalid price has been entered. Price must be int > 0");
    }
}
