public class IntervalTreap {

    private Node root;
    private int size;
    private int height;

    public IntervalTreap() {
        size = 0;
        height = 0;
        root = null;
    }

    public String toString() { //TODO
        Node cur = root;
        String ret = ""
    }

    public Node getRoot() {
        return root;
    }

    public int getSize() {
        return size;
    }

    public int getHeight() {
        return height;
    }

    /*
        adds node z, whose interv attribute references an
        Interval object, to the interval treap. This operation must maintain the required interval
        treap properties. The expected running time of this method should be O(log n) on an n-node
        interval treap.
     */
    public void intervalInsert(Node z) {

    }

    /*
        removes node z from the interval treap. This operation
        must maintain the required interval treap properties. The expected running time of this
        method should be O(log n) on an n-node interval treap.
     */
    public void intervalDelete(Node z) {

    }

    /*
        returns a reference to a node x in the interval
        treap such that x.interv overlaps interval i, or null if no such element is in the treap.
        This method must not modify the interval treap. The expected running time of this method
        should be O(log n) on an n-node interval treap.
     */
    public Node intervalSearch(Interval i) {
        Node curr = this.root;
        while (curr != null && !i.overlaps(curr.getInterval())) {
            if (curr.getLeft() != null && curr.getLeft().getIMax() >= i.getLow())
                curr = curr.getLeft();
            else
                curr = curr.getLeft();
        }
        return curr;
    }


}
