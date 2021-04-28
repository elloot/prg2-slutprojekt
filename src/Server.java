import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private Socket socket;
    private ScreenStreamer screenStreamer;
    private Thread streamerThread;

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
        streamerThread = new Thread(screenStreamer);
        streamerThread.start();
    }
}
