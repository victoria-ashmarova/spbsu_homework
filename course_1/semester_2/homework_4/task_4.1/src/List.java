abstract public class List <SomeType> implements ListInterface<SomeType> {
    /**
     * size of list
     */
    protected  int size = 0;

    /**
     * list element
     */
    protected class Element{
        SomeType value;
    }

    public int getSize(){
        return size;
    }
}
