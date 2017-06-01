package ashmarova.task_2_4_1;

/**
 * Exception, which is thrown, when somebody tries to go to value,
 * which didn't situated in list
 */
public class NoValueInListException extends Exception {
    /**
     * message, which is printed, when exception is caught
     */
    public void message(){
        System.out.println("there is no this value in list");
    }
}
