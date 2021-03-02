package exceptions;

public class InvalidEyesColorException extends InvalidTicketFieldException{
    public InvalidEyesColorException(){
        super("Invalid color has been entered. EyesColor should be one of the colors, which are present in the color list " +
                    "and color mustn't be null.");
    }
}
