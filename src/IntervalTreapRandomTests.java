import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class IntervalTreapRandomTests {
    IntervalTreap hugeIntervalTreap;
    ArrayList<Node> nodesInTree = new ArrayList<>();
    Interval notUsedInterval;
    IntervalTreap treapWithoutInterval;
    public static int TREAP_SIZE = 10000; // Number of nodes in hugeIntervalTreap, also affects numbers of nodes in TreapWithoutInterval, but isn't 1:1
    public static int FREQUENCY = 1; // 1 is highest frequency, chose larger ints (less than TREAP_SIZE) to make less frequent (affects search and delete tests)

    // Assume's that height was done recursively and is accessible on each node with a method called getHeightOfSubtree()
    private void validateHeight(Node n) throws Exception {
        int expected;
        if (n.getLeft() == null && n.getRight() == null) {
            expected = 0;
        } else if (n.getLeft() == null) {
            expected = n.getRight().getHeightOfSubtree() + 1;
        } else if (n.getRight() == null) {
            expected = n.getLeft().getHeightOfSubtree() + 1;
        } else {
            expected = Math.max(n.getLeft().getHeightOfSubtree(), n.getRight().getHeightOfSubtree()) + 1;
        }

        int actual = n.getHeightOfSubtree();
        if (actual != expected) {
            throw new Exception(new Error("Height does not have the expected value"));
        }
    }

    private void validateIMax(Node n) throws Exception {
        int expected;
        if (n.getLeft() == null && n.getRight() == null) {
            expected = n.getInterv().getHigh();
        } else if (n.getLeft() == null) {
            expected = Math.max(n.getInterv().getHigh(), n.getRight().getIMax());
        } else if (n.getRight() == null) {
            expected = Math.max(n.getInterv().getHigh(), n.getLeft().getIMax());
        } else {
            expected = Math.max(n.getInterv().getHigh(), n.getLeft().getIMax());
            expected = Math.max(expected, n.getRight().getIMax());
        }

        // Validate iMax
        int actual = n.getIMax();
        if (actual != expected) {
            throw new Exception(new Error("iMax does not have the expected value"));
        }
    }

    public void testIMaxRecursive(Node node) throws Exception {
        if (node.getLeft() != null) {
            testIMaxRecursive(node.getLeft());
        }

        validateIMax(node);
        // Comment out this line if your height is not done recursively
        validateHeight(node);

        if (node.getRight() != null) {
            testIMaxRecursive(node.getRight());
        }
    }

    public Interval createOverlappingInterval(Interval interval) {
        Random rand = new Random();
        int random = rand.nextInt(1000);
        int tooLow = rand.nextInt(interval.getLow());
        int diff = interval.getHigh() - interval.getLow();
        int inBetween1;
        int inBetween2;

        if (diff == 0) {
            inBetween1 = interval.getLow();
            inBetween2 = interval.getHigh();
        } else {
            inBetween1 = rand.nextInt(interval.getHigh() - interval.getLow()) + interval.getLow();
            inBetween2 = rand.nextInt(interval.getHigh() - interval.getLow()) + interval.getLow();
        }
        int tooHigh = rand.nextInt(2000) + interval.getHigh();

        if (random % 5 == 0) {
            return new Interval(tooLow, tooHigh);
        } else if (random % 5 == 2) {
            return new Interval(tooLow, inBetween1);
        } else if (random % 5 == 3) {
            return new Interval(inBetween1, tooHigh);
        } else if (random % 5 == 4) {
            return new Interval(Math.min(inBetween1, inBetween2), Math.max(inBetween1, inBetween2));
        } else {
            return new Interval(interval.getLow(), interval.getHigh());
        }
    }

    public boolean overlappingIntervals(Interval interval1, Interval interval2) {
        if (interval1.getLow() >= interval2.getLow() && interval1.getLow() <= interval2.getHigh()) {
            return true;
        } else if (interval2.getLow() >= interval1.getLow() && interval2.getLow() <= interval1.getHigh()) {
            return true;
        } else if (interval2.getHigh() <= interval1.getHigh() && interval2.getHigh() >= interval1.getLow()) {
            return true;
        } else if (interval1.getHigh() <= interval2.getHigh() && interval1.getHigh() >= interval2.getLow()) {
            return true;
        } else {
            return false;
        }
    }

    public void setupHugeIntervalTreap() {
        Random rand = new Random();
        hugeIntervalTreap = new IntervalTreap();
        int count = 0;
        while (count < TREAP_SIZE) {
            int int1 = rand.nextInt(25000) + 1;
            int int2 = rand.nextInt(25000) + 1;
            int low = Math.min(int1, int2);
            int high = Math.max(int1, int2);
            Node newNode = new Node(new Interval(low, high));
            if (count % FREQUENCY == 0) { // Adjust here to change frequency of nodes to be removed/searched
                nodesInTree.add(newNode);
            }
            hugeIntervalTreap.intervalInsert(newNode);
            count++;
        }
    }

    public void setupNonIncludedTree() {
        Random rand = new Random();
        treapWithoutInterval = new IntervalTreap();
        int diff = rand.nextInt(500);
        int notUsedLow = rand.nextInt(20000);
        notUsedInterval = new Interval(notUsedLow, notUsedLow + diff);
        int count = 0;
        while (count < TREAP_SIZE) {
            int int1 = rand.nextInt(25000) + 1;
            int int2 = rand.nextInt(25000) + 1;
            int low = Math.min(int1, int2);
            int high = Math.max(int1, int2);
            Node newNode = new Node(new Interval(low, high));
            if (!overlappingIntervals(newNode.getInterv(), notUsedInterval)) {
                treapWithoutInterval.intervalInsert(newNode);
                if (count % FREQUENCY == 0) { // Adjust here to change frequency of nodes to be removed/searched
                    nodesInTree.add(newNode);
                }
            }
            count++;
        }
    }

    @Test
    public void testInsert() {
        setupHugeIntervalTreap();
        try {
            testIMaxRecursive(hugeIntervalTreap.getRoot());
        } catch (Exception ex) {
            assertEquals(1, 2);
        }
        assertEquals(TREAP_SIZE, hugeIntervalTreap.getSize());
    }

    @Test
    public void testDelete() {
        setupHugeIntervalTreap();
        int size = TREAP_SIZE - 1;
        for (Node n : nodesInTree) {
            hugeIntervalTreap.intervalDelete(n);
            assertEquals(size, hugeIntervalTreap.getSize());
            try {
                if (size != 0) {
                    testIMaxRecursive(hugeIntervalTreap.getRoot());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                assertEquals(1, 2);
            }
            assertEquals(1, 1);
            size--;
        }
    }

    @Test
    public void testDeleteWhereIntervalNotInTreap(){
        setupNonIncludedTree();
        int size = treapWithoutInterval.getSize();
        int height = treapWithoutInterval.getHeight();

        treapWithoutInterval.intervalDelete(new Node(notUsedInterval));
        assertEquals(size, treapWithoutInterval.getSize());
        assertEquals(height, treapWithoutInterval.getHeight());
    }

    @Test
    public void testIntervalSearch() {
        setupHugeIntervalTreap();
        for (Node n : nodesInTree) {
            Interval createdInterval = createOverlappingInterval(n.getInterv());
            Node result = hugeIntervalTreap.intervalSearch(createdInterval);

            assertTrue(overlappingIntervals(result.getInterv(), createdInterval));
        }
    }

    @Test
    public void testIntervalSearchWithNoInterval(){
        setupNonIncludedTree();

        Node result = treapWithoutInterval.intervalSearch(notUsedInterval);
        assertNull(result);
    }

    /*
     * Methods below this point are the extra credit methods
     * I'm not even sure we can still get points for them at this point, but since I already had the methods I left the tests in.
     * If you didn't implement EC methods, or don't want to test them anything below this point can be removed
     */

    @Test
    public void testIntervalSearchExactly() {
        setupHugeIntervalTreap();
        for (Node n : nodesInTree) {
            Node result = hugeIntervalTreap.intervalSearchExactly(n.getInterv());

            assertEquals(result.getInterv().getLow(), n.getInterv().getLow());
            assertEquals(result.getInterv().getHigh(), n.getInterv().getHigh());
        }
    }

    @Test
    public void testIntervalSearchExactlyWhereNotInTreap(){
        setupNonIncludedTree();

        Node result = treapWithoutInterval.intervalSearchExactly(notUsedInterval);
        assertNull(result);
    }

    @Test
    public void testOverlappingIntervalsFromNode() {
        setupHugeIntervalTreap();
        for (Node n : nodesInTree) {
            List<Interval> results = hugeIntervalTreap.overlappingIntervals(n.getInterv());

            for (Interval result : results) {
                assertTrue(overlappingIntervals(result, n.getInterv()));
            }
        }
    }

    @Test
    public void testOverlappingIntervalsFromNodeWhereNotInTreap(){
        setupNonIncludedTree();
        List<Interval> results = treapWithoutInterval.overlappingIntervals(notUsedInterval);
        assertEquals(0, results.size());
    }
}