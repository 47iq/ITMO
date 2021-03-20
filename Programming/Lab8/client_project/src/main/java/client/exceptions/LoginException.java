package client.exceptions;

public class LoginException extends RuntimeException implements ClientException {
    public LoginException() {
        super("Error. Can't log in.");
    }

    public String accept(ClientExceptionMessenger visitor) {
        return visitor.visit(this);
    }
}
