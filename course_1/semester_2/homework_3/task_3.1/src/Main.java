import java.util.Scanner;

public class Main {
    /**
     * Contains types of actions: sorts and variant to exit program
     */
    private enum Sorts{
        GO_OUT, BUBBLE, MERGE, QUICK, INSERTION;

        /**
         * Translate number to action
         * @param number
         * @return action to do: which sort use or exit program
         */
        private static Sorts whichSort(int number){
            switch (number){
                case 1: {
                    return Sorts.BUBBLE;
                }
                case 2: {
                    return Sorts.MERGE;
                }
                case 3: {
                    return Sorts.QUICK;
                }
                case 4: {
                    return Sorts.INSERTION;
                }
                default: {
                    return Sorts.GO_OUT;
                }
            }
        }

        /**
         * prints actions to console
         */
        private static void printActions(){
            System.out.print("These program demonstrates work of sort arrays with integer elements.\n");
            System.out.print("Enter 1 to use Bubble sort.\n");
            System.out.print("Enter 2 to use Merge sort.\n");
            System.out.print("Enter 3 to use Quick sort.\n");
            System.out.print("Enter 4 to use Insertion sort.\n");
            System.out.print("Enter anything else to exit\n");
        }
    }

    public static void main(String args[]) {
        Sorts.printActions();
        Scanner scan = new Scanner(System.in);
        Sorts whichSort = Sorts.whichSort(scan.nextInt());
        try {
            Sorter sorter = null;
            switch (whichSort) {
                case BUBBLE: {
                    sorter = new BubbleSorter();
                    break;
                }
                case MERGE: {
                    sorter = new MergeSorter();
                    break;
                }
                case QUICK: {
                    sorter = new QSorter();
                    break;
                }
                case INSERTION: {
                    sorter = new InsertionSorter();
                    break;
                }
                default: {
                    System.out.print("You didn't chose type of sort");
                    break;
                }
            }
            if (sorter != null) {
                int array[] = Reader.getArray();
                sorter.sort(array);
                sorter.print(array);
            }
        } catch (NoArrayException e){
            System.out.print("Couldn't sort array");
        }
    }
}
