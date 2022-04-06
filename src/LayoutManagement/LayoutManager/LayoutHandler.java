package LayoutManagement.LayoutManager;

import model.interfaces.iGraph;
import static LayoutManagement.GraphRoutines.updateNodeDepth;

/**
 * TODO: implement application to the trees with different roots
 * @author nic0gin
 */

public class LayoutHandler {

    private iGraph graph;

    private LayoutMethod layout;

    public LayoutHandler(iGraph graph)
    {
        this.graph = graph;
    }

    public void setLayoutMethod(int l)
    {
        switch (l)
        {
            case 0 -> layout = new FruchtermanReingold(graph);
            case 1 -> layout = new DFSBoxDrawing(graph, 25);
            case 2 -> layout = new PLANET(graph, 1,10);
            default -> layout = new DFSBoxDrawing(graph, 5);
        }

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
