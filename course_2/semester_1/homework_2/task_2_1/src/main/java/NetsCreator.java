import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Creates net, gets information about it from file
 */
public class NetsCreator {
    /** gets name of file from console*/
    private static String nameOfFile() {
        System.out.print("Enter the name of file>");
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }

    /** gets file scanner from its name*/
    public static Scanner getFileScanner() throws FileNotFoundException {
        File file = new File(nameOfFile());
        return new Scanner(file);
    }

    /**
     * reads from file information about net and adds it to computersNet
     * @return ComputersNet
     * @throws IncorrectDataException when there is no file to read or file to read is incorrect
     */
    public ComputersNet createNet(Scanner fileScanner) throws IncorrectDataException {
        try {
            int numberOfComputers = fileScanner.nextInt();
            int period = fileScanner.nextInt();
            int attemptsCoefficient = fileScanner.nextInt();

            fileScanner.nextLine();
            if (numberOfComputers < 2) {
                throw new IncorrectDataException("Error. Amount of computers in net is less, than 2.");
            }
            if (period < 1) {
                throw new IncorrectDataException("Error. Period of checking of net is less, than 1");
            }
            if (attemptsCoefficient < 1) {
                throw new IncorrectDataException("Error. Iteration coefficient is less, than 1");
            }

            boolean[][] connection = createConnectionTable(numberOfComputers);

            ArrayList<Computer> computers = new ArrayList<Computer>();

            while (fileScanner.hasNext()) {
                String[] data = fileScanner.nextLine().split(" ");
                addComputer(data, computers, connection);
            }

            fileScanner.close();
            return new ComputersNet(computers, connection, period, attemptsCoefficient);

        } catch (NullPointerException e) {
            throw new IncorrectDataException ("Error. Couldn't open the file to create net.");
        } catch (NoSuchElementException e) {
            throw new IncorrectDataException ("Error. There is incorrect data in file.");
        }
    }

    /** created boolean table of connection, where all values are false*/
    private boolean[][] createConnectionTable(int numberOfComputers) {
        boolean[][] connection = new boolean[numberOfComputers][numberOfComputers];
        for (int i = 0; i < numberOfComputers; i++) {
            for (int j = 0; j < numberOfComputers; j++) {
                connection[i][j] = false;
            }
        }
        return connection;
    }

    /**
     * added new computer to list with net and set information about its connections
     * @param data is operation system and connected computers
     * @param computers list with computers for addition new computer
     * @param connection table with information about connection
     * @throws IncorrectDataException when there are incorrect number of computer of name of operation system
     */
    private void addComputer(String[] data, ArrayList<Computer> computers, boolean[][]connection) throws IncorrectDataException{
        try {
            int numberOfComputer = Integer.parseInt(data[0]) - 1;

            OperationSystem operationSystem = OperationSystem.valueOf(data[1]);
            computers.add(new Computer(numberOfComputer, operationSystem));

            for (int i = 2; i < data.length; i++) {
                int numberOfConnected = Integer.parseInt(data[i]) - 1;
                if (numberOfComputer == numberOfConnected) {
                    throw new IncorrectDataException("Incorrect input data");
                }
                connection[numberOfComputer][numberOfConnected] = true;

            }
        } catch (IllegalArgumentException e) {
            throw new IncorrectDataException("Error. There is incorrect name of operation system");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IncorrectDataException("Error. There is incorrect input data about connection between computers.");
        } catch (NullPointerException e) {
            throw new IncorrectDataException("Incorrect input data");
        }
    }
}
