public class main {
    public static void main(String[] args) {
        IntervalTreap test = new IntervalTreap();
        test.intervalInsert(new Node(new Interval(1, 38)));
        System.out.println(test.toString());
    }
}
