import java.awt.*;
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

    }
}
