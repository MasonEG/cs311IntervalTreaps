public class IntervalDatabase {

    public IntervalNode root;

    public Interval[] intervalNodes;



    public Interval intervalSearch (Interval i) {
        IntervalNode curr = this.root;
        while(curr != null && curr.left.imax >= i.low){
            if(curr.left != null && curr.left.imax >= i.low)
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
