import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.Serializable;

public class MouseInfo implements Serializable {
    private final MouseEventType eventType;
    private final Rectangle screenBounds;
    private final Point location;
    private final double xFromEdge;
    private final double yFromEdge;
    private final int button;

    public MouseInfo(MouseEvent e, MouseEventType eventType) {
        this.eventType = eventType;
        screenBounds = e.getComponent().getBounds();
        location = e.getPoint();
        xFromEdge = ((double) location.x / (double) screenBounds.width);
        yFromEdge = ((double) location.y / (double) screenBounds.height);
        button = e.getButton();
    }

    public MouseEventType getEventType() {
        return eventType;
    }

    public Rectangle getScreenBounds() {
        return screenBounds;
    }

    public Point getLocation() {
        return location;
    }

    public double getxFromEdge() {
        return xFromEdge;
    }

    public double getyFromEdge() {
        return yFromEdge;
    }

    public int getButton() {
        return button;
    }
}
