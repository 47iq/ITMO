package server.exceptions;

public class InvalidIdException extends InvalidTicketFieldException implements ServerException{

    public InvalidIdException()  {
        super("Invalid ID has been entered. Id must be int.");
    }

    public String accept(ServerExceptionMessenger visitor) {
        return visitor.doForID();
    }
}
