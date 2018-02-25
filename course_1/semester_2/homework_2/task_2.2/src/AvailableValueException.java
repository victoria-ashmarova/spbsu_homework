/**
 * Is thrown when there is no searched value in list including situation, when list is empty.
 */
public class AvailableValueException extends Exception {
    private String cause;
    AvailableValueException(String cause){
        this.cause = cause;
    }

    /**
     * prints cause of throwing exception.
     */
    public void message(){
        System.out.println("Value is not available. " + cause);
    }
}
