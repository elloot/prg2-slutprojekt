import java.awt.*;
import java.awt.event.MouseEvent;

public class MouseInfo {
    private final MouseEventType eventType;
    private final Rectangle screenBounds;
    private final Point clickLocation;
    private final Point mappedClickLocation;

    public MouseInfo(MouseEvent e, MouseEventType eventType) {
        this.eventType = eventType;
        screenBounds = e.getComponent().getBounds();
        clickLocation = e.getPoint();
        mappedClickLocation = new Point((clickLocation.x / screenBounds.width) * screenBounds.width,
                (clickLocation.y / screenBounds.height) * screenBounds.height);
    }

    public MouseEventType getEventType() {
        return eventType;
    }

    public Rectangle getScreenBounds() {
        return screenBounds;
    }

    public Point getClickLocation() {
        return clickLocation;
    }

    public Point getMappedClickLocation() {
        return mappedClickLocation;
    }
}
