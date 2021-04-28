import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class ScreenListener implements Runnable {
    private InputStream in;
    private boolean running;
    private Client client;

    public ScreenListener(InputStream i, Client c) {
        in = i;
        client = c;
    }

    @Override
    public void run() {
        running = true;
        while(running) {
            byte[] imageAr;
            byte[] sizeAr = new byte[4];
            try {
                in.read(sizeAr);
                int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();
                imageAr = new byte[size];
                in.read(imageAr);
                System.out.println("Attempting to read data");
            } catch (IOException e) {
                imageAr = new byte[0];
                System.out.println("Failed to read image from server, exiting");
                e.printStackTrace();
                System.exit(0);
            }
            BufferedImage screenShot;
            try {
                screenShot = ImageIO.read(new ByteArrayInputStream(imageAr));
            } catch (IOException e) {
                screenShot = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
                System.out.println("Failed to convert bytes to image, exiting");
                e.printStackTrace();
                System.exit(0);
            }
            client.updateScreen(screenShot);
        }
    }
}
