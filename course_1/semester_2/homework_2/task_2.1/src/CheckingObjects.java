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
}
