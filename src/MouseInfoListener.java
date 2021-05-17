import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class MouseInfoListener implements Runnable {
    private ObjectInputStream in;
    private Server server;

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

    }
}
