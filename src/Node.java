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
        return iMax;
    }

    public int getPriority(){
        return priority;
    }
}
