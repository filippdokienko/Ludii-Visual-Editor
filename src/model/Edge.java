package model;

import LayoutManagement.Math.Vector2D;
import model.MetaGraph.ExpGraph;
import model.MetaGraph.ExpNode;

import java.awt.*;
import static LayoutManagement.VisualEditor.LayoutConfigs.NODE_SIZE;

/**
 * Stores information about edge endpoints and its graphics
 * @author nic0gin
 */

public class Edge {

    private final int nodeA;
    private final int nodeB;
    private int field;

    public Edge(int nodeA, int nodeB) {
        this.nodeA = nodeA;
        this.nodeB = nodeB;
    }

    public Edge(int nodeA, int nodeB, int field) {
        this.nodeA = nodeA;
        this.nodeB = nodeB;
        this.field = field;
    }

    public int getField() {
        return field;
    }

    public int getNodeA() {
        return nodeA;
    }

    public int getNodeB() {
        return nodeB;
    }

    public boolean equals(Edge e){
        return this.nodeA == e.getNodeA() && this.nodeB == e.getNodeB();
    }
}
