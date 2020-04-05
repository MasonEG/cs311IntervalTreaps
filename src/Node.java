import java.util.Random;

public class Node {
    private Interval interval;
    private Node left;
    private Node right;
    private Node parent;
    private int priority;

    /*
    Constructor that takes an Interval object i as its parameter. The
    constructor must generate a priority for the node.
     */
    public Node(Interval i) {
        interval = i;
        Random r = new Random(); //generate a random priority
        priority = r.nextInt();
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node n) {
        parent = n;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node n) {
         left = n;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node n) {
        right = n;
    }

    public Interval getInterval() {
        return interval;
    }

    public int getIMax() {
        int ret = interval.getHigh();

        if (left != null) {
            ret = Integer.max(ret, left.getIMax());
        }
        if (right != null) {
            ret = Integer.max(ret, right.getIMax());
        }
        return ret;
    }

    public void setIMax() {

    }

    public int getPriority() {
        return priority;
    }
}
