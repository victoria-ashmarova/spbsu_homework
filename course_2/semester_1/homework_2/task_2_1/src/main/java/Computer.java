/**
 * Created by Виктория on 13.12.2017.
 */
public class Computer {
    private NameOfOS nameOfOS;
    private boolean isInfected = false;

    public void setInfection() {
        this.isInfected = true;
    }

    public double getProbabilityOfInfection() {
        return this.nameOfOS.getProbabilityOfInfection();
    }

    public String getNameOfOS() {
        return this.nameOfOS.getName();
    }
}
