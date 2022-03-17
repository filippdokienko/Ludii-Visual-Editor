package LayoutManagement.GraphDrawing;

import LayoutManagement.GraphDrawing.View.ExpEdgeComponent;
import LayoutManagement.GraphDrawing.View.ExpNodeComponent;
import LayoutManagement.LayoutManager.LayoutHandler;
import model.MetaGraph.ExpGraph;
import LayoutManagement.GraphFactory.GraphCreator;
import LayoutManagement.GraphFactory.GraphFromFile;
import model.MetaGraph.ExpNode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Panel where graph to be drawn
 * @author nic0gin
 */

public class GraphPanel extends JPanel {

    private LayoutHandler lm;
    private ExpGraph expGraph;

    private List<ExpEdgeComponent> edgeComponentList;
    private List<ExpNodeComponent> nodeComponentList;

    public GraphPanel(Timer timer) {

        // initialise graph for the panel
        GraphCreator gc = new GraphFromFile();
        expGraph = (ExpGraph) gc.createGraph();

        // initialise layout manager
        lm = new LayoutHandler(expGraph);

        add(getMenuBar(timer));

        // Set up edge components

        // Set up node components

    }

    public void constructVisualComponents() {
        // TODO
        edgeComponentList = new ArrayList<ExpEdgeComponent>();
        expGraph.getEdgeList().forEach((e) -> edgeComponentList.add(new ExpEdgeComponent(expGraph, e)));

        // TODO
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // draw edges
        edgeComponentList.forEach((e)->e.drawEdge((Graphics2D) g));
        // draw nodes
        nodeComponentList.forEach((n)-> n.drawNode(g));

    }

    private JMenuBar getMenuBar(Timer timer) {
        JMenuBar jMenuBar = new JMenuBar();
        JMenu jMenuFD = new JMenu("Force-Directed Layout");
        JMenuItem fdLStart = new JMenuItem("Start");
        JMenuItem fdLSettings = new JMenuItem("Settings");
        JMenuItem fdLStop = new JMenuItem("Stop");

        fdLStart.addActionListener(new StartListener(timer));
        fdLSettings.addActionListener(new OpenSettings(timer));
        fdLStop.addActionListener(new StopListener(timer));

        jMenuFD.add(fdLStart);
        jMenuFD.add(fdLSettings);
        jMenuFD.add(fdLStop);
        jMenuBar.add(jMenuFD);

        return jMenuBar;
    }

    public static class StartListener implements ActionListener {

        private final Timer timer;

        public StartListener(Timer timer) {

            this.timer = timer;

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            timer.start();
        }
    }

    public static class StopListener implements ActionListener {

        private final Timer timer;

        public StopListener(Timer timer) {

            this.timer = timer;

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            timer.stop();
        }
    }

    private class OpenSettings implements ActionListener {

        private final Timer timer;

        private OpenSettings(Timer timer) {
            this.timer = timer;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            new SettingsFrame(timer);
        }
    }

    public LayoutHandler getLayoutManager() {
        return lm;
    }

    public int getNumber() {
        return 12;
    }

    public ExpGraph getExpGraph() {
        return expGraph;
    }
}

