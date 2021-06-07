import javax.swing.*;
import java.awt.*;

/**
 * The main class that starts the program.
 */
public class Main {
    public static void main(String[] args) {
        int choice = JOptionPane.showOptionDialog(null, "Do you want to control a computer or to have your computer " +
                        "controlled?", "Make a choice",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] {"Let someone control my " +
                        "computer",
                        "Let me control someone's computer"},
                "Let someone control my computer");
        if (choice == 0) {
            new Server(1234);
        } else {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            new Client(1234, JOptionPane.showInputDialog("Input the ip you wish to connect to"),
                    screenSize.width / 2, screenSize.height / 2);
        }
    }
}
