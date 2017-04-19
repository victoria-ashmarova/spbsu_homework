/**
 * Class contains methods, which make array with infix expression from string
 */
public class Reader{
    /**
     * class for counter
     */
    private class Counter{
        int index = 0;
    }

    /**
     * Makes array with expression in infix form from string element
     * @param line string element with expression
     * @return infix form expression in array of objects
     */
    public Object[] toArray(String line){
        Counter counter = new Counter();
        Object toReturn[] = new Object[line.length()];
        int index = 0;
        while (counter.index < line.length()){
            if (isDigit(line.charAt(counter.index))){
                int toAdd = makeNumber(line, counter);
                toReturn[index] = toAdd;
                index++;
            } else {
                if (isOperation(line.charAt(counter.index))) {
                    char toAdd = line.charAt(counter.index);
                    toReturn[index] = toAdd;
                    index++;
                }
                counter.index++;
            }
        }
        toReturn = overrideArray(toReturn, index);
        return toReturn;
    }

    /**
     * makes array size equal amount of elements
     * @param array
     * @param newSize right size
     * @return array with right size
     */
    private Object[] overrideArray(Object array[], int newSize){
        Object newArray[] = new Object[newSize];
        for (int i = 0; i < newSize; i++){
            newArray[i] = array[i];
        }
        return newArray;
    }

    /**
     * Check if the element digit 0 to 9
     * @param element
     * @return true if element is digit
     */
    private boolean isDigit(char element){
        return element >='0' && element <='9';
    }

    /**
     * checks if element operation of bracket
     * @param element
     * @return
     */
    private boolean isOperation(char element){
        return element =='+' || element == '-' || element == '*' || element == '/' || element == '(' || element == ')';
    }

    /**
     * makes integer number from some digits
     * @param line string element to get digits
     * @param counter counter to know where get digits in line
     * @return integer number
     */
    private int makeNumber(String line, Counter counter){
        int toReturn = 0;
        while (counter.index < line.length() && isDigit(line.charAt(counter.index))){
            toReturn = toReturn * 10 + (line.charAt(counter.index) - '0');
            counter.index++;
        }
        return toReturn;
    }
}
