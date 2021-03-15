package client.exceptions;

public interface ClientException {
    String accept(ClientExceptionMessenger visitor);
}
