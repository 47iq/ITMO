package client.exceptions;

public class LocalMessengerException extends RuntimeException implements ClientException {
    public LocalMessengerException() {
        super("Warning. Can't get local message class. English messenger will be used.");
    }

    public String accept(ClientExceptionMessenger visitor) {
        return visitor.visit(this);
    }
}
