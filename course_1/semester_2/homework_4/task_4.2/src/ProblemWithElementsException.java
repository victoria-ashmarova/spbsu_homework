/**
 * Exception, which is thrown, when vslue to add is in table or value to remove is not in table.
 */
public class ProblemWithElementsException extends Exception {
    private String cause;

    ProblemWithElementsException(String cause) {
        this.cause = cause;
    }

    public void message(){
        System.out.print(cause);
    }
}
