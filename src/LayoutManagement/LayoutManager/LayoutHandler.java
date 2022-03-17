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
 * Call layout management methods from here
 * To be refactored with strategy pattern until 17.03.22
 * @author nic0gin
 */

public class LayoutHandler {

    private iGraph graph;

    private LayoutMethod layout;

    public LayoutHandler(iGraph graph) {

        this.graph = graph;

    }

    public void setLayoutMethod() {

    }

    public void executeLayout() {
        layout.applyLayout(graph);
    }



}
