package components.ludemeblockOLD;

/**
 * Represents the input for a Ludeme as a circle next to the node.
 * @author Filipp Dokienko
 */

import panels.editor.EditorPanel;

import components.ludemeblock.CustomPoint;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LudemeConnectionComponent extends JComponent {

    private int width, height;
    private final Color COLOR;
    private EditorPanel editorPanel;
    public ConnectionPointComponent connectionPointComponent;
    public CustomPoint position;


    /**
     * Represents the circle
     *
     */
    class ConnectionPointComponent extends JComponent{
        public boolean fill = false;
        public int x,y;

        public ConnectionPointComponent(){
            setClickListener();
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;


            double percentageFill = 0.75;

            x = 2;
            y = (height / 2) - ((int) (width * percentageFill));


            // if fill = true, draw a filled circle. otherwise, the contour only
            if(fill) {
                g2.setColor(COLOR);
                g2.fillOval(x, y, ((int) (width * percentageFill)), ((int) (width * percentageFill)));
            }
            else {
                // fill a new oval with transparent colour (to make the filled out oval disappear)
                g2.setColor(new Color(0,0,0,0));
                g2.fillOval(x,y, ((int) (width * percentageFill)), ((int) (width * percentageFill)));
                // draw unfilled oval
                g2.setColor(COLOR);
                g2.drawOval(x,y, ((int) (width * percentageFill)), ((int) (width * percentageFill)));
            }
        }

        // if circle is clicked, fill/unfill circle
        private void setClickListener(){
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    fill = !fill;
                    repaint();
                    revalidate();
                    // if fill -> add edge
                    if(fill){
                        // position in jframe
                        updatePosition();
                        //editorPanel.addEdge(position);
                    }
                }
            });
        }
    }

    /**
     * Constructor
     * @param width
     * @param height
     * @param color
     */
    public LudemeConnectionComponent(int width, int height, Color color, EditorPanel editorPanel){
        this.editorPanel = editorPanel;

        this.width = width;
        this.height = height;
        this.COLOR = color;
        setPreferredSize(new Dimension(width,height));
        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());
        setSize(getPreferredSize());
        setLocation(0,0);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        connectionPointComponent = new ConnectionPointComponent();
        connectionPointComponent.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(connectionPointComponent);

        repaint();
        revalidate();
        setVisible(true);

    }

    public void updatePosition(){
        int x = connectionPointComponent.getX() + this.getX() + this.getParent().getX() + this.getParent().getParent().getX() + connectionPointComponent.getWidth()/2 - connectionPointComponent.x;// + this.getParent().getParent().getParent().getX() + this.getParent().getParent().getParent().getParent().getX();
        int y = connectionPointComponent.getY() + this.getY() + this.getParent().getY() + this.getParent().getParent().getY() + connectionPointComponent.getHeight()/2 - connectionPointComponent.y;// + this.getParent().getParent().getParent().getY() + this.getParent().getParent().getParent().getParent().getY();
        //Point p = SwingUtilities.convertPoint(connectionPointComponent, connectionPointComponent.getX(), connectionPointComponent.getY()-connectionPointComponent.getHeight()/2, SwingUtilities.getWindowAncestor(this));
        //EditorPanel editorPanel = (EditorPanel) this.getParent().getParent().getParent();
        Point p = new Point(x,y);
        if(position == null){
            position = new CustomPoint(p);
        }
        position.update(p);
    }

}
