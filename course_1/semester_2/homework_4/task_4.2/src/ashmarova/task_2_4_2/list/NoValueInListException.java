package ashmarova.task_2_4_2.list;

/**
 * Exception, which is thrown, when somebody tries to go to value,
 * which didn't situated in ashmarova.task_2_4_2.list
 */
public class NoValueInListException extends Exception {
    /**
     * message, which is printed, when exception is caught
     */
    public void message(){
        System.out.println("there is no this value in ashmarova.task_2_4_2.list");
    }
}
