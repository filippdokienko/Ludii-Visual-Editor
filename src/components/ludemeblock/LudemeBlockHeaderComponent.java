package components.ludemeblock;


import javax.swing.*;
import java.awt.*;

public class LudemeBlockHeaderComponent extends JPanel {

    private LudemeConnectionComponent ingoingConnectionComponent;

    public LudemeBlockHeaderComponent(LudemeBlock ludemeBlock){
        setPreferredSize(new Dimension(ludemeBlock.getWidth(), ludemeBlock.HEIGHT_HEADER_COMPONENT));
        setSize(getPreferredSize());
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(LudemeBlock.HEADER_COLOR);
        setOpaque(false); // TODO: ?

        int padding_left_center = 5;

        JLabel ludemeNameLabel = new JLabel(ludemeBlock.getLudeme().getName());
        ludemeNameLabel.setPreferredSize(new Dimension(ludemeBlock.WIDTH_CENTER - padding_left_center, getPreferredSize().height));
        ludemeNameLabel.setSize(getPreferredSize());
        ludemeNameLabel.setFont(LudemeBlock.HEADER_FONT);
        ludemeNameLabel.setForeground(LudemeBlock.HEADER_FONT_COLOR);

        // LEFT SIDE: Ingoing Connection
        // TODO: Center vertically and horizontally
        ingoingConnectionComponent = new LudemeConnectionComponent(ludemeBlock,ludemeBlock.WIDTH_SIDE,ludemeBlock.HEIGHT_HEADER_COMPONENT, ludemeBlock.HEIGHT_HEADER_COMPONENT/3,false);
        add(ingoingConnectionComponent);
        //add(new PlaceholderComponent(ludemeBlock.WIDTH_SIDE, (int)ludemeNameLabel.getPreferredSize().getHeight(), Color.RED));
        // PADDING BETWEEN LEFT & CENTER
        add(Box.createRigidArea(new Dimension(padding_left_center,1)));
        // CENTER: Ludeme Name
        add(ludemeNameLabel);
        // RIGHT SIDE: Empty
        JComponent emptyComponent = (JComponent) Box.createRigidArea(new Dimension(ludemeBlock.WIDTH_SIDE,5));
        add(emptyComponent);

        setVisible(true);
        revalidate();
        repaint();
    }

    public void updatePosition(){
        this.ingoingConnectionComponent.updatePosition();
    }

}