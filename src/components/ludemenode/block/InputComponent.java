package components.ludemenode.block;

/**
 * Represents an input field
 */

import components.DesignPalette;
import grammar.input.Input;
import grammar.input.TerminalInput;

import javax.swing.*;
import java.awt.*;

public class InputComponent extends JComponent {

    public final Input INPUT;
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
        inputNameLabel.setFont(DesignPalette.LUDEME_INPUT_FONT);
        inputNameLabel.setForeground(DesignPalette.FONT_LUDEME_INPUTS_COLOR);
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
