package client.exceptions;

public class NotEnoughAgrsException extends RuntimeException implements ClientException {

    public NotEnoughAgrsException() {
        super("Error. Not enough arguments have been entered. Arguments must be: IP address and port of the server.");
    }

    public String accept(ClientExceptionMessenger visitor) {
        return visitor.visit(this);
    }
}
