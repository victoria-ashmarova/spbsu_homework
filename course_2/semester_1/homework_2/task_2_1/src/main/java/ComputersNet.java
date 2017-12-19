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

    public Computer getComputer(int number) {
        return this.computers.get(number);
    }

    public boolean areConnected(int first, int second) throws IncorrectComputerNumberException{
        if (first < 0  || first > computers.size() || second < 0 || second > computers.size()) {
            throw new IncorrectComputerNumberException();
        }
        return this.connection[first][second];
    }

    public class IncorrectComputerNumberException extends Exception {}
    //добавить исключение для неправильных прочтений с файла
}
