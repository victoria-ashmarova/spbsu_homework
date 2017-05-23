/**
 * Class contains functional of list without same values
 * @param <SomeType> is type of elements
 */
public class UniqueList<SomeType> extends SingleLinkedList<SomeType> {
    @Override
    public void add(int index, SomeType value) throws IncorrectIndexException, ValueIsInListException {
        if (isInList(value)){
            throw new ValueIsInListException();
        }
        super.add(index, value);
    }
}
