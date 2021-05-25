import javax.swing.*;
import java.awt.*;

public class ClientUI {
    private final JPanel panel;
    private final ImageLabel screen;
    private final JFrame frame;

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

    public Rectangle getBounds() {
        return panel.getBounds();
    }

    public JFrame getFrame() {
        return frame;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void updateScreen(ImageIcon im) {
        screen.setImage(im);
        screen.repaint();
    }
}