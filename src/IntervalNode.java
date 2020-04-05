public class IntervalNode {
    public int low;
    public int high;
    public int priority;
    public IntervalNode lChild;
    public IntervalNode rChild;

    public IntervalNode (int l, int h) {
        if (l > h) {
            throw new IllegalArgumentException("the interval's low value cannot be higher than the interval's high value!");
        }
        this.low = l;
        this.high = h;
    }
}