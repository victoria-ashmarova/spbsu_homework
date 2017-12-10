import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Tree contains tree root and methods of addition, removal and search data in tree.
 */
public class Tree<T extends Comparable> implements Iterable<T>{
    private TreeNode<T> root;
    private int size;

    public Tree() {
        this.size = 0;
        this.root = null;
    }

    public int getSize() {
        return this.size;
    }

    /** search if data is in tree*/
    public boolean search(T data) {
        if (root == null) {
            return false;
        }
        TreeNode<T> parent = findParent(data, root);
        if (parent == null) {
            return data.compareTo(root.getData()) == 0;
        }


        TreeNode<T> node;
        if (data.compareTo(parent.getData()) < 0) {
            node = parent.getLeftChild();
        } else {
            node = parent.getRightChild();
        }

        if (node == null) {
            return false;
        }

        return data.compareTo(node.getData()) == 0;

    }

    /** addition to tree*/
    public void add(T data) {
        TreeNode<T> parent = findParent(data, root);

        if (parent == null) {
            if (root == null) {
                root = new TreeNode<T>(data);
                size++;
            }
        } else {
            if (data.compareTo(parent.getData()) < 0 && parent.getLeftChild() == null) {
                parent.setLeftChild(new TreeNode<T>(data));
                size++;
                return;
            }

            if (data.compareTo(parent.getData()) > 0 && parent.getRightChild() == null) {
                parent.setRightChild(new TreeNode<T>(data));
                size++;
            }
        }
    }

    /** removal data from tree*/
    public void remove(T data) {
        TreeNode<T> parent = findParent(data, root);

        if (parent == null) {
            root = refreshedNode(root);
        } else {
            boolean isLeftChild = data.compareTo(parent.getData()) < 0;
            TreeNode<T> deleted = isLeftChild ? parent.getLeftChild() : parent.getRightChild();

            if (isLeftChild) {
                parent.setLeftChild(refreshedNode(deleted));
            } else {
                parent.setRightChild(refreshedNode(deleted));
            }
        }

    }

    /** gets node, which must will be on place of deleted node*/
    private TreeNode<T> refreshedNode(TreeNode<T> toRefresh) {
        if (toRefresh == null) {
            return null;
        }

        size--;

        TreeNode<T> leftSon = toRefresh.getLeftChild();
        TreeNode<T> rightSon = toRefresh.getRightChild();

        if (leftSon != null) {
            if (rightSon == null) {
                return leftSon;
            }

            TreeNode<T> leaf = findParent(rightSon.getData(), leftSon);
            leaf.setRightChild(rightSon);
            return leftSon;
        } else {
            return rightSon;
        }
    }

    /** finding node, which is parent of node with current data or can be it*/
    private TreeNode<T> findParent(T data, TreeNode<T> root) {
        if (root == null) {
            return null;
        }

        if (root.getData().equals(data)) {
            return null;
        }

        TreeNode<T> parent = root;
        boolean stopCondition = false;
        while (!stopCondition) {
            TreeNode<T> nextParent = (data.compareTo(parent.getData()) < 0) ? parent.getLeftChild() : parent.getRightChild();

            if (nextParent == null) {
                stopCondition = true;
            } else {

                if (data.compareTo(nextParent.getData()) == 0) {
                    stopCondition = true;
                } else {
                    parent = nextParent;
                }
            }
        }
        return parent;
    }

    /** creation iterator*/
    public Iterator<T> iterator() {
        return new TreeIterator();
    }

    /** Implements iterator for tree*/
    private class TreeIterator implements Iterator<T> {
        private TreeNode<T> current;
        private TreeNode<T> next;
        private Stack<TreeNode<T>> nodeStack;

        public TreeIterator() {
            this.current = null;
            this.next = root;
            this.nodeStack = new Stack<TreeNode<T>>();

            if (next != null) {
                while (next.getLeftChild() != null) {
                    nodeStack.push(next);
                    next = next.getLeftChild();
                }
            }
        }

        //** checks if there is next element in tree*/
        public boolean hasNext() {
            return next != null;
        }

        //** gets next element from tree*/
        public T next() throws NoSuchElementException {
            if (next == null) {
                throw new NoSuchElementException();
            }
            current = next;

            if (nodeStack.isEmpty()) {
                if (next.getRightChild() == null) {
                    next = null;
                }
            } else {
                next = nodeStack.pop();
            }

            try {
                if (next.getRightChild() != null) {
                    TreeNode<T> temp = next.getRightChild();
                    while (temp != null) {
                        nodeStack.push(temp);
                        temp = temp.getLeftChild();
                    }
                }
            } catch (NullPointerException e) {
                return current.getData();
            }

            return current.getData();
        }

        //** deletes current element of tree*/
        public void remove() throws IllegalStateException {
            if (current == null) {
                throw new IllegalStateException();
            }
            if (current.getRightChild() != null) {
                nodeStack.push(current.getRightChild());
            }
            Tree.this.remove(current.getData());
        }
    }
}
