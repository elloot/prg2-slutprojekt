import javax.swing.*;
import java.awt.*;

public class ClientMain {
    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        new Client(1234, JOptionPane.showInputDialog("Input the ip you wish to connect to"),
                screenSize.width / 2, screenSize.height / 2);
    }
}
