public class IncorrectExpressionException extends Throwable {
    private String cause;

    IncorrectExpressionException(String cause){
        this.cause = cause;
    }

    public void message(){
        System.out.print("Couldn't work with expression. " + cause);
    }
}
