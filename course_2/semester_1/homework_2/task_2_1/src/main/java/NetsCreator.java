import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Creates net, getting information about it from file
 */
public class NetsCreator {
    /** gets name of file from console*/
    private String nameOfFile() {
        System.out.println("Enter the name of file>");
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }

    /** gets file scanner from its name*/
    private Scanner getFileScanner() throws FileNotFoundException {
        return new Scanner(nameOfFile());
    }

    /**
     * reads from file information about net and adds it to computersNet
     * @return ComputersNet
     * @throws NoNetException when there is no file to read
     */
    public ComputersNet createNet() throws NoNetException {
        try {
            Scanner file = getFileScanner();
            int numberOfComputers = file.nextInt();

            boolean[][] connection = new boolean[numberOfComputers][numberOfComputers];
            for (int i = 0; i < numberOfComputers; i++) {
                for (int j = 0; j < numberOfComputers; j++) {
                    connection[i][j] = false;
                }
            }
            ArrayList<Computer> computers = new ArrayList<Computer>();

            while (file.hasNext()) {
                String[] data = file.next().split(" ");
                addComputer(data, computers, connection);
            }

            return new ComputersNet(computers, connection);

        } catch (FileNotFoundException e) {
            throw new NoNetException ("There is no file to create net");
        }
    }

    /**
     * added new computer to list with net and set information about its connections
     * @param data is operation system and connected computers
     * @param computers list with computers for addition new computer
     * @param connection table with information about connection
     * @throws NoNetException when there are incorrect number of computer of name of operation system
     */
    private void addComputer(String[] data, ArrayList<Computer> computers, boolean[][]connection) throws NoNetException{
        int numberOfComputer = Integer.getInteger(data[0]);
        try {
            OperationSystem operationSystem = OperationSystem.valueOf(data[1]);
            computers.add(new Computer(numberOfComputer, operationSystem));

            for (int i = 2; i < data.length; i++) {
                int numberOfConnected = Integer.getInteger(data[i]);
                connection[numberOfComputer][numberOfConnected] = true;

            }
        } catch (IllegalArgumentException e) {
            throw new NoNetException("There is incorrect file with data about net");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoNetException("There is incorrect file with data about net");
        }
    }
}
