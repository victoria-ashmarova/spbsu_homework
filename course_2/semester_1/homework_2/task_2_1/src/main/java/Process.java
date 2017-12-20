/**
 * Created by Виктория on 13.12.2017.
 */
public class Process {
    public static void main(String args[]) {
        NetsCreator creator = new NetsCreator();
        try {
            ComputersNet net = creator.createNet();

        } catch (NoNetException e) {
            e.message();
        }
    }
}
