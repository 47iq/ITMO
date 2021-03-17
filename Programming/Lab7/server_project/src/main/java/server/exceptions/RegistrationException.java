package server.exceptions;

public class RegistrationException extends RuntimeException implements ServerException{

    public RegistrationException() {
        super("Error got while user registration. Try again.");
    }

    public String accept(ServerExceptionMessenger visitor) {
        return visitor.visit(this);
    }
}
