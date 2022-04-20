package LayoutManagement.LayoutManager;

import LayoutManagement.Math.Vector2D;
import model.interfaces.iGraph;
import static LayoutManagement.GraphRoutines.updateNodeDepth;

/**
 * TODO: implement application to the trees with different roots
 * @author nic0gin
 */

public class LayoutHandler {

    private iGraph graph;
    private int root;

    private LayoutMethod layout;

    public LayoutHandler(iGraph graph, int root)
    {
        this.graph = graph;
        this.root = root;
    }

    public void setLayoutMethod(int l)
    {
        switch (l)
        {
            case 0 -> layout = new FruchtermanReingold(graph, 0.5, 0.15, new Vector2D(500, 500));
            case 1 -> layout = new DFSBoxDrawing(graph, root,50);
            case 2 -> layout = new PLANET(graph, root,15);
            default -> layout = new DFSBoxDrawing(graph, root, 5);
        }

    }

    public void setRoot(int root) {
        this.root = root;
    }

    public void executeLayout()
    {
        // Prepare the graph

        // Calculate the depth for each node with respect with selected root
        // TODO implement int root into constructor
        int r = 1;
        updateNodeDepth(graph, r);

        layout.applyLayout();
    }

}
