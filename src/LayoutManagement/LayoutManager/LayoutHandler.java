package LayoutManagement.LayoutManager;

import LayoutManagement.GraphDrawing.DrawingFrame;
import model.Edge;
import model.MetaGraph.ExpGraph;
import LayoutManagement.Math.Vector2D;
import model.interfaces.iGNode;
import model.interfaces.iGraph;

import java.util.List;

import static java.lang.Math.*;

/**
 * @author nic0gin
 */

public class LayoutHandler {

    private iGraph graph;

    private LayoutMethod layout;

    public LayoutHandler(iGraph graph) {

        this.graph = graph;

    }

    public void setLayoutMethod(int l) {
        switch (l){
            case 0:
                layout = new FruchtermanReingold(graph);
                break;
            case 1:
                layout = new DFSBoxDrawing(graph, 15);
                break;
            default:
                layout = new DFSBoxDrawing(graph, 5);

        }

    }

    public void executeLayout() {
        layout.applyLayout();
    }



}
