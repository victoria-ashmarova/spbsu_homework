import java.util.Scanner;

abstract public class AbstractSorter implements Sorter {
    @Override
    public int[] getArray(){
        Scanner scan = new Scanner(System.in);
        int size = 0;
        while (size <= 0){
            System.out.print("Enter the size of array - positive integer value\n");
            size = scan.nextInt();
        }
        int array[] = new int[size];
        for (int i = 0; i < size; i++) {
            System.out.print("Enter the element number " + (i + 1) + " : ");
            array[i] = scan.nextInt();
        }
            return array;
    }

    @Override
    abstract public void sort(int[] array);

    /**
     * Changes places of two elements
     * @param array
     * @param indexFirst place of the fisrt element, where will be the second element
     * @param indexSecond place of the second element, where will be the first element
     */
    protected void swap(int[] array, int indexFirst, int indexSecond){
        int temp = array[indexFirst];
        array[indexFirst] = array[indexSecond];
        array[indexSecond] = temp;
    }

    @Override
    public void print(int[] array){
        System.out.print("Sorted array:\n");
        for (int i = 0; i < array.length; i++){
            System.out.print((i + 1) + ") " + array[i] + "; ");
        }
    }
}
