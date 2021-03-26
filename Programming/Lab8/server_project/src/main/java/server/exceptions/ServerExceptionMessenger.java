package server.exceptions;

public interface ServerExceptionMessenger extends ExceptionMessenger {
    String visit(UnknownCommandException e);

    String visit(CommandExecutionException e);

    String visit(BrokenPackageException e);

    String visit(UnknownTypeException e);

    String visit(UnknownException e);

    String visit(InvalidIdException e);

    String visit(LoginException e);

    String visit(RegistrationException e);

    String visit(NotLoggedInException e);

    String visit(UserExistsException e);
}
