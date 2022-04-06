package LayoutManagement.LayoutManager;

import LayoutManagement.GraphDrawing.DrawingFrame;
import LayoutManagement.Math.Vector2D;
import model.Edge;
import model.MetaGraph.ForceNode;
import model.interfaces.iGNode;
import model.interfaces.iGraph;
import java.util.HashMap;
import java.util.List;

import static java.lang.Math.*;

public class FruchtermanReingold implements LayoutMethod {

    // Force-directed drawing
    // Fruchterman-Reingold algorithm

    private final int W = DrawingFrame.getWIDTH();
    private final int H = DrawingFrame.getHEIGHT();
    private final int area =  W*H;
    private double C = 0.05;
    private double coolRate = 0.05;
    private double k;
    private double t = W/10;

    private List<iGNode> nodeList;
    private HashMap<Integer, Double> dispMap; // TODO: replace usage of ForceNode with dispMap
    private HashMap<Integer, ForceNode> forceNodesMap;
    private List<Edge> edgeList;
    private iGraph graph;

    private double repForce(double x) {
        return 1.0*(k*k)/x;
    }

    private double actForce(double x) {
        return 1.0*(x*x)/k;
    }

    private double cool(double x) {
        return max(x*(1-coolRate), 0.1);
    }

    public FruchtermanReingold(iGraph graph) {
        setUpFields(graph);
    }

    public void setUpFields(iGraph graph) {

        this.graph = graph;

        nodeList = graph.getNodeList().values().stream().toList();

        // wrapper for auxiliary variable
        forceNodesMap = new HashMap<>();
        nodeList.forEach((n)-> forceNodesMap.put(n.getId(), new ForceNode(n)));

        edgeList = graph.getEdgeList();

        k = C*sqrt((double) area/(double) nodeList.size());

    }

    public void FruchReinIteration() {
        //k = C*sqrt((double) area/(double) graph.getNodeNum());

        forceNodesMap.forEach((k, v)-> {
            v.setDisp(new Vector2D(0, 0));

            nodeList.forEach((u)-> {
                if (v.getNode().getId() != u.getId()) {
                    Vector2D delta = v.getNode().getPos().sub(u.getPos());
                    v.setDisp(v.getDisp().add( (delta.normalize()).mult(
                            repForce(delta.euclideanNorm())))
                    );
                }
            });
        });

        edgeList.forEach((e)->{
            Vector2D delta = graph.getNodeList().get(e.getNodeA()).getPos().sub(
                    graph.getNodeList().get(e.getNodeB()).getPos());

            forceNodesMap.get(e.getNodeA()).setDisp(
                    forceNodesMap.get(e.getNodeA()).getDisp().sub(
                    delta.normalize().mult(actForce(delta.euclideanNorm())))
            );

            forceNodesMap.get(e.getNodeB()).setDisp(
                    forceNodesMap.get(e.getNodeB()).getDisp().sub(
                            delta.normalize().mult(actForce(delta.euclideanNorm())))
            );
        });

        forceNodesMap.forEach((k, v)->{
            Vector2D tempPos = new Vector2D(v.getNode().getPos().getX(), v.getNode().getPos().getY());

            Vector2D dispNorm = v.getDisp().normalize().mult(min(v.getDisp().euclideanNorm(), t));

            v.getNode().setPos(v.getNode().getPos().add(dispNorm));
            double x = min(W/2, max(-W/2, v.getNode().getPos().getX()));
            double y = min(H/2, max(-H/2, v.getNode().getPos().getY()));
            v.getNode().setPos(new Vector2D(x, y));

            if (Double.isNaN(v.getNode().getPos().getX())) {
                v.getNode().setPos(tempPos);
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

    @Override
    public void applyLayout()
    {

        // Set up variables (only once)

        // Execute algorithm iteration
        for (int i = 0; i < 50; i++)
        {
            FruchReinIteration();
        }


    }

}
