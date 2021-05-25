import javax.swing.*;
import java.io.*;
import java.net.SocketException;

public class ScreenListener implements Runnable {
    private ObjectInputStream in;
    private boolean running;
    private final Client client;

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
        Object im;
        ImageIcon screenShot;
        while(running) {
            try {
                im = in.readObject();
            } catch (SocketException e) {
                if (e.getMessage().equals("Connection reset")) {
                    System.out.println("Server disconnected, exiting");
                    System.exit(0);
                } else {
                    System.out.println("Something went wrong with the connection to the server");
                    e.printStackTrace();
                }
                im = null;
            } catch (ClassNotFoundException | IOException e) {
                im = null;
                System.out.println("Failed to read image from server, exiting");
                e.printStackTrace();
                System.exit(0);
            }
            screenShot = (ImageIcon) im;
            client.updateScreen(screenShot);
        }
    }
}
