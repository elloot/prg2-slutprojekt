import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class MouseInfoListener implements Runnable {
    private ObjectInputStream in;
    private Server server;
    private boolean running;

    public MouseInfoListener(InputStream i, Server s) {
        try {
            in = new ObjectInputStream(i);
        } catch (IOException e) {
            System.out.println("Failed to create object input stream from input stream, exiting");
            e.printStackTrace();
            System.exit(0);
        }
        server = s;
    }

    @Override
    public void run() {
        running = true;
        while (running) {
            Object object;
            MouseInfo mouseInfo;
            try {
                object = in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                object = null;
                System.out.println("Something went wrong when reading the mouse info");
                e.printStackTrace();
            }
            mouseInfo = (MouseInfo) object;
            server.handleMouseEvent(mouseInfo);
        }
    }
}
