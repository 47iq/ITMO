package server.exceptions;

public class InvalidEyesColorException extends InvalidTicketFieldException implements CommonException {

    public InvalidEyesColorException() {
        super("ERR_INVALID_EYES");
    }

    public String accept(ExceptionMessenger visitor) {
        return visitor.visit(this);
    }
}
