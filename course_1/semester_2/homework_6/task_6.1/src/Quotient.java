public class Quotient extends Action {
    Quotient(){
        this.action = "/";
    }

    @Override
    public int getValue() {
        return leftSon.getValue() / rightSon.getValue();
    }
}
