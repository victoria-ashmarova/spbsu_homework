/**
 * Created by Виктория on 19.12.2017.
 */
public enum NameOfOS {
    WINDOWS, MAS_O__S, LINUX;

    private final double windowsProbabilityOfInfection = 0.5;
    private final double linuxProbabilityOfInfection = 0.7;
    private final double macOSProbabilityOfInfection = 0.3;

    public String getName() {
        switch (this) {
            case LINUX: {
                return "LINUX";
            }
            case MAS_O__S: {
                return "MAS_O_S";
            }
            case WINDOWS: {
                return "WINDOWS";
            }
        }
        return null;
    }

    public double getProbabilityOfInfection() {
        switch (this) {
            case LINUX: {
                return linuxProbabilityOfInfection;
            }
            case MAS_O__S: {
                return macOSProbabilityOfInfection;
            }
            case WINDOWS: {
                return windowsProbabilityOfInfection;
            }
        }
        return 0;
    }
}
