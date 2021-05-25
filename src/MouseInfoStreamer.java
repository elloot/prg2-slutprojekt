import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class MouseInfoStreamer {
    private ObjectOutputStream out;
    private final MouseListener mouseListener;
    private final MouseMotionListener mouseMotionListener;

    public MouseInfoStreamer(OutputStream o) {
        try {
            out = new ObjectOutputStream(o);
        } catch (IOException e) {
            System.out.println("Failed to create object output stream, exiting");
            e.printStackTrace();
            System.exit(0);
        }
        mouseListener = new MouseListener(this);
        mouseMotionListener = new MouseMotionListener(this);
    }

    void sendMouseInfo(MouseInfo mi) {
        try {
            out.writeObject(mi);
            out.flush();
            out.reset();
        } catch (IOException ioException) {
            System.out.println("Something went wrong when sending the mouse info");
            ioException.printStackTrace();
        }
    }

    public MouseListener getMouseListener() {
        return mouseListener;
    }

    public MouseMotionListener getMouseMotionListener() {
        return mouseMotionListener;
    }
}

class MouseListener implements java.awt.event.MouseListener {
    private final MouseInfoStreamer mouseStreamer;

    public MouseListener(MouseInfoStreamer m) {
        mouseStreamer = m;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mouseStreamer.sendMouseInfo(new MouseInfo(e, MouseEventType.CLICKED));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseStreamer.sendMouseInfo(new MouseInfo(e, MouseEventType.PRESSED));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseStreamer.sendMouseInfo(new MouseInfo(e, MouseEventType.RELEASED));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}

class MouseMotionListener implements java.awt.event.MouseMotionListener {
    private final MouseInfoStreamer mouseStreamer;

    public MouseMotionListener(MouseInfoStreamer m) {
        mouseStreamer = m;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseStreamer.sendMouseInfo(new MouseInfo(e, MouseEventType.DRAGGED));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseStreamer.sendMouseInfo(new MouseInfo(e, MouseEventType.MOVED));
    }
}
