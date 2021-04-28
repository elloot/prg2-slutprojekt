import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

public class ScreenStreamer implements Runnable {
    private OutputStream out;
    private Rectangle screenBounds;
    private Robot robot;

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
        BufferedImage screenshot = robot.createScreenCapture(screenBounds);
        try {
            ImageIO.write(screenshot, "JPEG", out);
        } catch (IOException e) {
            System.out.println("Failed to send image of screen, exiting");
            e.printStackTrace();
            System.exit(0);
        }
    }
}
