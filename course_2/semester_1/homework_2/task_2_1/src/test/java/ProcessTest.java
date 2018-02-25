import org.junit.Test;

import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import static org.junit.Assert.*;

public class ProcessTest {
    /** definition of correct ner*/
    private String testData = "6 2 3\n1 WINDOWS 3 2\n2 LINUX 1\n3 LINUX 1 4 5\n4 MAC_O_S 3 1\n5 LINUX 4\n6 LINUX 1 3 5\n";

    /** test correctness of step of infection*/
    @Test
    public void firstStepOfInfectionTest() throws IncorrectDataException {
        Virus rnd = new RandomInfectingVirus();
        NetsCreator creator = new NetsCreator();
        ComputersNet net = creator.createNet(new Scanner(testData));

        net.getComputer(1).setInfection();
        Queue<Integer> toInfect = new ConcurrentLinkedQueue<Integer>();
        toInfect.add(2);
        toInfect.add(3);

        Process.stepOfInfection(toInfect, net, rnd);

        assertFalse(net.getComputer(4).isInfected());
        assertFalse(net.getComputer(5).isInfected());
        assertFalse(net.getComputer(6).isInfected());
    }

    @Test
    public void someStepOfInfectionTest() throws IncorrectDataException {
        Virus rnd = new RandomInfectingVirus();
        NetsCreator creator = new NetsCreator();
        ComputersNet net = creator.createNet(new Scanner(testData));

        net.getComputer(1).setInfection();
        net.getComputer(3).setInfection();
        net.getComputer(5).setInfection();
        Queue<Integer> toInfect = new ConcurrentLinkedQueue<Integer>();
        toInfect.add(2);
        toInfect.add(4);

        Process.stepOfInfection(toInfect, net, rnd);
        boolean nextState = (net.getComputer(2).isInfected() && !toInfect.contains(2))
                || (!net.getComputer(2).isInfected() && toInfect.contains(2));
        assertTrue(nextState);
    }

    @Test
    public void additionConnectedWithInfectedTest() throws IncorrectDataException {
        NetsCreator creator = new NetsCreator();
        ComputersNet net = creator.createNet(new Scanner(testData));
        net.getComputer(3).setInfection();
        Queue<Integer> toInfect = new ConcurrentLinkedQueue<Integer>();
        Process.addConnectedWithInfected(3, net, toInfect);
        assertEquals(3, toInfect.size());
        assertTrue(toInfect.contains(1));
        assertTrue(toInfect.contains(4));
        assertTrue(toInfect.contains(5));
    }

    @Test
    public void sequenceOfInfectionTest() throws IncorrectDataException {
        Virus virus = new HardTestVirus();
        NetsCreator creator = new NetsCreator();
        ComputersNet net = creator.createNet(new Scanner(testData));

        Integer numberOfFirstInfected = 3;
        net.getComputer(numberOfFirstInfected).setInfection();
        Queue<Integer> toInfect = new ConcurrentLinkedQueue<Integer>();
        Process.addConnectedWithInfected(numberOfFirstInfected, net, toInfect);
        assertTrue(toInfect.size() == 3);

        Process.stepOfInfection(toInfect, net, virus);
        assertTrue(net.getComputer(1).isInfected());
        assertTrue(toInfect.size() == 3);

        Process.stepOfInfection(toInfect, net, virus);
        assertTrue(net.getComputer(4).isInfected());
        assertTrue(toInfect.size() == 2);

        Process.stepOfInfection(toInfect, net, virus);
        assertTrue(net.getComputer(5).isInfected());
        assertTrue(toInfect.size() == 1);

        Process.stepOfInfection(toInfect, net, virus);
        assertTrue(net.getComputer(2).isInfected());
        assertTrue(toInfect.size() == 0);

        assertFalse(net.getComputer(6).isInfected());
    }

    @Test
    public void otherSequenceOfInfectionTest() throws IncorrectDataException {
        Virus virus = new HardTestVirus();
        NetsCreator creator = new NetsCreator();
        ComputersNet net = creator.createNet(new Scanner(testData));

        Integer numberOfFirstInfected = 6;
        net.getComputer(numberOfFirstInfected).setInfection();
        Queue<Integer> toInfect = new ConcurrentLinkedQueue<Integer>();

        Process.addConnectedWithInfected(numberOfFirstInfected, net, toInfect);
        assertTrue(toInfect.size() == 3);

        Process.stepOfInfection(toInfect, net, virus);
        assertTrue(net.getComputer(1).isInfected());
        assertTrue(toInfect.size() == 3);

        Process.stepOfInfection(toInfect, net, virus);
        assertTrue(net.getComputer(3).isInfected());
        assertTrue(toInfect.size() == 3);

        Process.stepOfInfection(toInfect, net, virus);
        assertTrue(net.getComputer(5).isInfected());
        assertTrue(toInfect.size() == 2);

        Process.stepOfInfection(toInfect, net, virus);
        assertTrue(net.getComputer(2).isInfected());
        assertTrue(toInfect.size() == 1);

        Process.stepOfInfection(toInfect, net, virus);
        assertTrue(net.getComputer(4).isInfected());
        assertTrue(toInfect.size() == 0);
    }


    /**
     * Virus, which always infects computer
     */
    private class HardTestVirus implements Virus{
        public boolean infect(double probabilityOfInfection) {
            return true;
        }
    }
}