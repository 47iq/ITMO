package exceptions;

public class InvalidRequestException extends RuntimeException{
    public InvalidRequestException() {
        super("Error. Invalid request.");
    }
}
