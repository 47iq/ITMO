package exceptions;

public class UnknownCommandException extends IllegalArgumentException{
    public UnknownCommandException() {
        super("Error. Unknown command has been entered.");
    }
}
