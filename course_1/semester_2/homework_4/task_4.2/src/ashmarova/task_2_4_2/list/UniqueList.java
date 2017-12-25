package ashmarova.task_2_4_2.list;

/**
 * Class contains functional of ashmarova.task_2_4_2.list without same values
 * @param <T> is type of elements
 */
public class UniqueList<T> extends SingleLinkedList<T> {
    /**
     * addes value to ashmarova.task_2_4_2.list without ability to add value again
     * @param index is future index of added element
     * @param value added element
     * @throws IncorrectIndexException when index to add is incorrect
     * @throws ValueIsInListException when value to add is in ashmarova.task_2_4_2.list yet
     */
    @Override
    public void add(int index, T value) throws IncorrectIndexException, ValueIsInListException {
        if (isInList(value)){
            throw new ValueIsInListException();
        }
        super.add(index, value);
    }
}
