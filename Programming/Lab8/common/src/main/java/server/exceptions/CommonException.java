package server.exceptions;

public interface CommonException {
    String accept(ExceptionMessenger visitor);
}
