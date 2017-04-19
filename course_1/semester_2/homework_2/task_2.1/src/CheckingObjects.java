/**
 * Class contains methods, which check if an object number,
 * operations of first type(composition ('*') and quotient('/')) or operations of the second type(sum('+') and difference('-'))
 */
public class CheckingObjects {
    /** checks if object in array is operation*/
    public boolean isOperation(Object current){
        try{
            boolean isOperation = current.equals('+') || current.equals('-') || current.equals('*')|| current.equals('/');
            return isOperation;
        } catch (ClassCastException e){
            return false;
        }
    }

    /**check if object in array integer value*/
    public boolean isNumber(Object current){
        try{
            int value = (int) current;
            return true;
        } catch(ClassCastException e){
            return false;
        }
    }

    /**
     * checks if object is composition('*') or quotient('/')
     * @param operation is character(or not) value to check
     * @return  true if operation is composition('*') or quotient('/')
     * @exception ClassCastException
     */
    public boolean isFirstOperation(Object operation) throws  ClassCastException{
        return (char)operation == '*' || (char)operation == '/';
    }

    /**
     * checks if object is sum('+') or difference('-')
     * @param operation is character(or not) value to check
     * @return  true if operation is sum('+') or difference('-')
     * @exception ClassCastException
     */
    public boolean isSecondOperation(Object operation) throws  ClassCastException{
        return (char)operation == '+' || (char)operation == '-';
    }
}
