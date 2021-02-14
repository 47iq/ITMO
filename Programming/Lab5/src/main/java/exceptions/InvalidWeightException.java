package exceptions;

public class InvalidWeightException extends IllegalArgumentException{
    public InvalidWeightException() {
        super("Invalid weight value has been entered. Value must be > 0.");
    }
}
