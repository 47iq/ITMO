package client.exceptions;

public class ConnectionException extends RuntimeException implements ClientException {
    public ConnectionException() {
        super("Error. Server is temporary unavailable.");
    }

    public String accept(ClientExceptionMessenger visitor) {
        return visitor.doForConnection();
    }
}
