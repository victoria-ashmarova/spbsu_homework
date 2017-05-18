/**
 * Class, which contains methods and parameters for operations
 * of sum, multiplication, difference and quotient
 */
abstract public class Action extends AbstractNode {
    protected String action;
    protected AbstractNode rightSon = null;
    protected AbstractNode leftSon = null;

    @Override
    public void print(){
        System.out.print(action);
    }

    /**
     * adds pointer to left son
     * @param futureLeftSon node for pointer for left son
     */
    public void setLeftSon(AbstractNode futureLeftSon){
        leftSon = futureLeftSon;
    }

    /**
     * adds pointer to right son
     * @param futureRightSon node for pointer for right son
     */
    public void setRightSon(AbstractNode futureRightSon){
        rightSon = futureRightSon;
    }

    @Override
    public boolean leftSonIsEmpty(){
        return leftSon == null;
    }

    @Override
    public boolean rightSonIsEmpty(){
        return rightSon == null;
    }

    @Override
    public void printAllChildren(){
        System.out.print("(");
        this.print();
        if (!leftSonIsEmpty()){
            this.leftSon.printAllChildren();
        }
        if (!rightSonIsEmpty()){
            this.rightSon.printAllChildren();
        }
        System.out.print(")");
    }
}
