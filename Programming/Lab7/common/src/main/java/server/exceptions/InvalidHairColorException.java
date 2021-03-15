package server.exceptions;

public class InvalidHairColorException extends InvalidTicketFieldException implements CommonException {

    public InvalidHairColorException(){
        super("Invalid color has been entered. HairColor should be one of the colors, which are present in the color list " +
                "and color mustn't be null.");
    }

    public String accept(ExceptionMessenger visitor) {
        return visitor.doForHair();
    }
}
