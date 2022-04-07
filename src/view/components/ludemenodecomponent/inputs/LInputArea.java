package view.components.ludemenodecomponent.inputs;

import model.grammar.input.Input;
import view.components.DesignPalette;
import view.components.ludemenodecomponent.LudemeNodeComponent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LInputArea extends JPanel {

    private List<LInputField> inputFields = new ArrayList<>();;
    private LudemeNodeComponent LNC;


    public LInputArea(LudemeNodeComponent ludemeNodeComponent) {
        LNC = ludemeNodeComponent;
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setAlignmentX(LEFT_ALIGNMENT);
        setBackground(DesignPalette.BACKGROUND_LUDEME_BODY);

        List<Input> inputs = ludemeNodeComponent.getLudemeNode().getCurrentConstructor().getInputs();
        System.out.println("current constructor: " + ludemeNodeComponent.getLudemeNode().getCurrentConstructor());

        for(int i = 0; i < inputs.size(); i++){
            Input in = inputs.get(i);

            LInputField inputField = new LInputField(ludemeNodeComponent, in, i);
            inputField.setAlignmentX(LEFT_ALIGNMENT);
            inputFields.add(inputField);
            add(inputField);
            revalidate();
        }

        int preferredHeight = getPreferredSize().height;
        setSize(new Dimension(LNC.getWidth(), preferredHeight));

        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        repaint();
        revalidate();
        setVisible(true);
    }

    public void updateConstructor(){
        removeAll();
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setAlignmentX(LEFT_ALIGNMENT);
        setBackground(DesignPalette.BACKGROUND_LUDEME_BODY);

        inputFields.clear();

        List<Input> inputs = LNC.getLudemeNode().getCurrentConstructor().getInputs();
        System.out.println("current constructor: " + LNC.getLudemeNode().getCurrentConstructor());

        for(int i = 0; i < inputs.size(); i++){
            Input in = inputs.get(i);

            LInputField inputField = new LInputField(LNC, in, i);
            inputField.setAlignmentX(LEFT_ALIGNMENT);
            inputFields.add(inputField);
            add(inputField);
            revalidate();
        }

        int preferredHeight = getPreferredSize().height;

        setSize(new Dimension(LNC.getWidth(), preferredHeight));

        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        repaint();
        revalidate();
        setVisible(true);
    }

    public void updateProvidedInputs(){
        // Fill existing inputs
        Object[] providedInputs = LNC.getLudemeNode().getProvidedInputs();
        for(int i = 0; i < providedInputs.length; i++){
            Object providedInput = providedInputs[i];
            if(providedInput != null){
                // find the inputfield with same index
                LInputField inputField = inputFields.get(i);
                if(inputField.getInputIndex() != i){
                    for(int j = 0; j < inputFields.size(); j++){
                        if(inputFields.get(j).getInputIndex() == i){
                            inputField = inputFields.get(j);
                        }
                    }
                }
                inputField.setUserInput(providedInput);
            }
        }

        int preferredHeight = getPreferredSize().height;
        setSize(new Dimension(LNC.getWidth(), preferredHeight));

        repaint();
        revalidate();
        setVisible(true);

    }

    public void updatePosition(){
        for(LInputField inputField : inputFields){
            if(inputField.getConnectionComponent() != null){
                inputField.getConnectionComponent().updatePosition();
            }
        }
    }

}
