import javax.swing.*;
import java.awt.image.BufferedImage;

public class ClientUI {
    private JPanel panel;
    private ImageLabel screen;

    public ClientUI() {
        screen = new ImageLabel();
        panel.add(screen);
        JFrame frame = new JFrame();
        frame.getContentPane().add(panel);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

    public void updateScreen(BufferedImage im) {
        screen.setImage(im);
        screen.repaint();
    }
}
