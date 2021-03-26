package server.exceptions;

public class RegistrationException extends RuntimeException implements ServerException {

    public RegistrationException() {
        super("ERR_REGISTER");
    }

    public String accept(ServerExceptionMessenger visitor) {
        return visitor.visit(this);
    }
}
