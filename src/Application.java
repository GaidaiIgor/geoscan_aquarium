import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * This class represents main window with scene settings
 */
public class Application {
    Action runAction = new RunAction("Run");
    private JTextField input = new JTextField("4, 1, 3, 1, 2, 1, 3, 1, 2", 15);
    // this button starts building scene window corresponding to scene description in input
    private JButton run;

    public Application() {
        run = new JButton(runAction);
    }

    /**
     * Builds main window
     */
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
            DisplayScreen displayScreen = new DisplayScreen();
            displayScreen.start(input.getText());
        }
    }
}
