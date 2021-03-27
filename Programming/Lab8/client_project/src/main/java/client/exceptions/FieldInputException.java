package client.exceptions;

public class FieldInputException extends RuntimeException implements ClientException {
    public FieldInputException() {
        super(" Try to enter the field again:");
    }
}
