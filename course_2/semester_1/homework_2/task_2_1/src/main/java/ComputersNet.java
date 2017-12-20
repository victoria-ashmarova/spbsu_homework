import java.util.ArrayList;

/**
 * Created by Виктория on 13.12.2017.
 */
public class ComputersNet {
    private ArrayList<Computer> computers;
    private boolean[][] connection;

    //сюда скидывается уже чтецом полученная инфа
    public ComputersNet(ArrayList<Computer> computers, boolean[][]connection) {
        this.computers = computers;
        this.connection = connection;
    }

    public Computer getComputer(int index) {
        return this.computers.get(index);
    }

    public int getSize() {
        return this.computers.size();
    }

    public boolean areConnected(int first, int second) throws IncorrectComputerNumberException{
        if (first < 0  ||  second < 0) {
            throw new IncorrectComputerNumberException("Number of computer is less then zero");
        }
        if (first > computers.size() || second > computers.size()) {
            throw new IncorrectComputerNumberException("Number of computer is more then amount of computers");
        }

        return this.connection[first][second];
    }

    public void printState() {
        for (Computer computer : computers) {
            computer.print();
        }
    }
}
