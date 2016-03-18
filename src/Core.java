import java.util.*;

public class Core {
    public static List<Integer> waterDepth(List<Integer> cubesHeight) {
        List<Integer> waterDepth = new ArrayList<>(Collections.nCopies(cubesHeight.size(), 0));
//        List<List<Integer>> solidPieces = splitBySinks(cubesHeight);
        List<List<Integer>> bounds = getLeftRightBounds(cubesHeight);
        for (int i = 0; i < cubesHeight.size(); ++i) {
            waterDepth.set(i, Math.min(bounds.get(0).get(i), bounds.get(1).get(i)) - cubesHeight.get(i));
        }

        return waterDepth;
    }

    private static List<List<Integer>> getLeftRightBounds(List<Integer> cubesHeight) {
        List<Integer> reverseHeight = new ArrayList<>(cubesHeight);
        Collections.reverse(reverseHeight);

        List<Integer> leftBounds = getBounds(cubesHeight.iterator());
        List<Integer> rightBounds = getBounds(reverseHeight.iterator());
        Collections.reverse(rightBounds);

        return Arrays.asList(leftBounds, rightBounds);
    }

    private static List<Integer> getBounds(Iterator<Integer> cubesHeight) {
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

    private static List<List<Integer>> splitBySinks(List<Integer> cubesHeight) {
        List<List<Integer>> solidPieces = new ArrayList<>();
        solidPieces.add(new ArrayList<>());
        for (Integer height : cubesHeight) {
            if (height > 0) {
                solidPieces.get(solidPieces.size() - 1).add(height);
            } else {
                solidPieces.add(new ArrayList<>());
            }
        }
        return solidPieces;
    }
}
