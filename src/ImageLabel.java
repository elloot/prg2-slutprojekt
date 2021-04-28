import javax.swing.*;
import java.awt.image.BufferedImage;

class ImageLabel extends JLabel {
    public ImageLabel(BufferedImage im) {
        ImageIcon icon = new ImageIcon(im);
        setIcon(icon);
        setIconTextGap(0);
        setBorder(null);
        setText(null);
        setSize(icon.getImage().getWidth(null) / 2, icon.getImage().getHeight(null) / 2);
    }

    public ImageLabel() {
        setIconTextGap(0);
        setBorder(null);
        setText(null);
    }

    public void setImage(BufferedImage im) {
        ImageIcon icon = new ImageIcon(im);
        setIcon(icon);
        setSize(icon.getImage().getWidth(null) / 2, icon.getImage().getHeight(null) / 2);
    }
}