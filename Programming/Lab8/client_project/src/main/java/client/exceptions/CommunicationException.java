package client.exceptions;

public class CommunicationException extends RuntimeException implements ClientException {
    public CommunicationException() {
        super("ERR_COMMUNICATION");
    }
}
