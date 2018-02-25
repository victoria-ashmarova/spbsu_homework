/**
 * Tree Node contains value and pointers to children' nodes
 */
public class TreeNode<T extends Comparable> {
    private final T data;
    private TreeNode<T> leftChild;
    private TreeNode<T> rightChild;

    public TreeNode(T data) {
        this.data = data;
        this.rightChild = null;
        this.leftChild = null;
    }

    public T getData() {
        return this.data;
    }

    public void setLeftChild(TreeNode<T> leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(TreeNode<T> rightChild) {
        this.rightChild = rightChild;
    }

    public TreeNode<T> getLeftChild() {
        return this.leftChild;
    }

    public TreeNode<T> getRightChild() {
        return this.rightChild;
    }
}
