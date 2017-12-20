/**
 * Created by Виктория on 20.12.2017.
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