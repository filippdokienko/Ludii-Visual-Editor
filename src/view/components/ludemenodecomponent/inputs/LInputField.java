package view.components.ludemenodecomponent.inputs;

import handler.Handler;
import model.LudemeNode;
import model.grammar.Ludeme;
import model.grammar.input.ChoiceInput;
import model.grammar.input.Input;
import model.grammar.input.LudemeInput;
import model.grammar.input.TerminalInput;
import view.components.DesignPalette;
import view.components.ludemenodecomponent.LudemeNodeComponent;
import view.panels.IGraphPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an input field used to supply an argument to a ludeme.
 * It is displayed in the LudemeNodeComponent.
 */

public class LInputField extends JComponent{

    private final Input INPUT;
    private final LudemeNodeComponent LNC;
    private final int INPUT_INDEX;
    private JComponent inputFieldComponent;
    private LConnectionComponent connectionComponent;

    public LInputField(LudemeNodeComponent ludemeNodeComponent, Input input, int inputIndex){
        this.LNC = ludemeNodeComponent;
        this.INPUT = input;
        this.INPUT_INDEX = inputIndex;

        addInputField();

        revalidate();
        repaint();
    }

    private void addInputField() {

        /*
             CASES:
                1. Optional: (a) Label: "add optional argument" , (b) icon button
                2. Choice: (a) Label: "choice", (b) icon button, (c) connection component || when connected -> (a) Label = ludeme name
                3. Collection: (a) Label: "add <name>", (b) icon button

                All but 1. are strictly non-terminal
         */

        if(INPUT.isOptional()){
            JLabel label = new JLabel("Add optional argument: " + INPUT.getName());
            label.setFont(DesignPalette.LUDEME_INPUT_FONT);
            label.setForeground(DesignPalette.FONT_LUDEME_INPUTS_COLOR);

            LInputButton addOptionalArgumentButton = new LInputButton(DesignPalette.OPTIONAL_ICON_ACTIVE, DesignPalette.OPTIONAL_ICON_HOVER);

            // TODO: Hover
            // TODO: Button Listener

            setLayout(new FlowLayout(FlowLayout.RIGHT));

            add(Box.createHorizontalStrut(10));
            add(label);
            add(Box.createHorizontalStrut(5));
            add(addOptionalArgumentButton);

            return;
        }

        JLabel label = new JLabel(INPUT.getName());
        label.setFont(DesignPalette.LUDEME_INPUT_FONT);
        label.setForeground(DesignPalette.FONT_LUDEME_INPUTS_COLOR);

        if(INPUT.isTerminal()){
            inputFieldComponent = ((TerminalInput)INPUT).getComponent();
            inputFieldComponent.setPreferredSize(new Dimension(((int)((LNC.getWidth()-label.getPreferredSize().width)*0.8)),inputFieldComponent.getPreferredSize().height));
            inputFieldComponent.addMouseListener(userInputListener);

            setLayout(new FlowLayout(FlowLayout.LEFT));
            add(Box.createHorizontalStrut(10));
            add(label);
            add(inputFieldComponent);
        }
        else {
            setLayout(new FlowLayout(FlowLayout.RIGHT));
            add(label);

            if (INPUT.isChoice()) {
                label.setText("Choice");
                LInputButton addChoiceButton = new LInputButton(DesignPalette.CHOICE_ICON_ACTIVE, DesignPalette.CHOICE_ICON_HOVER);

                // TODO: Hover
                // TODO: Button Listener

                add(Box.createHorizontalStrut(10));
                add(addChoiceButton);
            }
            // TODO: Collection
            add(Box.createHorizontalStrut(5));
            connectionComponent = new LConnectionComponent(this, label.getPreferredSize().height, (int) (label.getPreferredSize().height * 0.4), false);
            add(connectionComponent);
        }
    }

    public int getInputIndex() {
        return INPUT_INDEX;
    }

    public Input getInput() {
        return INPUT;
    }

    /**
     * If this input field requires ludemes, it will return a list of all possible ludemes that may be supplied to the field as input.
     * @return List of possible ludemes
     */
    public List<Ludeme> getRequiredLudemes(){
        List<Ludeme> requiredLudemes = new ArrayList<>();

        if(INPUT.isTerminal()) {
          return requiredLudemes;
        }
        if(INPUT instanceof LudemeInput) {
            requiredLudemes.add(((LudemeInput) INPUT).getRequiredLudeme());
            return requiredLudemes;
        }
        if(INPUT instanceof ChoiceInput) {
            for(Input input : ((ChoiceInput) INPUT).getInputs()) {
                requiredLudemes.add(((LudemeInput) input).getRequiredLudeme());
            }
            return requiredLudemes;
        }
        return null;
    }

    /**
     * Returns the user supplied input for an input field
     * @return
     */
    public Object getUserInput(){
        if(inputFieldComponent == null){
            // then its ludeme input
            return connectionComponent.getConnectedTo().getLudemeNode();
        }
        if(inputFieldComponent instanceof JTextField) return ((JTextField)inputFieldComponent).getText();
        if(inputFieldComponent instanceof JSpinner) return ((JSpinner)inputFieldComponent).getValue();
        if(inputFieldComponent instanceof JComboBox) return ((JComboBox)inputFieldComponent).getSelectedItem();

        // TODO: What about ludeme inputs? required?

        return null;
    }

    // Updates the provided input list in the model whenever the mouse moves over this input field
    MouseListener userInputListener = new MouseAdapter() {
        @Override
        public void mouseExited(MouseEvent e) {
            super.mouseMoved(e);
            Handler.updateInput(LNC.getGraphPanel().getGraph(), LNC.getLudemeNode(), getInputIndex(), getUserInput());
            System.out.println("Updated input " + getInputIndex() + " to " + getUserInput());
        }
    };

    public void setUserInput(Object input){
        if(inputFieldComponent == null){
            // then its ludeme input
            IGraphPanel graphPanel = LNC.getGraphPanel();
            graphPanel.addConnection(connectionComponent, graphPanel.getNodeComponent((LudemeNode) input).getIngoingConnectionComponent());
        }
        if(inputFieldComponent instanceof JTextField) ((JTextField)inputFieldComponent).setText((String)input);
        if(inputFieldComponent instanceof JSpinner) ((JSpinner)inputFieldComponent).setValue(input);
        if(inputFieldComponent instanceof JComboBox) ((JComboBox)inputFieldComponent).setSelectedItem(input);
    }

    public LConnectionComponent getConnectionComponent(){
        return connectionComponent;
    }

    public LudemeNodeComponent getLuDemeNodeComponent(){
        return LNC;
    }

}
