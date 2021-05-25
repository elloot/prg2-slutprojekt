import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.Socket;

/**
 * A controller class for the part of the program that controls the other computer. Sends data between the UI and the
 * necessary models (i.e. the MouseStreamer and ScreenListener). This class also sets up the connection between it
 * and the server with a Socket.
 */
public class Client {
    private Socket socket;
    private ClientUI ui;
    private ScreenListener screenListener;
    private MouseInfoStreamer mouseStreamer;
    private final int initialWidth;
    private final int initialHeight;

    /**
     * Constructs a client with a port and IP to try to connect to and an initial width and height for the UI. When
     * constructed, the client immediately attempts to connect to the server using the provided IP and port.
     * @param port The port the client should try to connect on.
     * @param IP The IP the client should try to connect to.
     * @param initialWidth The initial width to use for the UI.
     * @param initialHeight The initial height to use for the UI.
     */
    public Client(int port, String IP, int initialWidth, int initialHeight) {
        this.initialWidth = initialWidth;
        this.initialHeight = initialHeight;
        try {
            socket = new Socket(IP, port);
        } catch (IOException e) {
            System.out.println("Failed to connect to IP " + IP + " on port " + port);
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

    /**
     * Updates the UI with the incoming image of the server's screen.
     * @param im Image of the server's screen.
     */
    public void updateScreen(ImageIcon im) {
        if (ui == null) {
            ui = new ClientUI(fitToSize(im, initialWidth, initialHeight));
            ui.getPanel().addMouseListener(mouseStreamer.getMouseListener());
            ui.getPanel().addMouseMotionListener(mouseStreamer.getMouseMotionListener());
        }
        ui.updateScreen(fitToSize(im, ui.getBounds().width, ui.getBounds().height));
    }

    /**
     * Fits an ImageIcon to the specified width and height, i.e. gets the largest width and height that the ImageIcon
     * can have and still fit inside the specified dimensions.
     * @param srcIco The ImageIcon that will be used to create a new, fitted ImageIcon.
     * @param width The width that the new ImageIcon should fit into.
     * @param height The height that the new ImageIcon should fit into.
     * @return A new ImageIcon that fits inside the specified width and height.
     */
    private ImageIcon fitToSize(ImageIcon srcIco, int width, int height) {
        Dimension newSize = calcSizeFromBounds(srcIco, width, height);
        return new ImageIcon(ImageUtil.getScaledImage(srcIco.getImage(), newSize.width, newSize.height));
    }

    /**
     * Calculates the width and height that an ImageIcon should have to fit inside the specified bounds (width and
     * height).
     * @param ico The ImageIcon used to determine proper width and height of a fitted icon.
     * @param width The width that the ImageIcon needs to fit into.
     * @param height The height that the ImageIcon needs to fit into.
     * @return A dimension describing the width and height an ImageIcon should have to fit inside the specified width
     * and height.
     */
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
}
