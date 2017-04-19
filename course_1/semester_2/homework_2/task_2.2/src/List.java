/**
 * Class contains size of list
 * ans class Element, which contains its value
 */
abstract public class List implements ListInterface{
    protected  int size = 0;
    protected class Element{
        Object value;
    }

    public int getSize(){
        return size;
    }
}
