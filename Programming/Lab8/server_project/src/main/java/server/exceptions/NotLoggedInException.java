package server.exceptions;

public class NotLoggedInException extends RuntimeException implements ServerException {

    public NotLoggedInException() {
        super("ERR_NOT_LOGGED_IN");
    }
}
