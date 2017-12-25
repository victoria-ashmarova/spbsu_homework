package ashmarova.task_2_4_2;

/**
 * Exception, which is thrown, when value for addition is in table
 */
public class ValueIsInTableException extends Exception{
    public void message(){
        System.out.print("Couldn't add value to table. Value is in table yet");
    }
}
