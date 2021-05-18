import java.awt.*;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private Socket socket;
    private ScreenStreamer screenStreamer;
    private Thread streamerThread;
    private Robot robot;
    private Dimension screenSize;
    private Thread mouseThread;
    private MouseInfoListener mouseInfoListener;

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
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
        streamerThread = new Thread(screenStreamer);
        streamerThread.start();
        mouseThread = new Thread(mouseInfoListener);
        mouseThread.start();
    }

    public void handleMouseEvent(MouseInfo mouseInfo) {
        screenStreamer.setClientScreenSize(mouseInfo.getScreenBounds());
        Point location = new Point((int) (mouseInfo.getxFromEdge() * (double) screenSize.width), (int)
                (mouseInfo.getyFromEdge() * (double) screenSize.height));
        switch (mouseInfo.getEventType()) {
            case CLICKED -> {
                robot.mouseMove(location.x, location.y);
                robot.mousePress(InputEvent.getMaskForButton(mouseInfo.getButton()));
                robot.mouseRelease(InputEvent.getMaskForButton(mouseInfo.getButton()));
            }
            case PRESSED -> {
                robot.mouseMove(location.x, location.y);
                robot.mousePress(InputEvent.getMaskForButton(mouseInfo.getButton()));
            }
            case RELEASED -> {
                robot.mouseRelease(InputEvent.getMaskForButton(mouseInfo.getButton()));
            }
            case DRAGGED -> {
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseMove(location.x, location.y);
            }
            case MOVED -> {
                robot.mouseMove(location.x, location.y);
            }
        }
    }
}
