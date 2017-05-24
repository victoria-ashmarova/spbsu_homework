import list.IncorrectIndexException;
import list.NoValueInListException;
import list.ValueIsInListException;

/**
 * Class which realizes hash table functional
 * @param <T> is type of data
 */
public class HashTable <T> implements HashTableInterface <T> {
    private final double CRITICAL_LOAD_FACTOR = 1.5;
    private final int INITIAL_SIZE = 10;
    private final double EXTENSION_VALUE = 1.2;
    private list.UniqueList<T> table[] = new list.UniqueList[INITIAL_SIZE];
    private Hasher<T> hasher = null;

    public HashTable(Hasher<T> hasher){
        this.hasher = hasher;
    }

    private int amountOfConflicts = 0;
    private int amountOfElements = 0;

    /**
     * addes value to hash table
     * @param value is value to add
     * @throws ProblemWithElementsException when value to addition is in table
     */
    @Override
    public void add(T value) throws ProblemWithElementsException {
        int position = hasher.hashFunction(value) % table.length;

        if (table[position] == null){
            table[position] = new list.UniqueList<>();
        }

        try{
            table[position].add(table[position].getSize() + 1, value);
            amountOfElements++;
            if (table[position].getSize() == 2) {
                amountOfConflicts++;
            }
            if (getLoadFactor() > CRITICAL_LOAD_FACTOR || getMaxLenght() > table.length) {
                refill((int) (table.length * EXTENSION_VALUE));
            }
        } catch (ValueIsInListException e) {
            throw new ProblemWithElementsException("This element is in table yet.");
        } catch (IncorrectIndexException e) {
            throw new ProblemWithElementsException("Unknown problem with addition.");
        }
    }

    /**
     * removes value from hash table
     * @param value is value to remove
     * @throws ProblemWithElementsException when value for removal is not in table
     */
    @Override
    public void remove(T value) throws ProblemWithElementsException {
        int position = hasher.hashFunction(value) % table.length;
        try {
            table[position].removeFromValue(value);
            amountOfElements--;
            if (table[position].getSize() == 1) {
                amountOfConflicts--;
            }
            if (table[position].getSize() == 0) {
                table[position] = null;
            }
        } catch (NullPointerException e) {
            throw new ProblemWithElementsException("There is not this element in table.");
        } catch (IncorrectIndexException e) {
            throw new ProblemWithElementsException("Unknown problem with removal.");
        } catch (NoValueInListException e) {
            throw new ProblemWithElementsException("There is not this element in table.");
        }
    }

    /**
     * checks if value is in list
     * @param value is value to check
     * @return true if value is in table
     */
    @Override
    public boolean isInTable(T value) {
        int position = hasher.hashFunction(value) % table.length;
        if (table[position] == null) {
            return false;
        }
        return table[position].isInList(value);
    }

    /**
     * prints statistics of hash table.
     * amount of cells, amount of elements, load factor, amount of conflicts,
     * max lenght of chain and currect hash function are printed.
     */
    @Override
    public void printStat() {
        System.out.print("Amount of cells " + table.length +
                "\nAmount of elements " + amountOfElements +
                "\nLoad factor " + getLoadFactor() +
                "\nAmount of conflicts " + amountOfConflicts +
                "\nMax lenght in conflict cells " + getMaxLenght() +
                "\nCurrent hash function " + hasher.toString() + "\n");
    }

    /**
     * @return lenght of max chain
     */
    private int getMaxLenght() {
        int toReturn = 0;
        for (list.UniqueList<T> cell : table){
            if (cell != null) {
                if (cell.getSize() > toReturn) {
                    toReturn = cell.getSize();
                }
            }
        }
        return toReturn;
    }

    /**
     * @return load factor
     */
    private double getLoadFactor(){
        return (amountOfElements) / table.length;
    }

    /**
     * @return number of cells
     */
    public int getSize(){
        return table.length;
    }

    /**
     * @return amount of elements
     */
    public int getAmountOfElements(){
        return amountOfElements;
    }

    /**
     * sets hash function
     * @param hasher is hasher to set
     * @throws ProblemWithElementsException when there is no ability to refill table
     */
    public void setHasher(Hasher<T> hasher) throws ProblemWithElementsException {
        this.hasher = hasher;
        refill(table.length);
    }

    /**
     * chandes table size and location of elements.
     * @param newSizeOfTable is size of table to refill
     * @throws ProblemWithElementsException when ther eis no ability to refill table
     */
    public void refill(int newSizeOfTable) throws ProblemWithElementsException {
        list.UniqueList<T>[] tableToRefill = table;
        table = new list.UniqueList[newSizeOfTable];
        amountOfConflicts = 0;
        amountOfElements = 0;
        for (list.UniqueList<T> cell : tableToRefill) {
            try {
                while (cell.getSize() > 0) {
                    T element = cell.getFromIndex(1);
                    add(element);
                    cell.removeFromIndex(1);
                }
            } catch (IncorrectIndexException e) {
                throw new ProblemWithElementsException("Unknown problem with table's refilling.");
            } catch (NullPointerException e) {}
        }
    }
}
