import java.util.*;

/**
 * This class performs core calculations
 */
public class Core {
    /**
     * Calculates water height in each column in accordance with land description
     *
     * @param cubesHeight heights of land columns
     * @return list of water heights. Water is located above ground
     */
    public static List<Integer> waterDepth(List<Integer> cubesHeight) {
        List<Integer> waterDepth = new ArrayList<>(Collections.nCopies(cubesHeight.size(), 0));
        List<List<Integer>> bounds = getLeftRightBounds(cubesHeight);
        for (int i = 0; i < cubesHeight.size(); ++i) {
            waterDepth.set(i, Math.min(bounds.get(0).get(i), bounds.get(1).get(i)) - cubesHeight.get(i));
        }

        return waterDepth;
    }

    /**
     * Determines the highest obstacle for water from left and right side for each column
     * @param cubesHeight land description
     * @return pair of bound arrays. First element is left bounds, second is right.
     */
    private static List<List<Integer>> getLeftRightBounds(List<Integer> cubesHeight) {
        List<Integer> reverseHeight = new ArrayList<>(cubesHeight);
        Collections.reverse(reverseHeight);

        List<Integer> leftBounds = getLeftBounds(cubesHeight.iterator());
        List<Integer> rightBounds = getLeftBounds(reverseHeight.iterator());
        Collections.reverse(rightBounds);

        return Arrays.asList(leftBounds, rightBounds);
    }

    /**
     * Calculates water bounds in left direction for each column
     *
     * @param cubesHeight land description iterator
     * @return water bounds in left direction
     */
    private static List<Integer> getLeftBounds(Iterator<Integer> cubesHeight) {
        List<Integer> bounds = new ArrayList<>();
        Integer max = 0;
        while (cubesHeight.hasNext()) {
            Integer height = cubesHeight.next();
            if (height > max) {
                max = height;
            }
            if (height == 0) {
                max = 0;
            }
            bounds.add(max);
        }
        return bounds;
    }
}
