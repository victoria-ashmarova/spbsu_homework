/**
 * class, which contains method of work with node
 */
abstract public class AbstractNode implements Node{
    /**
     * checks if there is place to add left son
     * @return true if there is
     */
    abstract public boolean leftSonIsEmpty();

    /**
     * hecks if there is place to add right son
     * @return true if there is
     */
    abstract public boolean rightSonIsEmpty();
}
