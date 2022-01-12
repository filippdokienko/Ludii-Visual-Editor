package components.ludemeblock;

import components.ludemeblock.grammar.input.Input;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LudemeBlockOutgoingConnectionsComponent extends JPanel {

    private List<LudemeConnectionComponent> connectionComponentList = new ArrayList();

    public LudemeBlockOutgoingConnectionsComponent(LudemeBlock ludemeBlock, List<JComponent> inputComponents){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setSize(new Dimension(ludemeBlock.WIDTH_SIDE, ludemeBlock.getHeight()));
        for(int i = 0; i < ludemeBlock.getCurrentConstructor().getInputs().size(); i++){
            Input in = ludemeBlock.getCurrentConstructor().getInputs().get(i);
            int inputComponentHeight = (int) inputComponents.get(i).getPreferredSize().getHeight();
            if(!in.isTerminal()){
                LudemeConnectionComponent lc = new LudemeConnectionComponent(ludemeBlock,ludemeBlock.WIDTH_SIDE,inputComponentHeight, ludemeBlock.WIDTH_SIDE/2, true);
                connectionComponentList.add(lc);
                lc.setAlignmentX(Component.CENTER_ALIGNMENT);
                add(lc);
            }
            else{
                add(Box.createRigidArea(new Dimension(ludemeBlock.WIDTH_SIDE, inputComponentHeight)));
            }
            revalidate();
        }

        // set background of connectionsComponent transparent
        setOpaque(false);
    }

    public List<LudemeConnectionComponent> getConnectionComponentList(){
        return connectionComponentList;
    }

}
