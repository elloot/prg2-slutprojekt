import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ClientUI {
    private JPanel panel;
    private ImageLabel screen;
    private JFrame frame;

    public ClientUI(ImageIcon image) {
        panel = new JPanel();
        screen = new ImageLabel(image);
        screen.setLocation(0, 0);
        panel.add(screen);
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.setLocationRelativeTo(null);
        frame.pack();
        // frame.addComponentListener(new ResizeListener());
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

class ResizeListener extends ComponentAdapter {
    @Override
    public void componentResized(ComponentEvent e) {
        final double W = 16;
        final double H = 9;
        Rectangle b = e.getComponent().getBounds();
        e.getComponent().setBounds(b.x, b.y, b.width, (int) (b.width * (H/W)));
    }
}
