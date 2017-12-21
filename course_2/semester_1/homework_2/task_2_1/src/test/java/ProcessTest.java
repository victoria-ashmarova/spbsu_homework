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
    public void stepOfInfectionTest() throws IncorrectDataException {
        Random rnd = new Random();
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
}