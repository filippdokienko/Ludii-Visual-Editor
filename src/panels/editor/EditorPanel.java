package panels.editor;

import components.ludemenode.CustomPoint;
import components.ludemenode.block.LudemeBlock;
import components.ludemenode.block.LudemeConnectionComponent;
import components.ludemenode.LudemeConnection;
import components.ludemenode.interfaces.LudemeNode;
import grammar.Constructor;
import grammar.Ludeme;
import grammar.parser.Parser;
import components.DesignPalette;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

public class EditorPanel extends JPanel {

    private EditorPanel editorPanel = this;

    /*

    TODO:
        - Ludeme ingoing connection component not centered
        - Zoom in/out
        - connection by dragging
        - connection only between ingoing & outgoing
        - header background

     */

    public List<LudemeConnection> list_edges = new ArrayList<>();

    private void addConnection(CustomPoint p1, CustomPoint p2){
        list_edges.add(new LudemeConnection(p1, p2));
    }
    private void addConnection(LudemeConnectionComponent outgoingConnectionComponent, LudemeConnectionComponent ingoingConnectionComponent){
        CustomPoint ingoingPosition = ingoingConnectionComponent.getPosition();
        CustomPoint outgoingPosition = outgoingConnectionComponent.getPosition();
        addConnection(outgoingPosition, ingoingPosition);

        ingoingConnectionComponent.fill();
        outgoingConnectionComponent.fill();

        selectedConnectionComponent = null;
        currentMousePoint = null;
        repaint();
    }

    private LudemeConnectionComponent selectedConnectionComponent;
    private Point currentMousePoint;

    public void connectNewConnection(LudemeConnectionComponent connectionComponent){
        if(selectedConnectionComponent != null && connectionComponent != null){
            if(selectedConnectionComponent.isOutgoing() && !connectionComponent.isOutgoing() && selectedConnectionComponent.getLudemeBlock() != connectionComponent.getLudemeBlock()){
                addConnection(selectedConnectionComponent, connectionComponent);
            }
            else {
                selectedConnectionComponent.unfill();
                this.selectedConnectionComponent = connectionComponent;
            }
        } else if (selectedConnectionComponent != null){
            selectedConnectionComponent.unfill();
            this.selectedConnectionComponent = connectionComponent;
        }
        else {
            this.selectedConnectionComponent = connectionComponent;
        }

    }

    public void ludemeBlockClicked(LudemeNode ludemeNode){
        if(selectedConnectionComponent != null && selectedConnectionComponent.isOutgoing() && ludemeNode != selectedConnectionComponent.getLudemeBlock()){
            LudemeConnectionComponent ingoingConnectionComponent = ((LudemeBlock) ludemeNode).getIngoingConnectionComponent();
            addConnection(selectedConnectionComponent, ingoingConnectionComponent);
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();

        // draw new connection
        if(selectedConnectionComponent != null && currentMousePoint != null) {
            paintNewConnection(g2, currentMousePoint);
        }

        // draw existing connections
        paintConnections(g2);

        repaint();
        revalidate();
    }

    private void paintConnections(Graphics2D g2){
        for(LudemeConnection e : list_edges){
            int cp_x = e.p1.x + Math.abs((e.p1.x-e.p2.x)/2);
            int cp1_y = e.p1.y;
            int cp2_y = e.p2.y;

            Path2D p2d = new Path2D.Double();
            p2d.moveTo(e.p1.x, e.p1.y);
            p2d.curveTo(cp_x, cp1_y, cp_x, cp2_y, e.p2.x, e.p2.y);
            g2.draw(p2d);
        }
    }

    private void paintNewConnection(Graphics2D g2, Point mousePosition){
        CustomPoint connection_point = selectedConnectionComponent.getPosition();
        Path2D p2d = new Path2D.Double();

        if(selectedConnectionComponent.isOutgoing()){
            int cp_x = connection_point.x + Math.abs((connection_point.x-mousePosition.x)/2);
            p2d.moveTo(connection_point.x, connection_point.y);
            p2d.curveTo(cp_x, connection_point.y, cp_x, mousePosition.y, mousePosition.x, mousePosition.y);
        }
        else {
            int cp_x = mousePosition.x + Math.abs((mousePosition.x-connection_point.x)/2);
            p2d.moveTo(mousePosition.x, mousePosition.y);
            p2d.curveTo(cp_x, mousePosition.y, cp_x, connection_point.y, connection_point.x, connection_point.y);
        }
        g2.draw(p2d);
    }

    public EditorPanel(int width, int height){
        setLayout(null);
        setBackground(DesignPalette.BACKGROUND_EDITOR);
        setPreferredSize(new Dimension(width, height));
        addMouseListener(new SpawnNodePanelListener());


        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                currentMousePoint = e.getPoint();
                if(selectedConnectionComponent != null){
                    repaint();
                }
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton() == MouseEvent.BUTTON1) {
                    System.out.println("mouse clicked editor panel -> pop up 'create new ludeme'");
                    // TODO: option to create new Ludeme
                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    // cancels creation of new connection
                    connectNewConnection(null);
                }
            }
        });

        Ludeme game = findLudeme("game");
        add(getBlock(game, game.CONSTRUCTORS.get(1)));

        Ludeme players = findLudeme("players");
        add(getBlock(players));

        Ludeme equipment = findLudeme("equipment");
        add(getBlock(equipment));

        Ludeme string = findLudeme("string");
        add(getBlock(string));

        Ludeme integer = findLudeme("int");
        System.out.println(integer.CONSTRUCTORS);
        add(getBlock(integer,integer.CONSTRUCTORS.get(30)));

        Ludeme board = findLudeme("container.board.board");
        add(getBlock(board));

        Ludeme rules = findLudeme("rules.rules");
        add(getBlock(rules));

        Ludeme play = findLudeme("play");
        add(getBlock(play));


        revalidate();
        repaint();

    }


    private LudemeNode getBlock(Ludeme l){
        return new LudemeBlock(0, 0, 300, l, this);
    }

    private LudemeNode getBlock(Ludeme l, Constructor c){
        LudemeNode lb = new LudemeBlock(0,0,300, l,c,this);
        //lb.setCurrentConstructor(c);
        return lb;
    }

    private Ludeme findLudeme(String name){
        for(Ludeme l : ludemes){
            if(l.getName().equals(name)) return l;
        }
        return null;
    }

    Parser p = new Parser();
    List<Ludeme> ludemes = p.getLudemes();
    int cc = 67;

    private class SpawnNodePanelListener extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {

            /*if (e.getButton() == MouseEvent.BUTTON2) {
                Input i1 = new Input("InputTypesAllPossible.STRING input", InputTypesAllPossible.STRING);
                Input i2 = new Input("int dropdown", InputTypesAllPossible.COLLECTION);
                Input i3 = new Input("ludeme input", InputTypesAllPossible.LUDEME);
                Input i4 = new Input("InputTypesAllPossible.STRING input 2", InputTypesAllPossible.STRING);

                ArrayList<Input> inputs1 = new ArrayList<>();
                inputs1.add(i1);
                inputs1.add(i2);
                inputs1.add(i3);
                inputs1.add(i4);
                Ludeme l1 = new Ludeme("play", inputs1);

                LudemeNode b1 = new LudemeBlock(e.getX(),e.getY(), 300,10, l1);
                add(b1);
                revalidate();
                repaint();
            }*/

            if (e.getButton() == MouseEvent.BUTTON2) {
                /*
                Terminal t1 = new Terminal("A1");
                Terminal t2 = new Terminal("A2");

                grammar.input.Input i1 = new TerminalInput("Integer", TerminalInputType.INTEGER);
                grammar.input.Input i2 = new TerminalInput("Dropdown", TerminalInputType.DROPDOWN, Arrays.asList(t1,t2));
                grammar.input.Input i3 = new LudemeInput("Ludeme",null);
                grammar.input.Input i4 = new LudemeInput("Ludeme2",null);
                grammar.input.Input i5 = new TerminalInput("String",TerminalInputType.STRING);

                Constructor c1 = new Constructor(List.of(i1, i2, i3, i4, i5));
                grammar.Ludeme l1 = new grammar.Ludeme("test ludeme", List.of(c1));

                components.ludemeblock.LudemeNode b1 = new components.ludemeblock.LudemeNode(e.getX(), e.getY(),300,l1,editorPanel);

                 */


                Ludeme l = ludemes.get(cc++);
                System.out.println(l.NAME);
                System.out.println(l.CONSTRUCTORS);

                LudemeNode b1 = new LudemeBlock(e.getX(),e.getY(), 300,l,editorPanel);


                add(b1);
                revalidate();
                repaint();
            }
        }
    }
}
