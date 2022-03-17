package LayoutManagement.GraphDrawing.View;

import LayoutManagement.GraphDrawing.DrawingFrame;
import LayoutManagement.Math.Vector2D;
import model.MetaGraph.ExpNode;

import java.awt.*;
import java.awt.geom.Ellipse2D;

import static LayoutManagement.VisualEditor.LayoutConfigs.NODE_SIZE;

public class ExpNodeComponent {

    private Vector2D pos;
    private String label;

    public ExpNodeComponent(ExpNode node) {
        pos = node.getPos();
        label = node.getLabel();
    }

    public void drawNode(Graphics g) {
        drawInner((Graphics2D) g);
        drawOuter((Graphics2D) g);
        ((Graphics2D) g).drawString(label,
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
