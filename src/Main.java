import java.util.Arrays;
import java.util.List;

public class Main {
    public static void testCore() {
        List<Integer> test1 = Arrays.asList(4, 1, 3, 1, 2, 1, 3, 1, 2);
        List<Integer> test2 = Arrays.asList(3, 1, 2, 0, 2, 1, 3);
        List<Integer> test3 = Arrays.asList(3, 1, 1, 2, 1, 4);
        List<Integer> test4 = Arrays.asList(3, 1, 0, 3, 1, 4, 1, 2);
        List<Integer> test5 = Arrays.asList(0, 0, 0);
        List<Integer> test6 = Arrays.asList(0);
        List<Integer> test7 = Arrays.asList(5, 5, 5);
        List<Integer> test8 = Arrays.asList(5);
        List<Integer> test9 = Arrays.asList(4, 2, 3, 4, 0, 3);
        List<Integer> test10 = Arrays.asList(4, 3, 0, 2, 5);
        List<Integer> test11 = Arrays.asList(4, 3, 1, 4, 2);
        List<Integer> test12 = Arrays.asList(1, 3, 2, 0, 6, 1, 2, 5);
        List<Integer> test13 = Arrays.asList(1, 3, 1, 4, 2, 0, 6, 1, 2, 5);
        List<Integer> waterDepth = Core.waterDepth(test13);
        System.out.println(waterDepth);
    }

    public static void main(String[] args) {
        TestGame game = new TestGame();

    }
}
