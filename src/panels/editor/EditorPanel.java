package panels.editor;

import components.ludemeblock.CustomPoint;
import components.ludemeblock.LudemeBlock;
import components.ludemeblock.LudemeConnectionComponent;
import components.ludemeblock.LudemeBlockEdge;
import components.ludemeblock.grammar.Constructor;
import components.ludemeblock.grammar.Ludeme;
import components.ludemeblock.grammar.input.LudemeInput;
import components.ludemeblock.grammar.input.Terminal;
import components.ludemeblock.grammar.input.TerminalInput;
import components.ludemeblock.grammar.input.TerminalInputType;
import components.parsegrammar.Parser;
import components.parsegrammar.ParserWithBlueprint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class EditorPanel extends JPanel {

    public static final Color BACKGROUND_COLOR = new Color(233,233,233);
    private EditorPanel editorPanel = this;

    /*

    TODO:
        - Ludeme ingoing connection component not centered
        - Zoom in/out
        - connection by dragging
        - connection only between ingoing & outgoing
        - header background

     */

    public List<LudemeBlockEdge> list_edges = new ArrayList<>();

    private void addConnection(CustomPoint p1, CustomPoint p2){
        list_edges.add(new LudemeBlockEdge(p1, p2));
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

    public void ludemeBlockClicked(LudemeBlock ludemeBlock){
        if(selectedConnectionComponent != null && selectedConnectionComponent.isOutgoing() && ludemeBlock != selectedConnectionComponent.getLudemeBlock()){
            LudemeConnectionComponent ingoingConnectionComponent = ludemeBlock.getIngoingConnectionComponent();
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
        for(LudemeBlockEdge e : list_edges){
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
        setBackground(BACKGROUND_COLOR);
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

        // add default ludeme blocks

        int posX = 20;
        int posY = 20;
        int widthBlock = 300;
        int heightBlock = 10;

        // game
/*

        LudemeBlock.setEditorPanel(this);

        Input ig1 = new Input("", InputTypesAllPossible.STRING);
        Input ig2 = new Input("players", InputTypesAllPossible.LUDEME); // ludeme
        Input ig3 = new Input("equipment", InputTypesAllPossible.LUDEME); // ludeme
        Input ig4 = new Input("rules", InputTypesAllPossible.LUDEME); // ludeme
        Ludeme gameLudeme = new Ludeme("game", Arrays.asList(ig1,ig2,ig3,ig4));
        LudemeBlock game_block = new LudemeBlock(posX, posY, widthBlock, heightBlock, gameLudeme);

        // players

        Input ip1 = new Input("", InputTypesAllPossible.INTEGER);
        Ludeme playersLudeme = new Ludeme("players", List.of(ip1));
        LudemeBlock players_block = new LudemeBlock(posX, posY, widthBlock, heightBlock, playersLudeme);

        // equipment

        Input ie1 = new Input("", InputTypesAllPossible.LUDEME); // ludeme
        Input ie2 = new Input("", InputTypesAllPossible.LUDEME); // ludeme
        Input ie3 = new Input("", InputTypesAllPossible.LUDEME); // ludeme
        Ludeme equipmentLudeme = new Ludeme("equipment", Arrays.asList(ie1,ie2,ie3));
        LudemeBlock equipment_block = new LudemeBlock(posX, posY, widthBlock, heightBlock, equipmentLudeme);

        // board

        Input ib1 = new Input("", InputTypesAllPossible.LUDEME); // ludeme
        Ludeme boardLudeme = new Ludeme("board", List.of(ib1));
        LudemeBlock board_block = new LudemeBlock(posX, posY, widthBlock, heightBlock, boardLudeme);

        // square
        Input is1 = new Input("", InputTypesAllPossible.INTEGER); // should be collection of integers OR integer
        Ludeme squareLudeme = new Ludeme("square", List.of(is1));
        LudemeBlock square_block = new LudemeBlock(posX, posY, widthBlock, heightBlock, squareLudeme);

        // piece
        Input ipi1 = new Input("", InputTypesAllPossible.COLLECTION, List.of("Disc", "Cross")); // should be collection of shape types
        Input ipi2 = new Input("", InputTypesAllPossible.COLLECTION, List.of("P1", "P2", "SHOULD BE AUTOMATIC")); // should be collection of players
        Ludeme pieceLudeme = new Ludeme("piece", Arrays.asList(ipi1, ipi2));
        LudemeBlock piece_block1 = new LudemeBlock(posX, posY, widthBlock, heightBlock, pieceLudeme);
        LudemeBlock piece_block2 = new LudemeBlock(posX, posY, widthBlock, heightBlock, pieceLudeme);

        // rules
        Input ir1 = new Input("", InputTypesAllPossible.LUDEME); // ludeme
        Input ir2 = new Input("", InputTypesAllPossible.LUDEME); // ludeme
        Ludeme rulesLudeme = new Ludeme("rules", Arrays.asList(ir1, ir2));
        LudemeBlock rules_block = new LudemeBlock(posX, posY, widthBlock, heightBlock, rulesLudeme);

        // play
        Input ipl1 = new Input("", InputTypesAllPossible.LUDEME); // ludeme
        Ludeme playLudeme = new Ludeme("play", List.of(ipl1));
        LudemeBlock play_block = new LudemeBlock(posX, posY, widthBlock, heightBlock, playLudeme);

        // move
        Input im1 = new Input("Add", InputTypesAllPossible.LUDEME);
        Ludeme moveLudeme = new Ludeme("move", List.of(im1));
        LudemeBlock move_block = new LudemeBlock(posX, posY, widthBlock, heightBlock, moveLudeme);

        // to
        Input it1 = new Input("", InputTypesAllPossible.LUDEME);
        Ludeme toLudeme = new Ludeme("to", List.of(it1));
        LudemeBlock to_block = new LudemeBlock(posX, posY, widthBlock, heightBlock, toLudeme);

        // sites
        Input isi1 = new Input("", InputTypesAllPossible.COLLECTION, List.of("Empty")); // should be collection of sites
        Ludeme sitesLudeme = new Ludeme("sites", List.of(isi1));
        LudemeBlock sites_block = new LudemeBlock(posX, posY, widthBlock, heightBlock, sitesLudeme);

        // end
        Input ien1 = new Input("", InputTypesAllPossible.LUDEME);
        Ludeme endLudeme = new Ludeme("end", List.of(ien1));
        LudemeBlock end_block = new LudemeBlock(posX, posY, widthBlock, heightBlock, endLudeme);

        // if
        Input ii1 = new Input("", InputTypesAllPossible.LUDEME);
        Input ii2 = new Input("", InputTypesAllPossible.LUDEME);
        Ludeme ifLudeme = new Ludeme("if", List.of(ii1, ii2));
        LudemeBlock if_block = new LudemeBlock(posX, posY, widthBlock, heightBlock, ifLudeme);


        // is
        Input iis1 = new Input("", InputTypesAllPossible.COLLECTION, List.of("Empty", "List")); // should be collection
        Input iis2 = new Input("", InputTypesAllPossible.INTEGER);
        Ludeme isLudeme = new Ludeme("is", List.of(iis1, iis2));
        LudemeBlock is_block = new LudemeBlock(posX, posY, widthBlock, heightBlock, isLudeme);

        // result
        Input ire1 = new Input("", InputTypesAllPossible.COLLECTION, List.of("Mover")); // should be collection
        Input ire2 = new Input("", InputTypesAllPossible.COLLECTION, List.of("Win", "Lose")); // should be collection
        Ludeme resultLudeme = new Ludeme("result", List.of(ire1, ire2));
        LudemeBlock result_block = new LudemeBlock(posX, posY, widthBlock, heightBlock, resultLudeme);



        // add all to board
        if(false){
        add(game_block);
            add(players_block);
        add(equipment_block);
            add(board_block);
                add(square_block);
            add(piece_block1);
            add(piece_block2);
        add(rules_block);
            add(play_block);
                add(move_block);
                    add(to_block);
                        add(sites_block);
            add(end_block);
                add(if_block);
                    add(is_block);
                    add(result_block);

         revalidate();
         repaint();
         }
*/
    }

    ParserWithBlueprint p = new ParserWithBlueprint();
    List<Ludeme> ludemes = p.getLudemes();
    int cc = 66;

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

                LudemeBlock b1 = new LudemeBlock(e.getX(),e.getY(), 300,10, l1);
                add(b1);
                revalidate();
                repaint();
            }*/

            if (e.getButton() == MouseEvent.BUTTON2) {
                /*
                Terminal t1 = new Terminal("A1");
                Terminal t2 = new Terminal("A2");

                components.ludemeblock.grammar.input.Input i1 = new TerminalInput("Integer", TerminalInputType.INTEGER);
                components.ludemeblock.grammar.input.Input i2 = new TerminalInput("Dropdown", TerminalInputType.DROPDOWN, Arrays.asList(t1,t2));
                components.ludemeblock.grammar.input.Input i3 = new LudemeInput("Ludeme",null);
                components.ludemeblock.grammar.input.Input i4 = new LudemeInput("Ludeme2",null);
                components.ludemeblock.grammar.input.Input i5 = new TerminalInput("String",TerminalInputType.STRING);

                Constructor c1 = new Constructor(List.of(i1, i2, i3, i4, i5));
                components.ludemeblock.grammar.Ludeme l1 = new components.ludemeblock.grammar.Ludeme("test ludeme", List.of(c1));

                components.ludemeblock.LudemeBlock b1 = new components.ludemeblock.LudemeBlock(e.getX(), e.getY(),300,l1,editorPanel);

                 */


                Ludeme l = ludemes.get(cc++);
                System.out.println(l.NAME);
                System.out.println(l.CONSTRUCTORS);

                LudemeBlock b1 = new LudemeBlock(e.getX(),e.getY(), 300,l,editorPanel);


                add(b1);
                revalidate();
                repaint();
            }
        }
    }
}
