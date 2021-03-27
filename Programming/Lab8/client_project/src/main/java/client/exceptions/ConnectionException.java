package client.exceptions;

public class ConnectionException extends RuntimeException implements ClientException {
    public ConnectionException() {
        super("ERR_CONNECTION");
    }
}
