public class Interval {
    public int low;
    public int high;

    public Interval (int l, int h) {
        if (l > h) {
            throw new IllegalArgumentException("the interval's low value cannot be higher than the interval's high value!");
        }
        this.low = l;
        this.high = h;
    }
}