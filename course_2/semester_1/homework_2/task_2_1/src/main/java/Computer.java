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

    public void setInfection() {
        this.isInfected = true;
    }

    public double getProbabilityOfInfection() {
        return this.operationSystem.getProbabilityOfInfection();
    }

    public String getOperationSystem() {
        return this.operationSystem.name();
    }
}
