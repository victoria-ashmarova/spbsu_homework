/**
 * Class gives ability to upload tree from file and get value of it
 */

import java.io.File;
import java.io.FileNotFoundException;
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
            System.out.print("Tree: ");
            treeMaker.print();
            System.out.print("\nValue of tree: " + treeMaker.getValue());
        } catch (FileNotFoundException e) {
            System.out.print("Couldn't open the file.");
        } catch (IncorrectTreeException e) {
            System.out.print("Couldn't work with tree.");
        }
    }
}
