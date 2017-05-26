/**
 * Is thrown when index of index to search is less then 0 or more then size of list.
 */
public class IncorrectIndexException extends Exception {
    private String cause;
    IncorrectIndexException(String cause){
        this.cause = cause;
    }

    /**
     * prints cause of throwing exception.
     */
    public void message(){
        System.out.println("Couldn't refer to element with this index. " + cause);
    }
}
