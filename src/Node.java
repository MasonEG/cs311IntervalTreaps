public class Node {
    public Interval interval;
    public Node left;
    public Node right;
    public Node parent;
    public int priority;
    public int iMax;

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

    public int getMax(){
        return 0;
    }

    public int getPriority(){
        return priority;
    }
}
