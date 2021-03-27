package server.exceptions;

public class InvalidNameException extends RuntimeException implements CommonException{
    public InvalidNameException() {
        super("ERR_INVALID_NAME");
    }
}
