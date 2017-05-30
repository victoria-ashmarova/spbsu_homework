package ashmarova.task_2_4_2;

public interface Hasher<T> {
    /**
     * gets hash of value;
     * @param value is value to get hash
     * @return integer value of hash
     */
    int hashFunction(T value);
}
