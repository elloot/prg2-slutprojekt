import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private Socket socket;
    private ClientUI ui;
    private ScreenListener screenListener;
    private Thread listenerThread;
    private MouseInfoStreamer mouseStreamer;

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
        try {
            mouseStreamer = new MouseInfoStreamer(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Failed to get output stream from socket, exiting");
            e.printStackTrace();
            System.exit(0);
        }
        listenerThread = new Thread(screenListener);
        listenerThread.start();
    }

    public void updateScreen(ImageIcon im) {
        if (ui == null) {
            ui = new ClientUI(im);
            ui.getFrame().addMouseListener(mouseStreamer.getMouseListener());
            ui.getFrame().addMouseMotionListener(mouseStreamer.getMouseMotionListener());
        }
        ui.updateScreen(resizeIcon(im));
    }

    public ImageIcon resizeIcon(ImageIcon srcIco) {
        int iconWidth = srcIco.getIconWidth();
        int iconHeight = srcIco.getIconHeight();
        double aspectRatio = ((double) iconWidth)/((double)iconHeight);
        int newWidth = ui.getBounds().width;
        int newHeight = (int) (newWidth / aspectRatio);
        return new ImageIcon(ImageUtil.getScaledImage(srcIco.getImage(), newWidth, newHeight));
    }

    public ImageIcon resize(ImageIcon im) {
        return ImageUtil.resize(im, ui.getBounds().width, ui.getBounds().height);
    }
}
