import java.awt.*;
import java.awt.event.MouseEvent;
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
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        streamerThread = new Thread(screenStreamer);
        streamerThread.start();
    }

    public void handleMouseMove(MouseEvent e) {
        Rectangle clientBounds = e.getComponent().getBounds();
        Point clickLocation = e.getPoint();
        Point mappedClick = new Point((clickLocation.x / clientBounds.width) * screenSize.width,
                (clickLocation.y / clientBounds.height) * screenSize.height);
        robot.mouseMove(mappedClick.x, mappedClick.y);
        if (e.getClickCount() > 0) {
            robot.mousePress(e.getButton());
        } else {
            robot.mouseRelease(e.getButton());
        }
    }
}
