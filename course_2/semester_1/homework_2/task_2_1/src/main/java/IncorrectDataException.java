/**
 * Is thrown, when data about net is incorrect
 * For example, there is incorrect name of an operation system or incorrect number of computer
 */
public class IncorrectDataException extends Exception {
    private String cause;

    public IncorrectDataException(String cause) {
        this.cause = cause;
    }

    public void message() {
        System.out.print(cause);
    }
}