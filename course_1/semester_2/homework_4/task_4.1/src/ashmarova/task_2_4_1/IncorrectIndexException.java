package ashmarova.task_2_4_1;

/**
 * Exception, which is thrown, when the value of parameter of place is incorrect
 * Value of the parameter mustn't be negative or more then size of list
 */
public class IncorrectIndexException extends Exception {
    /**
     * message, which is printed, when exception is caught
     */
    public void message(){
        System.out.println("There is no index with this number in list.");
    }
}
