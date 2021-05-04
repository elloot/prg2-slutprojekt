import javax.swing.*;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private Socket socket;
    private ClientUI ui;
    private ScreenListener screenListener;
    private Thread listenerThread;

    public Client(int port, String ip) {
        try {
            socket = new Socket(ip, port);
        } catch(IOException e) {
            System.out.println("Failed to connect to ip " + ip + " on port " + port);
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println("Connection established");
        try {
            screenListener = new ScreenListener(socket.getInputStream(), this);
        } catch (IOException e) {
            System.out.println("Failed to get input stream, exiting");
            e.printStackTrace();
            System.exit(0);
        }
        listenerThread = new Thread(screenListener);
        listenerThread.start();
    }

    public void updateScreen(ImageIcon im) {
        if (ui == null) {
            ui = new ClientUI(im);
        }
        ui.updateScreen(resize(im));
    }

    public ImageIcon resize(ImageIcon im) {
        return ImageUtil.resize(im, ui.getBounds().width, ui.getBounds().height);
    }
}
