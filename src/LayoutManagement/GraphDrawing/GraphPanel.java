package LayoutManagement.GraphDrawing;

import LayoutManagement.GraphDrawing.LayoutManager.LayoutManager;
import LayoutManagement.GraphDrawing.MetaGraph.Graph;
import LayoutManagement.GraphFactory.GraphCreator;
import LayoutManagement.GraphFactory.GraphFromFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Panel where graph to be drawn
 * @author nic0gin
 */

public class GraphPanel extends JPanel {

    private LayoutManager lm;

    public GraphPanel(Timer timer) {

        // initialise graph for the panel
        GraphCreator gc = new GraphFromFile();
        gc.createGraph();

        // initialise layout manager
        lm = new LayoutManager();

        add(getMenuBar(timer));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // draw edges
        Graph.getGraphInstance().getEdgeList().forEach((e)->e.drawEdge((Graphics2D) g));
        // draw nodes
        Graph.getGraphInstance().getNodeList().forEach((k, n)-> n.drawNode(g));

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

    public LayoutManager getLayoutManager() {
        return lm;
    }

    public int getNumber() {
        return 12;
    }
}

