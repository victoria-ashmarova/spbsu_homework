package ashmarova.task_2_4_2.list;

/**
 * Exception, which is thrown, when the value of parameter of place is incorrect
 * Value of the parameter mustn't be negative or more then size of ashmarova.task_2_4_2.list
 */
public class IncorrectIndexException extends Exception {
    /**
     * message, which is printed, when exception is caught
     */
    public void message(){
        System.out.println("There is no place with this number in ashmarova.task_2_4_2.list.");
    }
}
