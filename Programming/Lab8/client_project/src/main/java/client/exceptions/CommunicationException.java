package client.exceptions;

public class CommunicationException extends RuntimeException implements ClientException {
    public CommunicationException() {
        super("Error got while communicating to the server.");
    }

    public String accept(ClientExceptionMessenger visitor) {
        return visitor.visit(this);
    }
}
