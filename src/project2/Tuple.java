package project2;

/**
 * Created by Thomas on 14-11-2015.
 */
public class Tuple<T, S> {
    private final T left;
    private final S right;

    public Tuple(T left, S right){
        this.left = left;
        this.right = right;
    }

    public T getLeft() {
        return left;
    }

    public S getRight() {
        return right;
    }

    public boolean compareWithInterval(Tuple<T, S> other){
        return left.equals(other.getLeft()) && right.equals(other.getRight());
    }
}
