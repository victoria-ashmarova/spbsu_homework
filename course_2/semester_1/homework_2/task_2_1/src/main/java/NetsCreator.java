import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Виктория on 19.12.2017.
 * чтение из файла и призыв конструктора
 */
public class NetsCreator {
    private String nameOfFile() {
        System.out.println("Enter the name of file.");
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }

    private FileReader getFileReader() throws FileNotFoundException {
        return new FileReader(nameOfFile());
    }

    private ArrayList<Computer> getComputersInformation() {
        ArrayList<Computer> computers = new ArrayList<Computer>();
        //todo
        return computers;
    }


    public ComputersNet createNet() {
        /*try {

        } catch (FileNotFoundException e) {
            System.out.print("Couldn't create net. There is no file with net's description.");
        }*/
        return null;
    }
}
