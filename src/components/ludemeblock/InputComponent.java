package components.ludemeblock;

/**
 * Represents an input field
 */

import components.ludemeblock.grammar.input.Input;
import components.ludemeblock.grammar.input.TerminalInput;

import javax.swing.*;
import java.awt.*;

public class InputComponent extends JComponent {

    private final Input INPUT;
    private final LudemeBlock LUDEME_BLOCK;

    public InputComponent(LudemeBlock ludemeBlock, Input input){
        this.LUDEME_BLOCK = ludemeBlock;
        this.INPUT = input;

        addInputTypeField();

        revalidate();
        repaint();

    }

    private void addInputTypeField() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel inputNameLabel = new JLabel(INPUT.getName());
        inputNameLabel.setFont(LudemeBlock.MAIN_FONT);
        inputNameLabel.setForeground(LudemeBlock.MAIN_FONT_COLOR);
        add(Box.createRigidArea(new Dimension(10, 0)));

        add(inputNameLabel);

        JComponent component = null;

        // different component based on input type
        if(INPUT.isTerminal()){
            component = ((TerminalInput)INPUT).getComponent();
        }
        else {
            // TODO: Collection, Choice, Optional
            // otherwise it's a ludeme input -> only text
        }
        if(component!=null) {
            component.setPreferredSize(new Dimension(((int) (0.8 * LUDEME_BLOCK.WIDTH_CENTER) - inputNameLabel.getPreferredSize().width), component.getPreferredSize().height));
            add(component);
        }

    }

}
