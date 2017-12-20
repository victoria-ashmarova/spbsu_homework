/**
 * Created by Виктория on 13.12.2017.
 */
public class Computer {
    private final int number;
    private OperationSystem operationSystem;
    private boolean isInfected = false;

    public Computer(int number, OperationSystem operationSystem) {
        this.number = number;
        this.operationSystem = operationSystem;
    }

    public boolean isInfected() {
        return this.isInfected;
    }

    public void setInfection() {
        this.isInfected = true;
    }

    public double getProbabilityOfInfection() {
        return this.operationSystem.getProbabilityOfInfection();
    }

    public void print() {
        System.out.println(number + ".) " + operationSystem.name() + " is infected:" + isInfected());
    }
}
