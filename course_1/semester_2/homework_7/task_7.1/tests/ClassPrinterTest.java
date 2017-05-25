import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ClassPrinterTest {
    /**
     * check correct work of method, which dets information about class
     */
    @Test
    public void printTest(){
        assertEquals(EXPECTED, ClassPrinter.getInformationAboutClass(ClassForTest.class));
    }

    /**
     * interface for class for test
     */
    interface PrinterForTest{
        void print();
    }

    /**
     * class to check correct printing
     */
    private class ClassForTest implements PrinterForTest{
       protected int value;
       private String line;

       ClassForTest(String line) {
           this.line = line;
       }

       public void setValue(int value) {
           this.value = value;
       }

       public int getValue() {
           return value;
       }

       public void appendLine(String appendix) {
           line.concat(appendix);
       }

        @Override
        public void print() {
            System.out.print(line);
        }
    }

    private final String EXPECTED = "private  class ClassForTest extends Object  implements PrinterForTest {\n" +
            "    protected int value;\n" +
            "    private String line;\n" +
            " \n" +
            "    ClassForTest(String);\n" +
            " \n" +
            "    public int getValue();\n" +
            "    public void print();\n" +
            "    public void setValue(int);\n" +
            "    public void appendLine(String);\n" +
            "}\n";
}