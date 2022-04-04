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

    private List<Integer> childNodes;
    private int parent;

    private static int node_count = 1;

    private int height;
    private int width;

    public ExpNode() {
        childNodes = new ArrayList<>();
        pos = DrawingFrame.getRandomScreenPos();
        id = node_count++;
        label = "";

        height = NODE_SIZE;
        width = NODE_SIZE;
    }

    public ExpNode(String data) {
        childNodes = new ArrayList<>();
        pos = DrawingFrame.getRandomScreenPos();
        id = node_count++;
        this.label = data;

        height = NODE_SIZE;
        width = NODE_SIZE;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getParent() {
        return parent;
    }

    @Override
    public List<Integer> getChildren() {
        return childNodes;
    }

    @Override
    public List<Integer> getSiblings() {
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

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void addChildNode(int id) {
        childNodes.add(id);
    }

    public void addParent(int id) {
        parent = id;
    }

    public String getLabel() {
        return label;
    }
}
