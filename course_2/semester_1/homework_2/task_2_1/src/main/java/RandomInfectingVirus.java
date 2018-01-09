import java.util.Random;

/**
 * virus contains Random to generate value for comparing with parameter probability of infection
 */
public class RandomInfectingVirus implements Virus{
    private Random rnd;

    public RandomInfectingVirus() {
        this.rnd = new Random();
    }

    /**
     * @param probabilityOfInfection is probability of infection to compare
     * @return true if infection will be situated
     */
    public boolean infect(double probabilityOfInfection) {
        double currentProbabilityOfInfection = rnd.nextDouble();
        return (currentProbabilityOfInfection - (int) currentProbabilityOfInfection) < probabilityOfInfection;
    }
}
