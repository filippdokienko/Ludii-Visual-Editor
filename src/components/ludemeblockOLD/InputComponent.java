package components.ludemeblockOLD;

import components.ludemeblockOLD.tempgrammar.Input;

import javax.swing.*;
import java.awt.*;

public class InputComponent extends JComponent {

    private int width, height;

    private final Font FONT;
    private final Color FONT_COLOR;

    /*
        Possible Input Types:
                - Ludeme
                - String
                - Integer
                - Collection
                    - Strings
                    - Integers
     */

    private Input INPUT;

    public InputComponent(int width, int height, Input input, Font font, Color fontColor){
        this.width = width;
        this.height = height;

        this.INPUT = input;

        this.FONT = font;
        this.FONT_COLOR = fontColor;

        addInputTypeField();

        revalidate();
        repaint();

    }

    private void addInputTypeField() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel inputNameLabel = new JLabel(INPUT.getName());
        inputNameLabel.setFont(FONT);
        inputNameLabel.setForeground(FONT_COLOR);
        add(Box.createRigidArea(new Dimension(10, 0)));

        add(inputNameLabel);

        JComponent component = INPUT.getInputType().getJComponent();
        if (component != null) {
            if(INPUT.getInputType().isCollection()){
                component = new JComboBox(INPUT.getCollectionValues().toArray());
                //((JComboBox) component).addItem(INPUT.getCollectionValues());
            }
            component.setPreferredSize(new Dimension(((int) (0.8 * width) - inputNameLabel.getPreferredSize().width), component.getPreferredSize().height));
            add(component);
        }
    }

}
