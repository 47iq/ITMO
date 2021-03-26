package client.exceptions;

public class FieldNumberFormatException extends NumberFormatException implements ClientException {

    public FieldNumberFormatException() {
        super("ERR_NUMBER_FORMAT");
    }

    public String accept(ClientExceptionMessenger visitor) {
        return visitor.visit(this);
    }
}
