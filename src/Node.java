public class Node {
    private Interval interval;
    private Node left;
    private Node right;
    private Node parent;
    private int priority;
    private int iMax;

    public Node (Interval i){
        interval = i;
    }

    public Node getParent(){
        return parent;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public Interval getInterval(){
        return interval;
    }

    public int getIMax(){
        int ret = interval.getHigh();

        if (left != null) {
            ret = Integer.max(ret, left.getIMax());
        }
        if (right != null) {
            ret = Integer.max(ret, right.getIMax());
        }
        return ret;
    }

    public int getPriority(){
        return priority;
    }
}
