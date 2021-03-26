package server.exceptions;

public class InvalidIdException extends InvalidTicketFieldException implements ServerException {

    public InvalidIdException() {
        super("ERR_INVALID_ID");
    }

    public String accept(ServerExceptionMessenger visitor) {
        return visitor.visit(this);
    }
}
