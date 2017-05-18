/**
 * Class, which describes operation of multiplication
 */
public class Multiplication extends Action{
    Multiplication(){
        this.action = "*";
    }

    @Override
    public int getValue() {
        return leftSon.getValue() * rightSon.getValue();
    }
}
