import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A util class to make scaling images easier.
 */
public class ImageUtil {
    /**
     * Returns a new image with the specified width and height.
     * @param srcImg The image that the resized image will use.
     * @param w The width of the resized image.
     * @param h The height of the resized image.
     * @return A new image with the graphics of the provided image and the specified width and height.
     */
    public static Image getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }
}
