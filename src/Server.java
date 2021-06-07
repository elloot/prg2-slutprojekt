import java.awt.*;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private Socket socket;
    private ScreenStreamer screenStreamer;
    private Robot robot;
    private final Dimension screenSize;
    private MouseInfoListener mouseInfoListener;

    public Server(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();
        } catch (IOException e) {
            System.out.println("Failed to connect on port " + port);
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println("Connection established");
        try {
            screenStreamer = new ScreenStreamer(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Failed to get output stream, exiting");
            e.printStackTrace();
            System.exit(0);
        }
        try {
            robot = new Robot();
        } catch (AWTException e) {
            System.out.println("Failed to create robot, exiting");
            e.printStackTrace();
            System.exit(0);
        }
        try {
            mouseInfoListener = new MouseInfoListener(socket.getInputStream(), this);
        } catch (IOException e) {
            System.out.println("Failed to get input stream, exiting");
            e.printStackTrace();
            System.exit(0);
        }
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Thread streamerThread = new Thread(screenStreamer);
        streamerThread.start();
        Thread mouseThread = new Thread(mouseInfoListener);
        mouseThread.start();
    }

    public void handleMouseEvent(MouseInfo mouseInfo) {
        screenStreamer.setClientScreenSize(mouseInfo.getScreenBounds());
        Point location = new Point((int) (mouseInfo.getXFromEdge() * (double) screenSize.width), (int)
                (mouseInfo.getYFromEdge() * (double) screenSize.height));
        switch (mouseInfo.getEventType()) {
            case CLICKED:
                robot.mouseMove(location.x, location.y);
                robot.mousePress(InputEvent.getMaskForButton(mouseInfo.getButton()));
                robot.mouseRelease(InputEvent.getMaskForButton(mouseInfo.getButton()));
                break;
            case PRESSED:
                robot.mouseMove(location.x, location.y);
                robot.mousePress(InputEvent.getMaskForButton(mouseInfo.getButton()));
                break;
            case RELEASED:
                robot.mouseRelease(InputEvent.getMaskForButton(mouseInfo.getButton()));
                break;
            case DRAGGED:
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseMove(location.x, location.y);
                break;
            case MOVED:
                robot.mouseMove(location.x, location.y);
                break;
        }
    }
}
