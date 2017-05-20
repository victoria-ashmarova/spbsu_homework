/**
 * Class contains size of list
 * ans class Element, which contains its value
 */
abstract public class List<SomeType> implements ListInterface<SomeType>{
    protected  int size = 0;
    protected class Element{
        protected SomeType value;

        /**
         * @return value of element
         */
        protected SomeType getValue(){
            return this.value;
        }
    }

    /**
     * @return size of list
     */
    public int getSize(){
        return size;
    }
}
