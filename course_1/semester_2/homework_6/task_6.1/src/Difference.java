/**
 * Class, which describes operation of difference
 */
public class Difference extends Action {
    Difference(){
        this.action = "-";
    }

    @Override
    public int getValue() {
        return leftSon.getValue() - rightSon.getValue();
    }
}
