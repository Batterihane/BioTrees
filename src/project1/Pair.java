package project1;

/**
 * Created by Thomas on 14-11-2015.
 */
public class Pair<T, S> {
    private final T left;
    private final S right;

    public Pair(T left, S right){
        this.left = left;
        this.right = right;
    }

    public T getLeft() {
        return left;
    }

    public S getRight() {
        return right;
    }
}
