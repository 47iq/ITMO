package client.exceptions;

public class LoginException extends RuntimeException implements ClientException {
    public LoginException() {
        super("ERR_AUTH");
    }
}
