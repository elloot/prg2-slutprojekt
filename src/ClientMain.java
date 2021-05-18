import java.awt.*;

public class ClientMain {
    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Client client = new Client(1234, "127.0.0.1", screenSize.width / 2, screenSize.height / 2);
    }
}
