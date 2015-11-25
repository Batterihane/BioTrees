package project2;

public class IntPair {
    private final int lower;
    private final int higher;

    public IntPair(int left, int right){
        if(left <= right){
            this.lower = left;
            this.higher = right;
        }
        else {
            this.lower = right;
            this.higher = left;
        }
    }

    public int getLower() {
        return lower;
    }

    public int getHigher() {
        return higher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IntPair intPair = (IntPair) o;

        if (lower != intPair.lower) return false;
        return higher == intPair.higher;

    }

    @Override
    public int hashCode() {
        return 19319 * lower + higher;
    }
}
