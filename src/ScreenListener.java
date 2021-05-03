import javax.swing.*;
import java.io.*;

public class ScreenListener implements Runnable {
    private ObjectInputStream in;
    private boolean running;
    private Client client;

    public ScreenListener(InputStream i, Client c) {
        try {
            in = new ObjectInputStream(i);
        } catch (IOException e) {
            System.out.println("Failed to create input stream, exiting");
            e.printStackTrace();
            System.exit(0);
        }
        client = c;
    }

    @Override
    public void run() {
        running = true;
        while(running) {
            Object im;
            try {
                im = in.readObject();
                System.out.println("Attempting to read data");
            } catch (ClassNotFoundException | IOException e) {
                im = null;
                System.out.println("Failed to read image from server, exiting");
                e.printStackTrace();
                System.exit(0);
            }
            ImageIcon screenShot = (ImageIcon) im;
            client.updateScreen(screenShot);
        }
    }
}
