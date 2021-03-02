package exceptions;

public class InvalidCountryException extends InvalidTicketFieldException{
    public InvalidCountryException() {
        super("Invalid country has been entered. Country should be one of the countries, which are present in the country list " +
                "and country mustn't be null.");
    }
}
