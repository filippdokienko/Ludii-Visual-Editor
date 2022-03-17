package LayoutManagement.GraphFactory;

import model.MetaGraph.ExpGraph;
import model.interfaces.iGraph;

import java.io.File;

public abstract class GraphCreator {

    protected File file;
    protected ExpGraph graph;

    public GraphCreator() {this.graph = new ExpGraph();}

    public GraphCreator(File file) {this.file = file;}

    public abstract iGraph createGraph();

}
