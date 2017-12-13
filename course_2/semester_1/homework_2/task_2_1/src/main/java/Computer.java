/**
 * Created by Виктория on 13.12.2017.
 */
abstract public class Computer {
    protected String nameOfOS;
    protected double probabilityOfInfection;
    protected boolean isInfected = false;

    //how to infect?
    public void setInfection() {
        this.isInfected = true;
    }

    public double getProbabilityOfInfection() {
        return this.probabilityOfInfection;
    }

    public String getNameOfOS() {
        return this.nameOfOS;
    }
}
