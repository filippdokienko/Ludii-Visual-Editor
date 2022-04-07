package view.components.ludemenodecomponent;

import model.grammar.Constructor;
import view.components.DesignPalette;
import view.components.ludemenodecomponent.inputs.LIngoingConnectionComponent;

import javax.swing.*;
import java.awt.*;

public class LHeader extends JComponent {

    private LIngoingConnectionComponent ingoingConnectionComponent;
    private LudemeNodeComponent LNC;

    public LHeader(LudemeNodeComponent ludemeNodeComponent) {
        LNC = ludemeNodeComponent;

        setLayout(new BorderLayout());

        JLabel title = new JLabel(ludemeNodeComponent.getLudemeNode().getLudeme().getName());

        title.setFont(DesignPalette.LUDEME_TITLE_FONT);
        title.setForeground(DesignPalette.FONT_LUDEME_TITLE_COLOR);
        title.setSize(title.getPreferredSize());

        ingoingConnectionComponent = new LIngoingConnectionComponent(this, title.getHeight(), ((int)(title.getHeight()*0.4)), false);

        JPanel connectionAndTitle = new JPanel(new FlowLayout(FlowLayout.LEFT));
        connectionAndTitle.add(ingoingConnectionComponent);
        connectionAndTitle.add(Box.createHorizontalStrut(5));
        connectionAndTitle.add(title);
        connectionAndTitle.setOpaque(false);

        add(connectionAndTitle, BorderLayout.LINE_START);
        JComboBox<Constructor> constructorPicker = new JComboBox<>();
        for(Constructor c : ludemeNodeComponent.getLudemeNode().getLudeme().getConstructors()){
            constructorPicker.addItem(c);
        }
        constructorPicker.addActionListener(e -> {
            ludemeNodeComponent.changeConstructor((Constructor) constructorPicker.getSelectedItem());
            repaint();
                });

        add(constructorPicker, BorderLayout.LINE_END);


        //int width = title.getPreferredSize().width + ingoingConnectionComponent.getPreferredSize().width;
        //int height = title.getPreferredSize().height;

        setPreferredSize(new Dimension(ludemeNodeComponent.getWidth(), getPreferredSize().height));
        setSize(getPreferredSize());

        setOpaque(false);

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
