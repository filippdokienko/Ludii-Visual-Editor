package components.ludemeblockOLD;

import javax.swing.*;
import java.awt.*;

public class PlaceholderComponent extends JComponent {

    private int width, height;
    private Color color;

    public PlaceholderComponent(int width, int height, Color color){
        this.width = width;
        this.height = height;
        this.color = color;
        setPreferredSize(new Dimension(width,height));
        setSize(getPreferredSize());
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(color);
        g.fillRect(0,0,getPreferredSize().width,getPreferredSize().height);
    }

}
