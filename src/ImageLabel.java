import javax.swing.*;

/**
 * A wrapper class for the JLabel designed to make using the JLabel for displaying images easier.
 */
class ImageLabel extends JLabel {
    /**
     * Constructs a new imageLabel with the specified ImageIcon as its icon.
     * @param icon The ImageIcon that the imageLabel should initially display.
     */
    public ImageLabel(ImageIcon icon) {
        setIcon(icon);
        setIconTextGap(0);
        setBorder(null);
        setText(null);
        setSize(icon.getImage().getWidth(null), icon.getImage().getHeight(null));
    }

    /**
     * Sets the icon of the imageLabel.
     * @param icon The image icon that the imageLabel should display.
     */
    public void setImage(ImageIcon icon) {
        setIcon(icon);
        setSize(icon.getImage().getWidth(null), icon.getImage().getHeight(null));
    }
}