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
     * checks if object is ('*') or ('/')
     * @param operation is character(or not) value to check
     * @return  true if operation is ('*') or ('/')
     * @exception ClassCastException
     */
    public boolean isFirstOperation(Object operation) throws  ClassCastException{
        return (char)operation == '*' || (char)operation == '/';
    }

    /**
     * checks if object is ('+') or ('-')
     * @param operation is character(or not) value to check
     * @return  true if operation is ('+') or ('-')
     * @exception ClassCastException
     */
    public boolean isSecondOperation(Object operation) throws  ClassCastException{
        return (char)operation == '+' || (char)operation == '-';
    }
}
