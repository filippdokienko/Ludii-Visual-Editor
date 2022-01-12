package components.ludemeblockOLD;

/**
 * JComponent that visually represents a ludeme as a block/node
 * @author Filipp Dokienko
 */

import components.ludemeblockOLD.tempgrammar.Input;
import components.ludemeblockOLD.tempgrammar.Ludeme;
import components.ludemeblockOLD.tempgrammar.inputtypes.InputTypesAllPossible;
import panels.editor.EditorPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

public class LudemeBlock extends JComponent {

    // Colours for different components of Ludeme Block
    private static final Color HEADER_COLOR = new Color(230,230,230);
    private static final Color MAIN_COLOR = new Color(241,241,241);
    private static final Color CONNECTION_POINT_COLOR = new Color(112,112,112);

    private static final Color HEADER_FONT_COLOR = new Color(110,110,110);
    private static final Font HEADER_FONT = new Font(Font.SANS_SERIF, Font.BOLD,  16);
    private static final Color MAIN_FONT_COLOR = new Color(110,110,110);
    private static final Font MAIN_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 12);

    // height and position
    int height, width;
    int x,y;

    // Ludeme that is represented as a block/node
    Ludeme ludeme;

    // all LudemeConnectionComponents
    private ArrayList<LudemeConnectionComponent> connectionComponentList = new ArrayList();

    // Editor Panel
    static EditorPanel editorPanel;

    /**
     *
     * @param x Center x-position of ludeme block
     * @param y Center y-position of ludeme block
     * @param width Width of ludeme block
     * @param height Initial height of ludeme block
     * @param ludeme Ludeme to be represented
     */
    public LudemeBlock(int x, int y, int width, int height, Ludeme ludeme){
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.ludeme = ludeme;

        initialize();

    }

    public static void setEditorPanel(EditorPanel editorPanel){
        LudemeBlock.editorPanel = editorPanel;
    }


    /**
     * Initializes all graphical components of the block.
     * Uses a BorderLayout:
     *          NORTH:   HeaderComponent; Name of Ludeme
     *          EAST:    ConnectionComponent; Potential input points for other Ludemes
     *          CENTER:  MainComponent; List of InputComponents; InputComponent depended on InputType
     */
    private void initialize(){
        setLayout(new BorderLayout());

        // Set widths of different components relative to the width of the block
        double widthPercentageMiddle = 0.94;
        double widthPercentageSide = 1 - widthPercentageMiddle;
        int heightHeaderComponent = 35;
        int widthCenter = ((int)(widthPercentageMiddle*width));
        int widthSide = width - widthCenter;

        /**
         * Class representing the HeaderComponent.
         * JLabel + Placeholder
         */
        class HeaderComponent extends JPanel{

            public HeaderComponent(){
                setPreferredSize(new Dimension(width, heightHeaderComponent));
                setSize(getPreferredSize());
                setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
                setBackground(HEADER_COLOR);
                setOpaque(true);

                // JLabel for the Ludeme's name
                JLabel label = new JLabel(ludeme.getName(), SwingConstants.CENTER);
                label.setPreferredSize(new Dimension(widthCenter, getPreferredSize().height));
                label.setSize(getPreferredSize());
                label.setFont(HEADER_FONT);
                label.setForeground(HEADER_FONT_COLOR);
                // Placeholder component with same colour as background -> transparent effect. This way 'label' is centered to the mainComponent
                PlaceholderComponent placeholder = new PlaceholderComponent(widthSide, label.getSize().height, EditorPanel.BACKGROUND_COLOR);//new Color(233,233,233)); // TODO: Make really transparent

                add(label);
                add(placeholder);

                setVisible(true);
                revalidate();
                repaint();
            }
        }

        JComponent headerComponent = new HeaderComponent();

        JPanel mainComponent = new JPanel();
        mainComponent.setLayout(new BoxLayout(mainComponent,BoxLayout.Y_AXIS));
        mainComponent.setAlignmentX(LEFT_ALIGNMENT);
        mainComponent.setBackground(MAIN_COLOR);

        int inputComponentHeight = 36;

        // adds padding between arg0 & header component
        int paddingHeight = 5;
        mainComponent.add(Box.createRigidArea(new Dimension(0, paddingHeight)));
        this.height+=paddingHeight;

        // add input component for every input of ludeme
        for(Input in: ludeme.getInputs()){
            InputComponent ic = new InputComponent(widthCenter, inputComponentHeight, in, MAIN_FONT, MAIN_FONT_COLOR);
            ic.setAlignmentX(Component.LEFT_ALIGNMENT);
            mainComponent.add(ic);
            this.height+=inputComponentHeight;
            mainComponent.revalidate();
        }
        mainComponent.setSize(widthCenter,mainComponent.getPreferredSize().height);


        JPanel connectionsComponent = new JPanel();
        connectionsComponent.setLayout(new BoxLayout(connectionsComponent, BoxLayout.Y_AXIS));
        connectionsComponent.setSize(new Dimension(widthSide, height));
        for(Input in: ludeme.getInputs()){
            if(in.getInputType().equals(InputTypesAllPossible.LUDEME)){
                //connectionsComponent.add(new PlaceholderComponent(widthSide, inputComponentHeight, Color.RED));
                LudemeConnectionComponent lc = new LudemeConnectionComponent(widthSide, inputComponentHeight, CONNECTION_POINT_COLOR, editorPanel);
                connectionComponentList.add(lc);
                lc.setAlignmentX(Component.CENTER_ALIGNMENT);
                connectionsComponent.add(lc);
            }
            else{
                connectionsComponent.add(Box.createRigidArea(new Dimension(widthSide, inputComponentHeight)));
            }
            connectionsComponent.revalidate();
        }
        // set background of connectionsComponent transparent
        connectionsComponent.setOpaque(false);

        add(headerComponent, BorderLayout.NORTH);
        add(mainComponent, BorderLayout.CENTER);
        add(connectionsComponent, BorderLayout.EAST);

        setMinimumSize(new Dimension(width, height));
        setPreferredSize(new Dimension(getMinimumSize().width, getPreferredSize().height));
        setSize(new Dimension(getMinimumSize().width, getPreferredSize().height));
        x = x - getWidth()/2;
        y = y - getHeight()/2;
        setLocation(x,y);

        revalidate();
        repaint();

        setDragListener();

    }


    private void setDragListener(){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                LudemeBlock.this.x = e.getX();
                LudemeBlock.this.y = e.getY();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                e.translatePoint(e.getComponent().getLocation().x - LudemeBlock.this.x, e.getComponent().getLocation().y - LudemeBlock.this.y);
                LudemeBlock.this.setLocation(e.getX(),e.getY());
                for(LudemeConnectionComponent lc : connectionComponentList){
                    lc.updatePosition();
                }
            }
        });
    }


}
