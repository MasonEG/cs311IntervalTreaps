public class IntervalTreap {

    public Node root;
    public int size = 0;
    public int height = 0;

    public IntervalTreap(){

    }

    public Node getRoot(){

        return root;
    }

    public int getSize(){

        return size;
    }

    public int getHeight(){

        return height;
    }

    //adds node z, whose interv attribute references an
    //Interval object, to the interval treap. This operation must maintain the required interval
    //treap properties. The expected running time of this method should be O(log n) on an n-node
    //interval treap.
    public void intervalInsert( Node z){

    }

    //removes node z from the interval treap. This operation
    //must maintain the required interval treap properties. The expected running time of this
    //method should be O(log n) on an n-node interval treap.
    public void intervalDelete(Node z){

    }

    //returns a reference to a node x in the interval
    //treap such that x.interv overlaps interval i, or null if no such element is in the treap.
    //This method must not modify the interval treap. The expected running time of this method
    //should be O(log n) on an n-node interval treap.
    public Node intervalSearch (Interval i) {
        Node curr = this.root;
        while(curr != null && !i.overlaps(curr.interval)){
            if (curr.left != null && curr.left.iMax >= i.low)
                curr = curr.left;
            else
                curr = curr.left;
        }
        return curr;
    }



}
