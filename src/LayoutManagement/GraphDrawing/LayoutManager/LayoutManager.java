package LayoutManagement.GraphDrawing.LayoutManager;

import LayoutManagement.GraphDrawing.DrawingFrame;
import LayoutManagement.GraphDrawing.MetaGraph.Edge;
import LayoutManagement.GraphDrawing.MetaGraph.Graph;
import LayoutManagement.GraphDrawing.MetaGraph.Node;
import LayoutManagement.Math.Vector2D;
import LayoutManagement.interfaces.iGraph;

import java.util.List;

import static java.lang.Math.*;

/**
 * Call layout management methods from here
 * To be refactored with strategy pattern until 17.03.22
 * @author nic0gin
 */

public class LayoutManager {

    // Force-directed drawing
    // Fruchterman-Reingold algorithm

    private iGraph graph;
    private final int W = DrawingFrame.getWIDTH();
    private final int H = DrawingFrame.getHEIGHT();
    private final int area =  W*H;
    private double C = 0.2;
    private double coolRate = 0.05;
    private double k;
    private double t = W/10;

    private List<Node> nodeList;
    private List<Edge> edgeList;

    public LayoutManager() {

        this.graph = Graph.getGraphInstance();
        k = C*sqrt((double) area/(double) graph.getNodeNum());
        nodeList = graph.getNodeList().values().stream().toList();
        edgeList = graph.getEdgeList();

    }

    private double repForce(double x) {
        return 1.0*(k*k)/x;
    }

    private double actForce(double x) {
        return 1.0*(x*x)/k;
    }

    private double cool(double x) {
        return max(x*(1-coolRate), 0.1);
    }

    public void FruchReinIteration() {
        //k = C*sqrt((double) area/(double) graph.getNodeNum());

        nodeList.forEach((v)-> {
            v.disp = new Vector2D(0, 0);
            nodeList.forEach((u)-> {
                if (v.getId() != u.getId()) {
                    Vector2D delta = v.getPos().sub(u.getPos());
                    v.disp = v.disp.add( (delta.normalize()).mult(
                            repForce(delta.euclideanNorm()))
                    );
                }
            });
        });

        edgeList.forEach((e)->{
            Vector2D delta = e.getNodeA().getPos().sub(e.getNodeB().getPos());

            e.getNodeA().disp = e.getNodeA().disp.sub(
                    delta.normalize().mult(actForce(delta.euclideanNorm()))
            );
            e.getNodeB().disp = e.getNodeB().disp.add(
                    delta.normalize().mult(actForce(delta.euclideanNorm()))
            );
        });

        nodeList.forEach((v)->{
            Vector2D tempPos = new Vector2D(v.getPos().getX(), v.getPos().getY());

            Vector2D dispNorm = v.disp.normalize().mult(min(v.disp.euclideanNorm(), t));

            v.setPos(v.getPos().add(dispNorm));
            double x = min(W/2, max(-W/2, v.getPos().getX()));
            double y = min(H/2, max(-H/2, v.getPos().getY()));
            v.setPos(x, y);

            if (Double.isNaN(v.getPos().getX())) {
                v.setPos(tempPos);
            }
            System.out.println(x + " " + y);
        });

        t = cool(t);
    }


    public double getC() {
        return C;
    }

    public double getCoolRate() {
        return coolRate;
    }

    public double getT() {
        return t;
    }

    public void setC(double c) {
        C = c;
    }

    public void setCoolRate(double coolRate) {
        this.coolRate = coolRate;
    }

    public void setT(double t) {
        this.t = t;
    }

    public void incrementT(double incrementor) {
        this.t += incrementor;
    }

}
