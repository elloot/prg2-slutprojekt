import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.nio.Buffer;
import java.util.Arrays;

public class ClientUI {
    private JPanel panel;
    private ImageLabel screen;

    public ClientUI(BufferedImage image) {
        screen = new ImageLabel(image);
        screen.setLocation((1920/2)/2, (1080/2) / 2);
        panel.add(screen);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920/2, 1080/2);
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
