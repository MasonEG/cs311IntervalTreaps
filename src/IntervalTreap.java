public class IntervalTreap {

    private Node root;
    private int size;
    private int height;

    public IntervalTreap() {
        size = 0;
        height = (int) (Math.ceil(Math.log(n) / Math.log(2));
        root = null;
    }

    public String toString() { //TODO
        return toString(root);
    }

    public String toString(Node n) {
       String ret =  "([" + n.getInterval().getLow() + ", " + n.getInterval().getHigh() + "] P: " + n.getPriority();
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
        return height;
    }

    public void addToHeight(int i) {
        height += i;
    }

    int getMid(int s, int e) {
        return s + (e - s) / 2;
    }

    public static void rotate(Node parentNode, Node currNode) { //bases off priority
        if (parentNode.getLeft() == currNode) { // rotate left
            currNode.setParent(parentNode.getParent());
            parentNode.setParent(currNode);
            parentNode.setRight(currNode.getLeft());
            currNode.setLeft(parentNode);
        } else { // rotate right
            currNode.setParent(parentNode.getParent());
            parentNode.setParent(currNode);
            parentNode.setLeft(currNode.getRight());
            currNode.setRight(parentNode);
        }


        //TODO update height

    }

    public static void rebalance(Node p, Node c) {
        while (p.getPriority() > c.getPriority()) {
            rotate (p, c);
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
        Node temp = root;

        if (temp == null) {
            setRoot(z);
            height++;
            size++;
        }


        while (true) {
            if (z.getInterval().getLow() >= temp.getInterval().getLow() && temp.getRight() != null)
                temp = temp.getRight();

            else if (z.getInterval().getLow() < temp.getInterval().getLow() && temp.getRight() != null)
                temp = temp.getLeft();

            else if (z.getInterval().getLow() >= temp.getInterval().getLow() && temp.getRight() == null) {
                temp.setRight(z);
                z.setParent(temp);
                break;

            } else if (z.getInterval().getLow() > temp.getInterval().getLow() && temp.getLeft() == null) {
                temp.setLeft(z);
                z.setParent(temp);
                break;

            }
        }

        rebalance (z, temp);
    }

    /*
        removes node z from the interval treap. This operation
        must maintain the required interval treap properties. The expected running time of this
        method should be O(log n) on an n-node interval treap.
     */
    public void intervalDelete(Node z) {


        //if the node youre deleting has two children you will have to rotate
        Node temp = root;

        if (temp == null){
            return;
        }
        // L/R subtree
        if (z.getInterval().getLow() < temp.getInterval().getLow())
            intervalDelete(temp.getLeft());

        else if (z.getInterval().getLow() > temp.getInterval().getLow())
            intervalDelete(temp.getRight());

        //node found
        else{
            // if node has no childern
            if (temp.getLeft() == null && temp.getRight() == null){
                if(temp.getParent().getLeft() == z)
                    temp.getParent().setLeft(null);
                else
                    temp.getParent().setRight(null);
                temp = null;
            }
            //if node has 2 children
            else if (temp.getLeft() != null && temp.getRight() != null){

            }

        }
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
