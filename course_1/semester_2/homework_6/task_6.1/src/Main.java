import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        Scanner consoleScanner = new Scanner(System.in);
        System.out.print("Enter the name of file with tree: ");
        String fileName = consoleScanner.next();
        try {
            Scanner scanner = new Scanner(new File(fileName));
            TreeMaker treeMaker = new TreeMaker(scanner);
            treeMaker.makeTree();
            treeMaker.print();
            System.out.print(" " + treeMaker.getValue());
        } catch (lackOfValuesException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
