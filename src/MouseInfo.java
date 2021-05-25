import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.Serializable;

/**
 * A class containing information about the mouse. Supposed to be sent from the client to the server so that the
 * server can move its mouse based on the information in this class.
 */
public class MouseInfo implements Serializable {
    private final MouseEventType eventType;
    private final Rectangle screenBounds;
    private final Point location;
    private final double xFromEdge;
    private final double yFromEdge;
    private final int button;

    /**
     * Constructs a new mouseInfo instance based on the MouseEvent and the type of mouse event that occurred.
     * @param e The MouseEvent that just occurred on the clientside.
     * @param eventType The type of mouse event that occurred on the client side.
     */
    public MouseInfo(MouseEvent e, MouseEventType eventType) {
        this.eventType = eventType;
        screenBounds = e.getComponent().getBounds();
        location = e.getPoint();
        xFromEdge = ((double) location.x / (double) screenBounds.width);
        yFromEdge = ((double) location.y / (double) screenBounds.height);
        button = e.getButton();
    }

    /**
     * @return The type of mouse event this instance of MouseInfo describes.
     */
    public MouseEventType getEventType() {
        return eventType;
    }

    /**
     * @return A Rectangle describing the bounds of the client's screen.
     */
    public Rectangle getScreenBounds() {
        return screenBounds;
    }

    /**
     * @return The location the mouse had when the MouseEvent provided in the constructor occurred.
     */
    public Point getLocation() {
        return location;
    }

    /**
     * @return How far the mouse was from the edge of the clients screen (horizontally) when the MouseEvent provided in
     * the
     * constructor occurred. This is a percentage equal to the mouse x location / the screen width of the client.
     */
    public double getXFromEdge() {
        return xFromEdge;
    }

    /**
     * @return How far the mouse was from the edge of the clients screen (vertically) when the MouseEvent provided in
     * the
     * constructor occurred. This is a percentage equal to the mouse y location / the screen height the client.
     */
    public double getYFromEdge() {
        return yFromEdge;
    }

    /**
     * @return The button that was pressed when the MouseEvent provided in the constructor occurred.
     */
    public int getButton() {
        return button;
    }
}
