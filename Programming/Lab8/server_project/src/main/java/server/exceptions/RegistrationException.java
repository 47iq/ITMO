package server.exceptions;

public class RegistrationException extends RuntimeException implements ServerException {

    public RegistrationException() {
        super("ERR_REGISTER");
    }
}
