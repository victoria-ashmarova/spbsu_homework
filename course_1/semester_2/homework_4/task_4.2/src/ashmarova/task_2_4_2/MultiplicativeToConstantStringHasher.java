package ashmarova.task_2_4_2;

import ashmarova.task_2_4_2.Hasher;

public class MultiplicativeToConstantStringHasher implements Hasher<String> {
    /**
     * gets hash of string as sum of string's elements
     * @param value is value to get hash
     * @return integer value of hash
     */
    @Override
    public int hashFunction(String value) {
        int toReturn = 0;
        int helpValue = 19;
        for (int i = 0; i < value.length(); i++){
            toReturn = toReturn + (value.charAt(i) % helpValue);
        }
        return Math.abs(toReturn);
    }
}
