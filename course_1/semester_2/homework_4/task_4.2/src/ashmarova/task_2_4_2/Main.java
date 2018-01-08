package ashmarova.task_2_4_2;

import java.util.Scanner;

public class Main {
    /**
     * describes action, which can be done with hash table
     */
    private enum Actions{
        EXIT, ADD, REMOVE, SEARCH, GET_STATISTICS, CHOOSE_HASH_FUNCTIONS;

        /**
         * casts number to action
         * @param number to choose action
         * @return what action to do
         */
        public static Actions fromInt(int number) {
            switch (number) {
                case 1: {
                    return ADD;
                }
                case 2: {
                    return REMOVE;
                }
                case 3: {
                    return SEARCH;
                }
                case 4: {
                    return GET_STATISTICS;
                }
                case 5: {
                    return CHOOSE_HASH_FUNCTIONS;
                }
                default: {
                    return EXIT;
                }
            }
        }

        /**
         * prints comformity numbers and actions
         */
        public static void printActions() {
            System.out.print("Press\n 1 - to add element to table\n" +
                    "2 - to remove element from table\n" +
                    "3 - to search element in table\n" +
                    "4 - to get table's statistics\n" +
                    "5 - to choose hash function\n" +
                    "else - to escape.");
        }
    }

    /**
     * describes available hash functions
     */
    private enum HashFunctions{
        MULTIPLICATIVE_TO_CONSTANT, POLYNOMIAL, DEFAULT;

        /**
         * prints conformity numbers and hash functions
         */
        public static void printFunction() {
            System.out.print("Press\n 1 - to choose function with polynomial\n" +
                    "2 - to choose function with multiplication to constant\n" +
                    "else - to choose default function");
        }

        /**
         * casts number to kind of hash function
         * @param number to choose action
         * @return kind of hash function
         */
        public static HashFunctions fromInt(int number) {
            switch (number) {
                case 1: {
                    return POLYNOMIAL;
                }
                case 2: {
                    return MULTIPLICATIVE_TO_CONSTANT;
                }
                default: {
                    return DEFAULT;
                }
            }
        }

    }

    /**
     * realizes addition to hash table
     * @param scan to get value to add
     * @param table to add value
     */
    public static void addition(Scanner scan, HashTable<String> table) {
        System.out.print("Enter the string to add: ");
        String toAdd = scan.next();
        try {
            table.add(toAdd);
            System.out.println("Value was added.");
        } catch (ValueIsInTableException e) {
            e.message();
        } catch (UnknownProblemException e) {
            e.printStackTrace();
        }
    }

    /**
     * realizes removal value from hash table
     * @param scan to get value to remove
     * @param table to remove value
     */
    public static void removal(Scanner scan, HashTable<String> table) {
        System.out.print("Enter the string to remove: ");
        String toRemove = scan.next();
        try {
            table.remove(toRemove);
            System.out.println("Value was removed.");
        } catch (UnknownProblemException e) {
            e.message();
        } catch (ValueIsNotInTableException e) {
            e.message();
        }
    }

    /**
     * realizes search value in hash table
     * @param scan to get value to search
     * @param table to search value
     */
    public static void search(Scanner scan, HashTable<String> table) {
        System.out.print("Enter the string to search: ");
        String toSearch = scan.next();
        if (table.isInTable(toSearch)) {
            System.out.println("Value is in table.");
        } else {
            System.out.println("Value is not in table");
        }
    }

    /**
     * realizes printing statistics
     * @param table to print statistic
     */
    public static void getStatistics(HashTable<String> table) {
        table.printStat();
    }

    /**
     * realizes setting hash function
     * @param scan to get information about hash function
     * @param table to change hasher
     */
    public static void setFunction(Scanner scan, HashTable<String> table) {
        Hasher<String> hasher = null;
        HashFunctions.printFunction();
        HashFunctions function = HashFunctions.fromInt(scan.nextInt());
        switch (function) {
            case MULTIPLICATIVE_TO_CONSTANT: {
                hasher = new MultiplicativeToConstantStringHasher();
                break;
            }
            case POLYNOMIAL: {
                hasher = new PolynomialStringHasher();
                break;
            }
            case DEFAULT: {
                hasher = new DefaultHasher<>();
            }
        }
        try {
            table.setHasher(hasher);
        } catch (UnknownProblemException e) {
            e.message();
        }
    }

    public static void main(String args[]){
        System.out.println("Here you can work with hash table.");
        HashTable<String> table = new HashTable<>(new DefaultHasher<>());
        Scanner scan = new Scanner(System.in);
        Actions.printActions();
        Actions currentAction = Actions.fromInt(scan.nextInt());
        while (currentAction != Actions.EXIT) {
            switch (currentAction) {
                case ADD: {
                    addition(scan, table);
                    break;
                }
                case REMOVE: {
                    removal(scan, table);
                    break;
                }
                case SEARCH: {
                    search(scan, table);
                    break;
                }
                case GET_STATISTICS: {
                    getStatistics(table);
                    break;
                }
                case CHOOSE_HASH_FUNCTIONS: {
                    setFunction(scan, table);
                    break;
                }
            }
            System.out.println("Enter the action again.");
            currentAction = Actions.fromInt(scan.nextInt());
        }
        System.out.println("You have finished work with hash table.");
    }
}
