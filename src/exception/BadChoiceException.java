package exception;

public class BadChoiceException extends Exception {
    public BadChoiceException (String errorMessage){
        super(errorMessage);
    }
}