public class IntervalTreap {

    private Node root;
    private int size;

    public IntervalTreap() {
        size = 0;
        root = null;
    }

    public String toString() { //TODO
        return toString(root);
    }

    public String toString(Node n) {
        String ret = "([" + n.getInterval().getLow() + ", " + n.getInterval().getHigh() + "] P: " + n.getPriority();
        if (n.getLeft() != null) {
            ret += " L: " + toString(n.getLeft());
        }
        if (n.getRight() != null) {
            ret += " R: " + toString(n.getRight());
        }
        return ret + ")";
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node n) {
        root = n;
    }

    public int getSize() {
        return size;
    }

    public void addToSize(int i) {
        size += i;
    }

    public int getHeight() {
        return getHeightRec(root);
    }

    public int getHeightRec(Node n) {
       if (n == null) return 0;
       int lHeight = 0;
       int rHeight = 0;

       if (n.getLeft() != null) lHeight = getHeightRec(n.getLeft());
       if (n.getRight() != null) rHeight = getHeightRec(n.getRight());

       if (lHeight > rHeight) return lHeight + 1;
       else return rHeight + 1;
    }

    int getMid(int s, int e) {
        return s + (e - s) / 2;
    }

    public static void rotate(Node parentNode, Node childNode) { //bases off priority
        Node parentParent = parentNode.getParent();
        if (parentParent.getRight() == parentNode) parentParent.setRight(childNode);
        else parentParent.setLeft(childNode);
        if (parentNode.getRight() == childNode) { // rotate left
            childNode.setParent(parentNode.getParent());
            parentNode.setParent(childNode);
            parentNode.setRight(childNode.getLeft());
            childNode.setLeft(parentNode);
        } else { // rotate right
            childNode.setParent(parentNode.getParent());
            parentNode.setParent(childNode);
            parentNode.setLeft(childNode.getRight());
            childNode.setRight(parentNode);
        }
    }

    public static void rebalance(Node p, Node c) {
        while (p.getPriority() > c.getPriority()) {
            rotate(p, c);
            if (c.getParent() == null) return;
            p = c.getParent();
        }
    }


    /*
        adds node z, whose interv attribute references an
        Interval object, to the interval treap. This operation must maintain the required interval
        treap properties. The expected running time of this method should be O(log n) on an n-node
        interval treap.
     */
    public void intervalInsert(Node z) {
        if (root == null) {
            setRoot(z);
            size++;
            return;
        }

        Node temp = root;
        while (true) {

            if (z.getInterval().getLow() >= temp.getInterval().getLow() && temp.getRight() != null)
                temp = temp.getRight();

            else if (z.getInterval().getLow() < temp.getInterval().getLow() && temp.getLeft() != null)
                temp = temp.getLeft();

            else if (z.getInterval().getLow() >= temp.getInterval().getLow() && temp.getRight() == null) {
                temp.setRight(z);
                z.setParent(temp);
                break;

            } else if (z.getInterval().getLow() < temp.getInterval().getLow() && temp.getLeft() == null) {
                temp.setLeft(z);
                z.setParent(temp);
                break;
            }
        }
        size++;
        rebalance(temp, z);
    }

    /*
        removes node z from the interval treap. This operation
        must maintain the required interval treap properties. The expected running time of this
        method should be O(log n) on an n-node interval treap.
     */
    public void intervalDelete(Node z) {

        if (z == null) {
            return;
        }
        if (z.getLeft() == null ) {
            if (z.getParent().getLeft() == z)
                z.getParent().setLeft(z.getRight());
            else
                z.getParent().setRight(z.getRight());
            z = z.getRight();
        }
        else if (z.getLeft() != null && z.getRight() == null) {
            if (z.getParent().getLeft() == z)
                z.getParent().setLeft(z.getLeft());
            else
                z.getParent().setRight(z.getLeft());
            z = z.getLeft();
        }
        else {
            Node succ = z.getSuccessor();
            succ.setParent(z.getParent());
            if (z.getParent().getLeft() == z)
                z.getParent().setLeft(succ);
            else
                z.getParent().setRight(succ);
            succ.setLeft(z.getLeft());
            z.getLeft().setParent(succ);
            z = succ;
        }
        if (z != null) rebalance(z.getParent(), z);
    }

    /*
        returns a reference to a node x in the interval
        treap such that x.interv overlaps interval i, or null if no such element is in the treap.
        This method must not modify the interval treap. The expected running time of this method
        should be O(log n) on an n-node interval treap.
     */
        public Node intervalSearch (Interval i){
            Node curr = root;
            while (curr != null && !i.overlaps(curr.getInterval())) {
                if (curr.getLeft() != null && curr.getLeft().getIMax() >= i.getLow())
                    curr = curr.getLeft();
                else
                    curr = curr.getRight();
            }
            return curr;
        }
        /*
        Returns a reference to a Node object x
        in the treap such that x.interv.low = i.low and x.interv.high = i.high, or null if
        no such node exists. The expected running time of this method should be O(log n) on an
        n-node interval treap.
         */
        public Node intervalSearchExactly (Interval i){
            Node curr = root;
            if (curr.getInterval().getLow() > i.getLow()){
                if (curr.getLeft() != null)
                    curr = curr.getLeft();
                else
                    return null;
            }
            else if( curr.getInterval().getLow() <= i.getLow()){
                if (curr.getInterval().getHigh() == i.getHigh())
                    return curr;
                else{
                    if (curr.getRight() != null)
                        curr = curr.getRight();
                    else
                        return null;
                }
            }
            return curr;
        }
    }
