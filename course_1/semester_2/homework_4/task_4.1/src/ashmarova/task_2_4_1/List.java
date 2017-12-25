package ashmarova.task_2_4_1;

abstract public class List <T> implements ListInterface<T> {
    /**
     * size of list
     */
    protected  int size = 0;

    @Override
    public int getSize(){
        return size;
    }
}
