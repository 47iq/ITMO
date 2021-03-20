package server.exceptions;

public abstract class CommonExceptionMessenger implements ExceptionMessenger {
    public String visit(InvalidXCoordinateException e) {
        return e.getMessage();
    }

    public String visit(InvalidYCoordinateException e) {
        return e.getMessage();
    }

    public String visit(InvalidCountryException e) {
        return e.getMessage();
    }

    public String visit(InvalidDiscountException e) {
        return e.getMessage();
    }

    public String visit(InvalidEyesColorException e) {
        return e.getMessage();
    }

    public String visit(InvalidHairColorException e) {
        return e.getMessage();
    }

    public String visit(InvalidNameException e) {
        return e.getMessage();
    }

    public String visit(InvalidPriceException e) {
        return e.getMessage();
    }

    public String visit(InvalidRefundableException e) {
        return e.getMessage();
    }

    public String visit(InvalidTypeException e) {
        return e.getMessage();
    }

    public String visit(InvalidWeightException e) {
        return e.getMessage();
    }

    public String visit(InvalidTicketException e) {
        return e.getMessage();
    }

    public String visit(InvalidTicketFieldException e) {
        return e.getMessage();
    }
}
