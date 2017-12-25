/**
 * Class contains size of list
 * ans class Element, which contains its value
 */
abstract public class List<T> implements ListInterface<T> {
    protected  int size = 0;

    abstract protected class Element{
        protected T value;

        Element(T value){
            this.value = value;
        }
    }

    /**
     * @return size of list
     */
    public int getSize(){
        return size;
    }
}
