abstract public class List <SomeType> implements ListInterface<SomeType> {
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
        protected SomeType value;
        protected Element next;
        protected Element(SomeType value, Element next){
            this.value = value;
            this.next = next;
        }
    }

    @Override
    public int getSize(){
        return size;
    }
}
