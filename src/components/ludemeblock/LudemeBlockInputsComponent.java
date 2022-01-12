package components.ludemeblock;

import components.ludemeblock.grammar.input.Input;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LudemeBlockInputsComponent extends JPanel {

    private final int INPUT_COMPONENT_HEIGHT = 36;
    private List<JComponent> componentList = new ArrayList<>();
    //private final int PADDING = 5; // padding between arg0 and headerComponent

    public LudemeBlockInputsComponent(LudemeBlock ludemeBlock){
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setAlignmentX(LEFT_ALIGNMENT);
        setBackground(LudemeBlock.MAIN_COLOR);

        // for every input in current constructor add input field
        for(Input i : ludemeBlock.getCurrentConstructor().getInputs()){
            InputComponent ic = new InputComponent(ludemeBlock, i);
            ic.setAlignmentX(Component.LEFT_ALIGNMENT);
            componentList.add(ic);
            add(ic);
            ludemeBlock.setHeight(ludemeBlock.getHeight()+ic.getHeight());
            revalidate();
        }

        setSize(ludemeBlock.WIDTH_CENTER,getPreferredSize().height);
        revalidate();
        repaint();
        setVisible(true);

    }

    public List<JComponent> getComponentList(){
        return componentList;
    }

}
