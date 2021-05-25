import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

public class Graphics extends Canvas {
    private final BufferedImage image;
    private final int[] pixels;

    public Graphics(int w, int h) {
        image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

        Dimension size = new Dimension(w, h);
        setPreferredSize(size);
        JFrame frame = new JFrame();
        String title = "Totally not a TeamViewer ripoff";
        frame.setTitle(title);
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        this.requestFocus();
    }

    public void draw(ImageIcon imageIcon) {
        // clears the screen
        Arrays.fill(pixels, 0x000000);
        BufferedImage screen = ImageUtil.toBufferedImage(imageIcon);
        int[] screenPixels = ((DataBufferInt) screen.getRaster().getDataBuffer()).getData();
        System.arraycopy(screenPixels, 0, pixels, 0, pixels.length);

        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        java.awt.Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.dispose();
        bs.show();
    }
}