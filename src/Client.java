import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private Socket socket;
    private ClientUI ui;
    private ScreenListener screenListener;
    private MouseInfoStreamer mouseStreamer;
    private final int initialWidth;
    private final int initialHeight;

    public Client(int port, String ip, int initialWidth, int initialHeight) {
        this.initialWidth = initialWidth;
        this.initialHeight = initialHeight;
        try {
            socket = new Socket(ip, port);
        } catch (IOException e) {
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
        Thread listenerThread = new Thread(screenListener);
        listenerThread.start();
    }

    public void updateScreen(ImageIcon im) {
        if (ui == null) {
            ui = new ClientUI(fitToSize(im, initialWidth, initialHeight));
            ui.getPanel().addMouseListener(mouseStreamer.getMouseListener());
            ui.getPanel().addMouseMotionListener(mouseStreamer.getMouseMotionListener());
        }
        ui.updateScreen(fitToSize(im, ui.getBounds().width, ui.getBounds().height));
    }

    private ImageIcon fitToSize(ImageIcon srcIco, int width, int height) {
        Dimension newSize = calcSizeFromBounds(srcIco, width, height);
        return new ImageIcon(ImageUtil.getScaledImage(srcIco.getImage(), newSize.width, newSize.height));
    }

    private Dimension calcSizeFromBounds(ImageIcon ico, int width, int height) {
        int iconWidth = ico.getIconWidth();
        int iconHeight = ico.getIconHeight();
        double aspectRatio = ((double) iconWidth) / ((double) iconHeight);
        int newWidth = width;
        int newHeight = (int) (newWidth / aspectRatio);
        if (height - newHeight < 0) {
            newHeight = height;
            newWidth = (int) (newHeight * aspectRatio);
        }
        return new Dimension(newWidth, newHeight);
    }

    public ImageIcon resize(ImageIcon im) {
        return ImageUtil.resize(im, ui.getBounds().width, ui.getBounds().height);
    }
}
