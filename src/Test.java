public class Test {
    public static void main(String[] args) {
        IntervalTreap test = new IntervalTreap();
        System.out.println("test");
        test.intervalInsert(new Node(new Interval(1, 38)));
        test.intervalInsert(new Node(new Interval(9, 40)));
        test.intervalInsert(new Node(new Interval(-50, -5)));
        test.intervalInsert(new Node(new Interval(10, 20)));
        System.out.println(test.toString());
    }
}
