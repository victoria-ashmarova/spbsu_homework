package ashmarova.task_2_4_2.list;

/**
 * Exception, which is thrown in unique ashmarova.task_2_4_2.list,
 * when somebody wants to add element, which is in ashmarova.task_2_4_2.list
 */
public class ValueIsInListException extends Exception{
    /**
     * message, which is printed, when exception is caught
     */
    public void message(){
        System.out.println("there is this value in ashmarova.task_2_4_2.list yet");
    }
}
