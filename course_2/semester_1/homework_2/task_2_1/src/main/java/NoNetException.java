/**
 * Created by Виктория on 20.12.2017.
 */
public class NoNetException extends Exception {
    private String cause;

    public NoNetException(String cause) {
        this.cause = cause;
    }

    public void message() {
        System.out.print(cause);
    }
}
