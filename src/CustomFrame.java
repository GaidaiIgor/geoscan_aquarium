import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * This class represents custom JFrame. This class allows its instances to be closed by pressing escape key.
 * It also sets some common settings.
 */
public class CustomFrame extends JFrame {
    public CustomFrame(String title) {
        super(title);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        JRootPane root = getRootPane();
        root.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "close");
        root.getActionMap().put("close", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window parent = SwingUtilities.getWindowAncestor((Component) e.getSource());
                parent.dispose();
            }
        });
    }
}
