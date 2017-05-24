/**
 * Exception, which is thrown, when there ara tryings to initialize array index
 * with the negative integer value
 * or with incorrect data
 */
public class IncorrectArraySizeException extends NegativeArraySizeException{
    private String cause;

    IncorrectArraySizeException(String cause){
        this.cause = cause;
    }

    public void message(){
        System.out.println(cause);
    }
}
