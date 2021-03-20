package client.exceptions;

public class TerminalException extends RuntimeException implements ClientException {
    public TerminalException() {
        super("Terminal error got while executing the program.");
    }

    public String accept(ClientExceptionMessenger visitor) {
        return visitor.visit(this);
    }
}
