package ashmarova.task_2_4_2;

/**
 * Exception, which is thrown, when something went wrong
 */
public class UnknownProblemException extends Exception {
    private String cause;

    UnknownProblemException(String cause) {
        this.cause = cause;
    }

    public void message(){
        System.out.print(cause);
    }
}
