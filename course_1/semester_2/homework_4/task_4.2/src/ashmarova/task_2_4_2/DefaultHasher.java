package ashmarova.task_2_4_2;

public class DefaultHasher<T> implements Hasher<T> {
    /**
     * gets default hash code
     * @param value is value to get hash
     * @return integer value of hash
     */
    @Override
    public int hashFunction(T value) {
        return value.hashCode();
    }
}
