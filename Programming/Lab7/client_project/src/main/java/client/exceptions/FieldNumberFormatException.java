package client.exceptions;

public class FieldNumberFormatException extends NumberFormatException implements ClientException{

    public FieldNumberFormatException() {
        super("Number format exception got.");
    }

    public String accept(ClientExceptionMessenger visitor) {
        return visitor.visit(this);
    }
}
