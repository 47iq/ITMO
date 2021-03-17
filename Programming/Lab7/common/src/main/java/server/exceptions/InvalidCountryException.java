package server.exceptions;

public class InvalidCountryException extends InvalidTicketFieldException implements CommonException{
    public InvalidCountryException() {
        super("Invalid country has been entered. Country should be one of the countries, which are present in the country list " +
                "and country mustn't be null.");
    }

    public String accept(ExceptionMessenger visitor) {
        return visitor.visit(this);
    }
}
