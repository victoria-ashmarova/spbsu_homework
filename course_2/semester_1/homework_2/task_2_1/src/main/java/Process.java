import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Process of infection and checking state of net
 */
public class Process {
    public static void main(String args[]) {
        try {
            NetsCreator creator = new NetsCreator();
            ComputersNet net = creator.createNet();

            Random rnd = new Random();

            Integer numberOfFirstInfected = primaryInfection(net, rnd);

            if (numberOfFirstInfected == net.getSize()) {
                System.out.print("Primary infection didn't situate");
                return;
            }

            //процесс заражения?
            Queue<Integer> toInfect = new ConcurrentLinkedQueue<Integer>();
            addConnectedWithInfected(numberOfFirstInfected, net, toInfect);

            //надо бы как-то иначе захардкордить эту границу
            int maxIterations = 2 * net.getSize();
            int iteration = 0;
            while (iteration < maxIterations && !toInfect.isEmpty()) {
                stepOfInfection(toInfect, net, rnd);
                stepOfChecking(net, iteration); //период?
                iteration++;
            }
        } catch (IncorrectDataException e) {
            e.message();
        }
    }

    /** infected one of computer and returns its number*/
    private static Integer primaryInfection(ComputersNet net, Random rnd) {
        int i = 0;
        boolean stopCondition = false;

        while (!stopCondition && i < net.getSize()) {
            double currentProbability = getCurrentProbabilityOfInfection(rnd);
            Computer current = net.getComputer(i);

            if (current.getProbabilityOfInfection() > currentProbability) {
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
     * @param rnd to generate current probability of infection
     */
    private static void stepOfInfection(Queue<Integer> toInfect, ComputersNet net, Random rnd) {
        int numberToInfect = toInfect.remove();
        double currentProbabilityOfInfection = getCurrentProbabilityOfInfection(rnd);
        Computer current = net.getComputer(numberToInfect);
        if (currentProbabilityOfInfection < current.getProbabilityOfInfection()) {
            current.setInfection();
            addConnectedWithInfected(numberToInfect, net, toInfect);
        } else {
            toInfect.add(numberToInfect);
        }
    }

    private static void addConnectedWithInfected(int numberOfInfected, ComputersNet net, Queue<Integer> toInfect) {
        try {
            for (int i = 0; i < net.getSize(); i++) {
                if (net.areConnected(numberOfInfected, i) && !net.getComputer(i).isInfected()) {
                    toInfect.add(i);
                }
            }
        } catch (IncorrectComputerNumberException e) {
        }
    }

    private static double getCurrentProbabilityOfInfection(Random rnd) {
        double currentProbabilityOfInfection = rnd.nextDouble();
        return currentProbabilityOfInfection - (int) currentProbabilityOfInfection;
    }

    private static void stepOfChecking(ComputersNet net, int numberOfChecking) {
        System.out.println("Number of checking is " + numberOfChecking + ". State of net:");
        net.printState();
    }
}
