package exceptions;

public class InvalidIdException extends InvalidTicketFieldException{
    public InvalidIdException()  {
        super("Invalid ID has been entered. Id must be int > 0.");
    }
}
