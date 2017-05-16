/**
 * Exception, which is thrown in unique list,
 * when somebody wants to add element, which is in list
 */
public class ValueIsInListException extends Exception{
    /**
     * message, which is printed, when exception is caught
     */
    public void message(){
        System.out.println("there is this value in list yet");
    }
}
