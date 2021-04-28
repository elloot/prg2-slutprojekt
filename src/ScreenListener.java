import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ScreenListener implements Runnable {
    private InputStream in;
    private boolean running;

    public ScreenListener(InputStream i) {
        in = i;
    }

    @Override
    public void run() {
        running = true;
        while(running) {
            byte[] data;
            try {
                data = in.readAllBytes();
            } catch (IOException e) {
                data = new byte[0];
                e.printStackTrace();
            }
            BufferedImage screenShot;
            try {
                screenShot = ImageIO.read(new ByteArrayInputStream(data));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
