import javax.swing.*;
import java.awt.*;

/**
 * A view class that displays the screen of the computer that is being controlled.
 */
public class ClientUI {
    private final JPanel panel;
    private final ImageLabel screen;
    private final JFrame frame;

    /**
     * Constructs a clientUI capable of displaying incoming images. The width and height of the UI is based on the
     * provided ImageIcon.
     * @param image The initial image of the server's screen that the UI should display.
     */
    public ClientUI(ImageIcon image) {
        panel = new JPanel();
        screen = new ImageLabel(image);
        screen.setLocation(0, 0);
        screen.setSize(image.getIconWidth(), image.getIconHeight());
        screen.setHorizontalAlignment(SwingConstants.CENTER);
        screen.setVerticalAlignment(SwingConstants.CENTER);
        panel.add(screen);
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * @return The bounds of the panel containing the image of the server's screen.
     */
    public Rectangle getBounds() {
        return panel.getBounds();
    }

    /**
     * @return The panel used by the UI.
     */
    public JPanel getPanel() {
        return panel;
    }

    /**
     * Sets the image that the UI should display and repaints the UI.
     * @param im The image that the UI should display.
     */
    public void updateScreen(ImageIcon im) {
        screen.setImage(im);
        screen.repaint();
    }
}