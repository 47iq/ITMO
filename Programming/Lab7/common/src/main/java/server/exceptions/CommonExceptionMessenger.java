package server.exceptions;

public abstract class CommonExceptionMessenger implements ExceptionMessenger {

    public String doForXCoordinate() {
        return new InvalidXCoordinateException().getMessage();
    }

    public String doForYCoordinate() {
        return new InvalidYCoordinateException().getMessage();
    }

    public String doForCountry() {
        return new InvalidCountryException().getMessage();
    }

    public String doForDiscount() {
        return new InvalidDiscountException().getMessage();
    }

    public String doForEyes() {
        return new InvalidEyesColorException().getMessage();
    }

    public String doForHair() {
        return new InvalidHairColorException().getMessage();
    }

    public String doForName() {
        return new InvalidNameException().getMessage();
    }

    public String doForPrice() {
        return new InvalidPriceException().getMessage();
    }

    public String doForRefundable() {
        return new InvalidRefundableException().getMessage();
    }

    public String doForType() {
        return new InvalidTypeException().getMessage();
    }

    public String doForWeight() {
        return new InvalidWeightException().getMessage();
    }

    public String doForTicket() {
        return new InvalidTicketException().getMessage();
    }
}
