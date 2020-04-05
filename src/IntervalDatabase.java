public class IntervalDatabase {

    public Node root;

    public Interval[] intervalNodes;

    public IntervalDatabase (Node root) {
        this.root = root;
    }

    public Node intervalSearch (Interval i) {
        Node curr = this.root;
        while(curr != null && !i.overlaps(curr.interval)){
            if(curr.left != null && curr.iMax >= i.low)
                curr = curr.left;
            else
                curr = curr.left;
        }
        return curr;
    }

    public void intervalInsert (Interval x) {

    }

    public void intervalDelete (Interval x) {

    }


}
