package client.exceptions;

public class CommandInputException extends RuntimeException implements ClientException {
    public CommandInputException() {
        super("Error got while getting command.");
    }

    public String accept(ClientExceptionMessenger visitor) {
        return visitor.visit(this);
    }
}
