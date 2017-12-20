/**
 * Created by Виктория on 19.12.2017.
 */
public enum OperationSystem {
    WINDOWS, MAC_O_S, LINUX;

    private final double windowsProbabilityOfInfection = 0.5;
    private final double linuxProbabilityOfInfection = 0.7;
    private final double macOSProbabilityOfInfection = 0.3;

    public double getProbabilityOfInfection() {
        switch (this) {
            case LINUX: {
                return linuxProbabilityOfInfection;
            }
            case MAC_O_S: {
                return macOSProbabilityOfInfection;
            }
            case WINDOWS: {
                return windowsProbabilityOfInfection;
            }
        }
        return 0;
    }
}
