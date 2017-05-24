public class PolynomialStringHasher implements Hasher<String> {
    /**
     * gets hash as value of polynomial
     * @param value is value to get hash
     * @return integer value of hash
     */
    @Override
    public int hashFunction(String value) {
        int helpValue = 47;
        int toReturn = 0;
        for (int i = 0; i < value.length(); i++){
            toReturn = helpValue * toReturn + value.charAt(i) % helpValue;
        }
        return toReturn;
    }
}
