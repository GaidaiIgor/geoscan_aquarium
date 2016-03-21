import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;

import javax.swing.*;
import java.util.ArrayList;
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
        List<Integer> test1 = Arrays.asList(4, 1, 3, 1, 2, 1, 3, 1, 2);
        List<Integer> waterDepth = Core.waterDepth(test1);
        List<Cube> cubes = getCubes(test1, waterDepth);

        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        CustomPanel panel = new CustomPanel(capabilities);
        cubes.forEach(panel::addGLEventListener);

        JFrame frame = new JFrame("Aquarium");
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        panel.requestFocusInWindow();
    }

    private static List<Cube> getCubes(List<Integer> ground, List<Integer> water) {
        float[] brown = {150f / 255f, 75f / 255f, 0f};
        float[] blue = {0, 0, 1};

        List<Cube> cubes = new ArrayList<>();
        for (int i = 0; i < ground.size(); ++i) {
            for (int j = 0; j < ground.get(i); ++j) {
                cubes.add(new Cube(brown[0], brown[1], brown[2], i, j));
            }
            for (int j = 0; j < water.get(i); ++j) {
                cubes.add(new Cube(blue[0], blue[1], blue[2], i, ground.get(i) + j));
            }
        }

        return cubes;
    }
}
