package server.exceptions;

public interface ServerException {
    String accept(ServerExceptionMessenger visitor);
}
