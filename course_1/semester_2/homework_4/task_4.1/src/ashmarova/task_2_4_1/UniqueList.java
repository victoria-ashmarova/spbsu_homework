package ashmarova.task_2_4_1;

/**
 * Class contains functional of list without same values
 * @param <T> is type of elements
 */
public class UniqueList<T> extends SingleLinkedList<T> {
    @Override
    public void add(int index, T value) throws IncorrectIndexException, ValueIsInListException {
        if (isInList(value)){
            throw new ValueIsInListException();
        }
        super.add(index, value);
    }
}
