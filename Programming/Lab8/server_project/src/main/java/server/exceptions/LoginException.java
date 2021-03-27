package server.exceptions;

public class LoginException extends RuntimeException implements ServerException {

    public LoginException() {
        super("ERR_AUTH");
    }
}
