package panels.header;

public class SVGtoBufferedImage {
    public static java.awt.image.BufferedImage convert(java.awt.image.BufferedImage image, java.awt.Color bgColor) {
        java.awt.Graphics2D g2 = image.createGraphics();
        g2.setBackground(bgColor);
        g2.clearRect(0, 0, image.getWidth(), image.getHeight());
        g2.dispose();
        return image;
    }
}
