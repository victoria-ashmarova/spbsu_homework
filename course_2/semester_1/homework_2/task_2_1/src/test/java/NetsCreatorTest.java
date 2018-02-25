import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Tests of nets creation
 */
public class NetsCreatorTest {
    @Test(expected = IncorrectDataException.class)
    public void incorrectOSDataTest() throws IncorrectDataException {
        String incorrectData = "2 2 2\n1 WIDOWS 2\n2 LINUX 1\n";
        NetsCreator creator = new NetsCreator();
        Scanner scannerData = new Scanner(incorrectData);
        ComputersNet net = creator.createNet(scannerData);
    }

    @Test(expected = IncorrectDataException.class)
    public void incorrectAmountOfComputersTest() throws IncorrectDataException {
        String incorrectData = "4 2 2\n1 WINDOWS 2 3\n2 LINUX 1\n3 MAC_O_S 1";
        NetsCreator creator = new NetsCreator();
        Scanner scannerData = new Scanner(incorrectData);
        ComputersNet net = creator.createNet(scannerData);
    }

    @Test(expected = IncorrectDataException.class)
    public void incorrectComputersNumberTest() throws IncorrectDataException {
        String incorrectData = "3 2 2\n1 WINDOWS 2 3 9\n2 LINUX 1\n3 MAC_O_S 1 42";
        NetsCreator creator = new NetsCreator();
        Scanner scannerData = new Scanner(incorrectData);
        ComputersNet net = creator.createNet(scannerData);
    }

    @Test(expected = IncorrectDataException.class)
    public void sameNumbersTest() throws IncorrectDataException {
        String incorrectData = "2 2 2\n1 WINDOWS 2\n2 LINUX 1\n2 MAC_O_S 1";
        NetsCreator creator = new NetsCreator();
        Scanner scannerData = new Scanner(incorrectData);
        ComputersNet net = creator.createNet(scannerData);
    }

    @Test(expected = IncorrectDataException.class)
    public void connectingByItselfTest() throws IncorrectDataException {
        String incorrectData = "3 2 2\n1 WINDOWS 2\n2 LINUX 1 2\n";
        NetsCreator creator = new NetsCreator();
        Scanner scannerData = new Scanner(incorrectData);
        ComputersNet net = creator.createNet(scannerData);
    }

    @Test
    public void correctNetCreationTest() throws IncorrectDataException, IncorrectComputerNumberException {
        String correctData = "6 2 3\n1 WINDOWS 3 2\n2 LINUX 1\n3 LINUX 1 4 5\n4 MAC_O_S 3 1\n5 LINUX 4\n6 LINUX 1 3 5\n";
        NetsCreator creator = new NetsCreator();
        Scanner scannerData = new Scanner(correctData);
        ComputersNet net = creator.createNet(scannerData);
        assertEquals(6, net.getSize());
        assertTrue(net.firstIsConnectedWithSecond(1, 3));
        assertTrue(net.firstIsConnectedWithSecond(1, 2));
        assertTrue(net.firstIsConnectedWithSecond(2, 1));
        assertTrue(net.firstIsConnectedWithSecond(6, 5));
        assertFalse(net.firstIsConnectedWithSecond(5, 6));
    }
}