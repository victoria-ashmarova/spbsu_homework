public class Sum extends Action {
    Sum(){
        this.action = "+";
    }

    @Override
    public int getValue() {
        return leftSon.getValue() + rightSon.getValue();
    }
}
