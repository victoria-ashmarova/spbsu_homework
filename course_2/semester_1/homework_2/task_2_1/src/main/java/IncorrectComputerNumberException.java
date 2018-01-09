/**
 *
 */
public class IncorrectComputerNumberException extends Exception {
    private String cause;

    public IncorrectComputerNumberException(String cause) {
        this.cause = cause;
    }

    public void message() {
        System.out.print(cause);
    }
}
