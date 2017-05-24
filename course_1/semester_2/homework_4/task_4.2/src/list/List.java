package list;

abstract public class List <T> implements ListInterface<T> {
    /**
     * the first element of list
     */
    protected Element first;

    /**
     * size of list
     */
    protected  int size = 0;

    /**
     * list element
     */
    protected class Element{
        protected T value;
        protected Element next;
        protected Element(T value, Element next){
            this.value = value;
            this.next = next;
        }
    }

    /**
     * @return size of list
     */
    @Override
    public int getSize(){
        return size;
    }
}
