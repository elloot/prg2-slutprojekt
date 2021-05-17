import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class MouseInfoStreamer {
    private ObjectOutputStream out;

    public MouseInfoStreamer(OutputStream o) {
        try {
            out = new ObjectOutputStream(o);
        } catch (IOException e) {
            System.out.println("Failed to create object output stream, exiting");
            e.printStackTrace();
            System.exit(0);
        }
    }

    void sendMouseInfo(MouseEvent e) {
        try {
            out.writeObject(e);
            out.flush();
            out.reset();
        } catch (IOException ioException) {
            System.out.println("Something went wrong when sending the mouse info");
            ioException.printStackTrace();
        }
    }
}

class MouseListener implements java.awt.event.MouseListener {
    private MouseInfoStreamer mouseStreamer;

    public MouseListener(MouseInfoStreamer m) {
        mouseStreamer = m;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println(e.getClickCount());
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

class MouseMotionListener implements java.awt.event.MouseMotionListener {
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
