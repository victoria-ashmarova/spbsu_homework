import java.util.ArrayList;

/**
 * Keeps information about connection between computers
 */
public class ComputersNet {
    private ArrayList<Computer> computers;
    private boolean[][] connection;
    private int periodOfChecking;
    private int attemptsCoefficient;


    public ComputersNet(ArrayList<Computer> computers, boolean[][]connection, int periodOfChecking, int attemptsCoefficient) {
        this.computers = computers;
        this.connection = connection;
        this.periodOfChecking = periodOfChecking;
        this.attemptsCoefficient = attemptsCoefficient;
    }

    public Computer getComputer(int index) {
        return this.computers.get(index - 1);
    }

    public int getSize() {
        return this.computers.size();
    }

    public int getPeriodOfChecking() {
        return this.periodOfChecking;
    }

    public int getAttemptsCoefficient() {
        return this.attemptsCoefficient;
    }

    public boolean firstIsConnectedWithSecond(int first, int second) throws IncorrectComputerNumberException{
        if (first < 1  ||  second < 1) {
            throw new IncorrectComputerNumberException("Number of computer is less then zero");
        }
        if (first > computers.size() || second > computers.size()) {
            throw new IncorrectComputerNumberException("Number of computer is more then amount of computers");
        }

        return this.connection[first - 1][second - 1];
    }

    public void printState() {
        for (Computer computer : computers) {
            computer.print();
        }
    }
}
