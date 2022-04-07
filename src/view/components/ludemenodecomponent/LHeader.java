package view.components.ludemenodecomponent;

import view.components.DesignPalette;
import view.components.ludemenodecomponent.inputs.LIngoingConnectionComponent;

import javax.swing.*;
import java.awt.*;

public class LHeader extends JComponent {

    private LIngoingConnectionComponent ingoingConnectionComponent;
    private LudemeNodeComponent LNC;

    public LHeader(LudemeNodeComponent ludemeNodeComponent) {
        LNC = ludemeNodeComponent;

        setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel title = new JLabel(ludemeNodeComponent.getLudemeNode().getLudeme().getName());

        title.setFont(DesignPalette.LUDEME_TITLE_FONT);
        title.setForeground(DesignPalette.FONT_LUDEME_TITLE_COLOR);
        title.setSize(title.getPreferredSize());

        ingoingConnectionComponent = new LIngoingConnectionComponent(this, title.getHeight(), ((int)(title.getHeight()*0.4)), false);
        add(ingoingConnectionComponent);
        add(Box.createHorizontalStrut(5));
        add(title);

        //int width = title.getPreferredSize().width + ingoingConnectionComponent.getPreferredSize().width;
        //int height = title.getPreferredSize().height;

        //setPreferredSize(new Dimension(width, height));
        setSize(getPreferredSize());

        System.out.println("LHeader: " + getSize());

        revalidate();
        repaint();
        setVisible(true);
    }

    public void updatePosition(){
        ingoingConnectionComponent.updatePosition();
    }

    public LIngoingConnectionComponent getIngoingConnectionComponent() {
        return ingoingConnectionComponent;
    }

    public LudemeNodeComponent getLudemeNodeComponent() {
        return LNC;
    }

}
