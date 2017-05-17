abstract public class Action extends AbstractNode {
    protected String action;
    protected AbstractNode rightSon = null;
    protected AbstractNode leftSon = null;

    public void print(){
        System.out.print(" " + action + " ");
    }

    public void setLeftSon(AbstractNode futureLeftSon){
        leftSon = futureLeftSon;
    }

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
        System.out.print("( ");
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
