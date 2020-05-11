/**
 * @author Mason Gil
 * @author Andrei Baechle
 */
public class Interval {
    private int low;
    private int high;

    public Interval(int l, int h) throws IllegalArgumentException {
        if (l > h) {
            throw new IllegalArgumentException("the interval's low value cannot be higher than the interval's high value!");
        }
        this.low = l;
        this.high = h;
    }

    public boolean overlaps(Interval i) {
        return (this.low < i.high && i.low < this.high);
    }

    public int getLow() {
        return low;
    }

    public int getHigh() {
        return high;
    }

}