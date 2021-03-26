package server.exceptions;

public interface ExceptionMessenger {
    String visit(InvalidXCoordinateException e);

    String visit(InvalidYCoordinateException e);

    String visit(InvalidCountryException e);

    String visit(InvalidDiscountException e);

    String visit(InvalidEyesColorException e);

    String visit(InvalidHairColorException e);

    String visit(InvalidNameException e);

    String visit(InvalidPriceException e);

    String visit(InvalidRefundableException e);

    String visit(InvalidTypeException e);

    String visit(InvalidWeightException e);

    String visit(InvalidTicketException e);

    String visit(InvalidTicketFieldException e);
}
