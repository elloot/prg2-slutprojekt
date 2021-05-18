import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.Serializable;

public class MouseInfo implements Serializable {
    private final MouseEventType eventType;
    private final Rectangle screenBounds;
    private final Point location;
    private final Point locationFromEdge;
    private final int button;

    public MouseInfo(MouseEvent e, MouseEventType eventType) {
        this.eventType = eventType;
        screenBounds = e.getComponent().getBounds();
        location = e.getPoint();
        locationFromEdge = new Point((location.x / screenBounds.width),
                (location.y / screenBounds.height));
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

    public Point getLocationFromEdge() {
        return locationFromEdge;
    }

    public int getButton() {
        return button;
    }
}
