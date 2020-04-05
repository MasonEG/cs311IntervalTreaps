public class IntervalTreap {

    private Node root;
    private int size;
    private int height;

    public IntervalTreap() {
        size = 0;
        height = (int) (Math.ceil(Math.log(n) / Math.log(2));
        root = null;
    }

    //TODO
    public String toString() { //TODO
        Node cur = root;
        String ret = "";
        return ret;
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

    int getMid(int s, int e) {
        return s + (e - s) / 2;
    }

    public void rotate(Node parrNode, Node currNode, Node SuccNode) {
        if (parrNode.getLeft() == currNode)



            //update height and imax

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
