import ashmarova.task_2_7_1.ClassPrinter;
import org.junit.Test;

import java.util.TreeSet;

import static org.junit.Assert.*;

public class ClassPrinterTest {
    /**
     * checks correctness of getting modifiers
     */
    @Test
    public void checkCorrectModifiersTest() {
        TreeSet<String> expectedModifiers = new TreeSet<>();
        expectedModifiers.add("private");
        expectedModifiers.add("final");
        TreeSet<String> classModifiers = ClassPrinter.getModifiers(SomeClass.class.getModifiers());
        assertTrue(expectedModifiers.containsAll(classModifiers) && classModifiers.containsAll(expectedModifiers));
    }

    /**
     * checks correctness of getting methods
     */
    @Test
    public void checkCorrectMethodsTest() {
        TreeSet<String> expectedMethod = new TreeSet<>();
        expectedMethod.add("void setValue");
        expectedMethod.add("void plusTwo");
        expectedMethod.add("String concatString");
        TreeSet<String> methods = ClassPrinter.getMethods(SomeClass.class);
        assertTrue(methods.equals(expectedMethod));
    }

    /**
     * checks correctness of getting fields
     */
    @Test
    public void checkCorrectFieldsTest() {
        TreeSet<String> fields = ClassPrinter.getFields(SomeClass.class);
        TreeSet<String> expectedFields = new TreeSet<>();
        expectedFields.add("int");
        expectedFields.add("String");
        assertTrue(fields.equals(expectedFields));
    }

    /**
     * checks correctness of getting interfaces
     */
    @Test
    public void checkInterfacesTest() {
        TreeSet<String> interfaces = ClassPrinter.getInterfaces(SomeClass.class);
        assertEquals(0, interfaces.size());
    }

    /**
     * class for test
     */
    private final class SomeClass {
        private int value;
        public String line;

        public SomeClass(int value, String line) {
            this.value = value;
            this.line = line;
        }

        public void setValue(int value) {
            this.value = value;
        }

        protected void plusTwo() throws Exception {
            this.value += 2;
            if (value == 1) {
                throw new Exception();
            }
        }

        public String concatString(String toAdd) {
            return line.concat(toAdd);
        }
    }
}