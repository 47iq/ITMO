package server.exceptions;

public class LoginException extends Exception implements ServerException {

    public LoginException() {
        super("Error. There's currently no user with that login and password. Log in again or register.");
    }

    public String accept(ServerExceptionMessenger visitor) {
        return visitor.visit(this);
    }
}
