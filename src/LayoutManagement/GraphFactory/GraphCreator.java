package LayoutManagement.GraphFactory;

import LayoutManagement.interfaces.iGraph;

import java.io.File;

public abstract class GraphCreator {

    protected File file;

    public GraphCreator() {}

    public GraphCreator(File file) {this.file = file;}

    public abstract iGraph createGraph();

}
