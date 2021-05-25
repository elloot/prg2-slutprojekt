import javax.swing.*;

class ImageLabel extends JLabel {
    public ImageLabel(ImageIcon icon) {
        setIcon(icon);
        setIconTextGap(0);
        setBorder(null);
        setText(null);
        setSize(icon.getImage().getWidth(null), icon.getImage().getHeight(null));
    }

    public void setImage(ImageIcon icon) {
        setIcon(icon);
        setSize(icon.getImage().getWidth(null), icon.getImage().getHeight(null));
    }
}