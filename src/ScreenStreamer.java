import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class ScreenStreamer implements Runnable {
    private ObjectOutputStream out;
    private Rectangle screenBounds;
    private Robot robot;
    private int fps = 15;
    private boolean running;

    public ScreenStreamer(OutputStream o) {
        try {
            out = new ObjectOutputStream(o);
        } catch (IOException e) {
            System.out.println("Failed to create output stream, exiting");
            e.printStackTrace();
            System.exit(0);
        }
        screenBounds = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        try {
            robot = new Robot();
        } catch (AWTException e) {
            System.out.println("Failed to initialise robot, exiting");
            e.printStackTrace();
            System.exit(0);
        }
    }
    @Override
    public void run() {
        running = true;
        double fpsInterval = 1000000000.0 / fps;
        double delta = 0;
        long lastTime = System.nanoTime();

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / fpsInterval;
            lastTime = now;

            while(delta >= 1) {
                try {
                    BufferedImage im = captureScreen();
                    sendScreen(im);
                    im = null;
                    robot = null;
                    robot = new Robot();
                } catch (IOException e) {
                    errorSend(e);
                } catch (AWTException e) {
                    e.printStackTrace();
                    System.out.println("Failed to create robot");
                }
                delta--;
            }
        }
    }

    private void errorSend(IOException e) {
        System.out.println("Failed to send image of screen, exiting");
        e.printStackTrace();
        System.exit(0);
    }

    private BufferedImage captureScreen() {
        return robot.createScreenCapture(screenBounds);
    }

    private void sendScreen(BufferedImage im) throws IOException {
        ImageIcon imageIcon = compressToImageIcon(im);
        out.writeObject(imageIcon);
        out.flush();
    }

    private ImageIcon compressToImageIcon (BufferedImage im) throws IOException {
        return new ImageIcon(compressToByteArray(im));
    }

    private byte[] compressToByteArray (BufferedImage im) throws IOException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ImageIO.write(im, "JPEG", byteOut);
        return byteOut.toByteArray();
    }
}
