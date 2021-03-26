package client.exceptions;

public class CommunicationException extends RuntimeException implements ClientException {
    public CommunicationException() {
        super("ERR_COMMUNICATION");
    }

    public String accept(ClientExceptionMessenger visitor) {
        return visitor.visit(this);
    }
}
