import java.util.Scanner;

public class Main {
    /**
     * Contains types of actions: sorts and variant to exit program
     */
    private enum Sorts{GO_OUT, BUBBLE, MERGE, QUICK, INSERTION};

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
        System.out.print("Enter other number to exit\n");
    }

    public static void main(String args[]){
        printActions();
        Scanner scan = new Scanner(System.in);
        int toAction = scan.nextInt();
        Sorts whichSort = whichSort(toAction);
        switch (whichSort){
            case BUBBLE:{
                BubbleSorter bSorter = new BubbleSorter();
                int array[] = bSorter.getArray();
                bSorter.sort(array);
                bSorter.print(array);
                break;
            }
            case MERGE:{
                MergeSorter mSorter = new MergeSorter();
                int array[] = mSorter.getArray();
                mSorter.sort(array);
                mSorter.print(array);
                break;
            }
            case QUICK:{
                QSorter qSorter = new QSorter();
                int array[] = qSorter.getArray();
                qSorter.sort(array);
                qSorter.print(array);
                break;
            }
            case INSERTION:{
                InsertionSorter insertSorter = new InsertionSorter();
                int array[] = insertSorter.getArray();
                insertSorter.sort(array);
                insertSorter.print(array);
                break;
            }
            default: {
                System.out.print("You didn't chose type of sort");
                break;
            }
        }
    }
}
