/**
 * Exception, which is thrown, when there is no ability to get value of input exception.
 */
public class IncorrectExpressionException extends Throwable {
    private String cause;

    IncorrectExpressionException(String cause){
        this.cause = cause;
    }

    /**
     * prints, why exception is incorrect
     */
    public void message(){
        System.out.print("Couldn't work with expression. " + cause);
    }
}
