package server.exceptions;

public class UserExistsException extends RuntimeException implements ServerException {

    public UserExistsException() {
        super("Error. User with this login already exists. Pick another login.");
    }

    public String accept(ServerExceptionMessenger visitor) {
        return visitor.visit(this);
    }
}
