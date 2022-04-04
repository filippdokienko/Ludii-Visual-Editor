package LayoutManagement.GraphDrawing.View;

import LayoutManagement.GraphDrawing.DrawingFrame;
import LayoutManagement.Math.Vector2D;
import model.MetaGraph.ExpNode;

import java.awt.*;
import java.awt.geom.Ellipse2D;

import static LayoutManagement.VisualEditor.LayoutConfigs.NODE_SIZE;

public class ExpNodeComponent {

    private ExpNode node;
    private String label;

    public ExpNodeComponent(ExpNode node) {
        this.node = node;
        label = node.getLabel();
    }

    public void drawNode(Graphics g) {
        drawInner((Graphics2D) g);
        drawOuter((Graphics2D) g);
        ((Graphics2D) g).drawString(label,
                (int)node.getPos().getX() + DrawingFrame.getWIDTH() / 2,
                (int)node.getPos().getY() + DrawingFrame.getHEIGHT() / 2);
    }

    private void drawInner(Graphics2D g2) {
        Shape inner = new Ellipse2D.Double(
                node.getPos().getScreenTransX(),
                node.getPos().getScreenTransY(),
                NODE_SIZE, NODE_SIZE);
        g2.setColor(Color.WHITE);
        g2.fill(inner);
        g2.draw(inner);
    }

    private void drawOuter(Graphics2D g2) {
        Shape outer = new Ellipse2D.Double(
                node.getPos().getScreenTransX(),
                node.getPos().getScreenTransY(),
                NODE_SIZE, NODE_SIZE);
        g2.setColor(Color.BLACK);
        g2.draw(outer);
    }

}
