package exceptions;

public class UnknownCommandException extends IllegalArgumentException{
    public UnknownCommandException() {
        super("Unknown command has been entered");
    }
}
