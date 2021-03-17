package server.exceptions;

public class NotLoggedInException extends RuntimeException implements ServerException {

    public NotLoggedInException() {
        super("Error. User must log in to execute commands.");
    }

    public String accept(ServerExceptionMessenger visitor) {
        return visitor.visit(this);
    }
}
