/**
 * Keeps information about operation system and state of infection
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
        System.out.println(number + 1 + ".) " + operationSystem.name() + " is infected:" + isInfected());
    }
}
