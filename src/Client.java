import java.io.IOException;
import java.net.Socket;

public class Client {
    private Socket socket;

    public Client(int port, String ip) {
        try {
            socket = new Socket(ip, port);
        } catch(IOException e) {
            System.out.println("Failed to connect to ip " + ip + " on port " + port);
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println("Connection established");
    }
}
