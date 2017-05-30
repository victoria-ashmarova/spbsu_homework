package ashmarova.task_2_4_2.list;

abstract public class List <T> implements ListInterface<T> {
    /**
     * the first element of ashmarova.task_2_4_2.list
     */
    protected Element first;

    /**
     * size of ashmarova.task_2_4_2.list
     */
    protected  int size = 0;

    /**
     * ashmarova.task_2_4_2.list element
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
     * @return size of ashmarova.task_2_4_2.list
     */
    @Override
    public int getSize(){
        return size;
    }
}
