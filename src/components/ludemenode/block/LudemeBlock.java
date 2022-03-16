package components.ludemenode.block;

import components.ludemenode.interfaces.*;
import grammar.Constructor;
import grammar.Ludeme;
import model.LudemeNode;
import panels.editor.EditorPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class LudemeBlock extends LudemeNodeComponent {

    private final int WIDTH;
    private int height = 0;

    // TODO: (maybe parent as well)
    // TODO: have list of ludemenodes it is connect to

    // Set widths of different components relative to the width of the block
    public final int HEIGHT_HEADER_COMPONENT = 35;
    public final double WIDTH_PERCENTAGE_CENTER = 0.86;
    public final double WIDTH_PERCENTAGE_SIDE = (1 - WIDTH_PERCENTAGE_CENTER)/2; // there are two sides (east/west)
    public final int WIDTH_CENTER;
    public final int WIDTH_SIDE;

    private final LudemeNode LUDEME_NODE;
    public final EditorPanel EDITOR_PANEL;

    // Colours for different components of Ludeme Block


    private LudemeBlockHeaderComponent headerComponent;
    private LudemeBlockOutgoingConnectionsComponent outgoingConnectionsComponent;
    private LudemeBlockInputsComponent inputsComponent;


    public LudemeBlock(LudemeNode ludemeNode, EditorPanel editorPanel, int width){
        super(ludemeNode, editorPanel);
        this.LUDEME_NODE = ludemeNode;
        this.WIDTH = width;
        this.EDITOR_PANEL = editorPanel;

        this.WIDTH_CENTER = ((int)(WIDTH_PERCENTAGE_CENTER*WIDTH));
        this.WIDTH_SIDE = ((int)(WIDTH_PERCENTAGE_SIDE*WIDTH));
        initialize();
    }


    public void initialize(){
        setLayout(new BorderLayout());
        /*
         * NORTH COMPONENT: HeaderComponent
         *                  - Three parts: LEFT SIDE: Ingoing Connection
         *                                 CENTER: Ludeme Name
         *                                 RIGHT SIDE: Empty, maybe button for constructor choice
         *
         * CENTER COMPONENT: InputsComponent
         *                 - input field for every input in current constructor
         *
         * EAST COMPONENT: OutgoingInputsComponent
         *                - For every input, if LudemeInput: Outgoing Connection
         *
         * WEST COMPONENT: EMPTY
         */

        headerComponent = new LudemeBlockHeaderComponent(this);
        inputsComponent = new LudemeBlockInputsComponent(this);
        JComponent emptySideComponent = (JComponent) Box.createRigidArea(new Dimension(WIDTH_SIDE,5));

        add(headerComponent,BorderLayout.NORTH);
        add(inputsComponent,BorderLayout.CENTER);

        outgoingConnectionsComponent = new LudemeBlockOutgoingConnectionsComponent(this, inputsComponent.getComponentList());
        add(outgoingConnectionsComponent, BorderLayout.EAST);
        height = headerComponent.getPreferredSize().height + inputsComponent.getPreferredSize().height;

        add(emptySideComponent, BorderLayout.WEST);


        setMinimumSize(new Dimension(WIDTH, height));
        setPreferredSize(new Dimension(getMinimumSize().width, height));
        setSize(new Dimension(getMinimumSize().width, getPreferredSize().height));

        setSize(getPreferredSize());

        //this.x = x - getWidth()/2;
        //this.y = y - getHeight()/2;
        this.x = x;
        this.y = y;
        setLocation(x,y);

        setDragListener(outgoingConnectionsComponent);

        revalidate();
        repaint();

        setVisible(true);
    }

    private void setDragListener(LudemeBlockOutgoingConnectionsComponent outgoingConnectionsComponent){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                LudemeBlock.this.x = e.getX();
                LudemeBlock.this.y = e.getY();
                System.out.println("mouse pressed ludemeblock");
                EDITOR_PANEL.ludemeBlockClicked(LudemeBlock.this);
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                e.translatePoint(e.getComponent().getLocation().x - LudemeBlock.this.x, e.getComponent().getLocation().y -LudemeBlock.this.y);
                LudemeBlock.this.setLocation(e.getX(),e.getY());
                for(LudemeConnectionComponent lc : outgoingConnectionsComponent.getConnectionComponentList()){
                    lc.updatePosition();
                }
                headerComponent.getIngoingConnectionComponent().updatePosition();
            }
        });
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public Ludeme getLudeme() {
        return LUDEME;
    }

    public Constructor getCurrentConstructor() {
        return LUDEME_NODE.getCurrentConstructor();
    }

    public void setCurrentConstructor(Constructor c){
        LUDEME_NODE.setCurrentConstructor(c);

        inputsComponent.update();
        outgoingConnectionsComponent.update(inputsComponent.getComponentList());

        height = headerComponent.getPreferredSize().height + inputsComponent.getPreferredSize().height;


        setMinimumSize(new Dimension(WIDTH, height));
        setPreferredSize(new Dimension(getMinimumSize().width, height));
        setSize(new Dimension(getMinimumSize().width, getPreferredSize().height));
        revalidate();
        repaint();
    }

    public LudemeConnectionComponent getIngoingConnectionComponent(){
        return headerComponent.getIngoingConnectionComponent();
    }

    public void setWidth(int width){
        // TODO
    }
}
