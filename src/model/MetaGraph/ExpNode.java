package model.MetaGraph;

import LayoutManagement.GraphDrawing.DrawingFrame;
import LayoutManagement.Math.Vector2D;
import model.interfaces.iGNode;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

import static LayoutManagement.VisualEditor.LayoutConfigs.NODE_SIZE;

/**
 * Rough implementation of a node
 * @author nic0gin
 */

public class ExpNode implements iGNode {

    private Vector2D pos;
    private final int id;
    private String label;

    private List<Integer> adjacentNodes;
    private static int node_count = 1;

    public ExpNode() {
        adjacentNodes = new ArrayList<>();
        pos = DrawingFrame.getRandomScreenPos();
        id = node_count++;
        label = "";
    }

    public ExpNode(String data) {
        adjacentNodes = new ArrayList<>();
        pos = DrawingFrame.getRandomScreenPos();
        id = node_count++;
        this.label = data;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public iGNode getParent() {
        return null;
    }

    @Override
    public List<iGNode> getChildren() {
        return null;
    }

    @Override
    public List<iGNode> getSiblings() {
        return null;
    }

    // ###

    public Vector2D getPos() {
        return pos;
    }

    public Vector2D getTransPos() {
        return new Vector2D(pos.getX() + DrawingFrame.getWIDTH() / 2,
                pos.getY() + DrawingFrame.getHEIGHT() / 2);
    }

    public void setPos(int x, int y) {
        this.pos = new Vector2D(x, y);
    }

    public void setPos(Vector2D v) {
        this.pos = new Vector2D(v.getX(), v.getY());
    }

    public void addAdjacentNode(int id) {
        adjacentNodes.add(id);
    }

    public void addNeighbor(int id) {
        adjacentNodes.add(id);
    }

    public String getLabel() {
        return label;
    }
}
