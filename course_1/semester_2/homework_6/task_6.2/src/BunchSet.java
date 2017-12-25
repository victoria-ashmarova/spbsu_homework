import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * Class implements set with ArrayList.
 * @param <T> is type of data in set
 */
public class BunchSet<T> implements Set<T> {
    /**
     * is store of set's elements.
     */
    private ArrayList<T> bunch = new ArrayList<>();

    @Override
    public int size() {
        return bunch.size();
    }

    @Override
    public boolean isEmpty() {
        return bunch.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return bunch.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return bunch.iterator();
    }

    @Override
    public Object[] toArray() {
        return bunch.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return this.bunch.toArray(a);
    }

    @Override
    public boolean add(T t) {
        if (contains(t)){
            return false;
        } else {
            return bunch.add(t);
        }
    }

    @Override
    public boolean remove(Object o) {
        if (!contains(o)){
            return false;
        } else {
            return bunch.remove(o);
        }
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return bunch.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean isChanged = false;
        for (T current : c){
            if (add(current)){
                isChanged = true;
            }
        }
        return isChanged;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return bunch.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return bunch.removeAll(c);
    }

    @Override
    public void clear() {
        bunch.clear();
    }
}
