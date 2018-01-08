import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Process of infection, checking state of net and printing it to console
 */
public class Process {
    public static void main(String args[]) {
        try {
            NetsCreator creator = new NetsCreator();
            ComputersNet net = creator.createNet(NetsCreator.getFileScanner());
            Virus virus = new RandomInfectingVirus();

            Integer numberOfFirstInfected = primaryInfection(net, virus);

            process(numberOfFirstInfected, net, virus);


        } catch (IncorrectDataException e) {
            e.message();
        } catch (FileNotFoundException e) {
            System.out.print("Error. Couldn't open the file");
        }
    }

    protected static void process(Integer numberOfFirstInfected, ComputersNet net, Virus virus) {
        int maxIterations = net.getAttemptsCoefficient() * net.getSize();
        int periodOfChecking = net.getPeriodOfChecking();

        if (numberOfFirstInfected == net.getSize()) {
            System.out.print("Primary infection didn't situate");
            return;
        }

        Queue<Integer> toInfect = new ConcurrentLinkedQueue<Integer>();
        addConnectedWithInfected(numberOfFirstInfected, net, toInfect);

        int iteration = 0;
        while (iteration < maxIterations && !toInfect.isEmpty()) {
            stepOfInfection(toInfect, net, virus);
            if (iteration % periodOfChecking == 0) {
                stepOfChecking(net, iteration / periodOfChecking + 1);
            }
            iteration++;
        }
        finalMessage(toInfect);
    }

    /** infected one computer of net and returns its number*/
    private static Integer primaryInfection(ComputersNet net, Virus virus) {
        int i = 1;
        boolean stopCondition = false;

        while (!stopCondition && i <= net.getSize()) {
            Computer current = net.getComputer(i);
            if (virus.infect(current.getProbabilityOfInfection())) {
                current.setInfection();
                stopCondition = true;
            }

            i++;
        }
        return i - 1;
    }

    /**
     * After this step first computer in queue to infect is able to be infected.
     * If it becomes infected, numbers of computers, which are connected with it, are added to queue.
     * Else its number is added to queue again
     * @param toInfect if queue with computers, which are able to be infected
     * @param net is net with computers
     * @param virus to generate current probability of infection
     */
    protected static void stepOfInfection(Queue<Integer> toInfect, ComputersNet net, Virus virus) {
        int numberToInfect = toInfect.remove();
        Computer current = net.getComputer(numberToInfect);
        if (current.isInfected()) {
            return;
        }
        if (virus.infect(current.getProbabilityOfInfection())) {
            current.setInfection();
            addConnectedWithInfected(numberToInfect, net, toInfect);
        } else {
            toInfect.add(numberToInfect);
        }
    }

    /**
     * Adds number of computers connected with infected to queue for infection
     * @param numberOfInfected is number of last infected computer
     * @param net for getting connected computers
     * @param toInfect queue for infection
     */
    protected static void addConnectedWithInfected(int numberOfInfected, ComputersNet net, Queue<Integer> toInfect) {
        try {
            for (int i = 1; i <= net.getSize(); i++) {
                if (net.firstIsConnectedWithSecond(numberOfInfected, i) && !net.getComputer(i).isInfected() && !toInfect.contains(i)) {
                    toInfect.add(i);
                }
            }
        } catch (IncorrectComputerNumberException e) {
        }
    }

    /**
     * checks state of net and prints in to console
     * @param net is object to check state
     * @param numberOfChecking is number of checking
     */
    private static void stepOfChecking(ComputersNet net, int numberOfChecking) {
        System.out.println("______________________");
        System.out.println("Number of checking is " + numberOfChecking + ". State of net:");
        net.printState();
    }

    /**
     * prints message, when process of infection stoped
     * @param toInfect is list with computers, which were able, to be infected, but not infected
     */
    private static void finalMessage(Queue<Integer> toInfect) {
        System.out.println("__________________________");
        System.out.print("Process of infection ended.");
        if (toInfect.isEmpty()) {
            System.out.println("All computers, which are available from first infected computer, are infected.");
        } else {
            System.out.print("There is no attemps to infect any computers.");
        }
    }
}
