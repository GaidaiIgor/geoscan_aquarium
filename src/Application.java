import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Application {
    Action runAction = new RunAction("Run");
    private JTextField input = new JTextField("4, 1, 3, 1, 2, 1, 3, 1, 2", 15);
    private JButton run;

    public Application() {
        run = new JButton(runAction);
    }

    private void showCubes(String description) {
        List<Integer> ground = Arrays.stream(description.split(",")).map(String::trim).map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> waterDepth = Core.waterDepth(ground);
        List<Cube> cubes = getCubes(ground, waterDepth);

        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        float defaultXTranslation = (cubes.get(0).getSize() - ground.size()) / 2f;
        CustomPanel panel = new CustomPanel(capabilities, defaultXTranslation);
        cubes.forEach(panel::addGLEventListener);

        CustomFrame frame = new CustomFrame("Aquarium");
        frame.setContentPane(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        panel.requestFocusInWindow();
    }

    private List<Cube> getCubes(List<Integer> ground, List<Integer> water) {
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

    public void start() {
        CustomFrame frame = new CustomFrame("Aquarium data");
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(input, BorderLayout.CENTER);
        panel.add(run, BorderLayout.SOUTH);
        frame.setContentPane(panel);
        frame.setMinimumSize(new Dimension(300, 50));
        frame.getRootPane().setDefaultButton(run);

        input.requestFocusInWindow();
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private class RunAction extends AbstractAction {
        public RunAction(String text) {
            super(text);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            showCubes(input.getText());
        }
    }
}
