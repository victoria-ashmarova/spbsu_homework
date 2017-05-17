public class Value extends AbstractNode {
    Value(int value){
        this.value = value;
    }

    private int value;

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public boolean leftSonIsEmpty() {
        return false;
    }

    @Override
    public boolean rightSonIsEmpty() {
        return false;
    }

    @Override
    public void printAllChildren() {
        this.print();
    }

    @Override
    public void print() {
        System.out.print(value + " ");
    }
}
