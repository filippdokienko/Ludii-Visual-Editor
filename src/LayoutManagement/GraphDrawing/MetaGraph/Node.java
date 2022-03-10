package LayoutManagement.GraphDrawing.MetaGraph;

import LayoutManagement.GraphDrawing.DrawingFrame;
import LayoutManagement.Math.Vector2D;
import LayoutManagement.interfaces.iGNode;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

import static LayoutManagement.VisualEditor.LayoutConfigs.NODE_SIZE;

/**
 * Rough implementation of a node
 * @author nic0gin
 */

public class Node implements iGNode {

    private Vector2D pos;
    private final int id;
    private String data;

    private List<Integer> adjacentNodes;
    private static int node_count = 1;

    public Vector2D disp;

    public Node() {
        adjacentNodes = new ArrayList<>();
        pos = DrawingFrame.getRandomScreenPos();
        id = node_count++;
        data = "";
    }

    public Node(String data) {
        adjacentNodes = new ArrayList<>();
        pos = DrawingFrame.getRandomScreenPos();
        id = node_count++;
        this.data = data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
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

    @Override
    public void setPos() {

    }

    public Vector2D getTransPos() {
        return new Vector2D(pos.getX() + DrawingFrame.getWIDTH() / 2,
                pos.getY() + DrawingFrame.getHEIGHT() / 2);
    }

    public void setPos(double x, double y) {
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

    public void drawNode(Graphics g) {
        drawInner((Graphics2D) g);
        drawOuter((Graphics2D) g);
        ((Graphics2D) g).drawString(data,
                (int)pos.getX() + DrawingFrame.getWIDTH() / 2,
                (int)pos.getY() + DrawingFrame.getHEIGHT() / 2);
    }

    private void drawInner(Graphics2D g2) {
        Shape inner = new Ellipse2D.Double(
                pos.getScreenTransX(),
                pos.getScreenTransY(),
                NODE_SIZE, NODE_SIZE);
        g2.setColor(Color.WHITE);
        g2.fill(inner);
        g2.draw(inner);
    }

    private void drawOuter(Graphics2D g2) {
        Shape outer = new Ellipse2D.Double(
                pos.getScreenTransX(),
                pos.getScreenTransY(),
                NODE_SIZE, NODE_SIZE);
        g2.setColor(Color.BLACK);
        g2.draw(outer);
    }





}
