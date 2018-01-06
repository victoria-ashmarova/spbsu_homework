package networkComponent;

/**
 * Is thrown, when there are problems with connection between players
 */
public class DisableConnectionException extends Exception {
    private String cause;

    public DisableConnectionException(String cause) {
        this.cause = cause;
    }

    public void message() {
        System.out.println(cause);
    }
}
