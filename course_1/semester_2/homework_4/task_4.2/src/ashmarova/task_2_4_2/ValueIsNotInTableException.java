package ashmarova.task_2_4_2;

/**
 * Exception, which is thrown, when value for addition is not in table
 */
public class ValueIsNotInTableException extends Throwable {
    public void message(){
        System.out.print("Couldn't remove value from. Value is not in table");
    }
}
