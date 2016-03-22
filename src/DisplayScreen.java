import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DisplayScreen {
    private CustomPanel panel;

    public void start(String description) {
        List<Integer> ground = Arrays.stream(description.split(",")).map(String::trim).map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> waterDepth = Core.waterDepth(ground);

        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        float defaultXTranslation = (Cube.size - ground.size()) / 2f;
        panel = new CustomPanel(capabilities, defaultXTranslation);

        List<Cube> cubes = getCubes(ground, waterDepth);
        cubes.forEach(panel::addGLEventListener);

        CustomFrame frame = new CustomFrame("Aquarium");
        frame.setContentPane(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        panel.requestFocusInWindow();
    }

    private List<Cube> getCubes(List<Integer> ground, List<Integer> water) {
        List<Cube> cubes = new ArrayList<>();
        for (int i = 0; i < ground.size(); ++i) {
            for (int j = 0; j < ground.get(i); ++j) {
                cubes.add(new Cube(panel, i, j, Cube.Type.Ground));
            }
            for (int j = 0; j < water.get(i); ++j) {
                cubes.add(new Cube(panel, i, ground.get(i) + j, Cube.Type.Water));
            }
        }

        return cubes;
    }
}
