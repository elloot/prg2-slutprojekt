import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

public class ScreenStreamer implements Runnable {
    private OutputStream out;
    private Rectangle screenBounds;
    private Robot robot;
    private int fps = 30;
    private boolean running;

    public ScreenStreamer(OutputStream o) {
        out = o;
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
                    sendScreen(captureScreen());
                } catch (IOException e) {
                    errorSend(e);
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
        ImageIO.write(im, "JPEG", out);
    }
}
