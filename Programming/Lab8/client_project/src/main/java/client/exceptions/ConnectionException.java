package client.exceptions;

public class ConnectionException extends RuntimeException implements ClientException {
    public ConnectionException() {
        super("ERR_CONNECTION");
    }

    public String accept(ClientExceptionMessenger visitor) {
        return visitor.visit(this);
    }
}
