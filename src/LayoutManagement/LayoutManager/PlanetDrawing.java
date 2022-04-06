package LayoutManagement.LayoutManager;

import LayoutManagement.GraphRoutines;
import LayoutManagement.Math.Vector2D;
import model.interfaces.iGNode;
import model.interfaces.iGraph;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static LayoutManagement.GraphRoutines.*;
import static java.lang.Math.*;

public class PlanetDrawing implements LayoutMethod
{

    private iGraph graph;
    private final int ROOT_ID;
    private final int XI;
    private int theta;
    private HashMap<Integer, Double> thetaMap;

    public PlanetDrawing(iGraph graph, int ROOT_ID, int XI)
    {
        this.graph = graph;
        this.ROOT_ID = ROOT_ID;
        this.XI = XI;
        thetaMap = new HashMap<>();
    }

    /**
     * Get trees and widths of trees
     */
    private int extraPrep()
    {
        return graph.getRoot().getId();
    }

    /**
     * Node angle
     * @param i child index of node
     * @param nd number of siblings?
     * @return parameter theta
     */
    private double calculateTheta1(int i, int nd)
    {
        return (2*(i - 1)*PI) / nd;
    }

    private double calculateTheta2(int v, int d, int i, int nd)
    {
        double theta1 = thetaMap.get(graph.getNode(v).getParent());
        if (nd == 1) return theta1;
        else
        {
            double f0 = fJ(0);
            return theta1 - PI/f0 + 2*(i - 1)*PI/((nd - 1)*f0);
        }
    }

    /**
     * Node angle
     * @param v node index
     * @param d layer depth
     * @param i child index of node
     * @param nd number of siblings?
     * @return parameter theta
     */
    private double calculateThetaNd(int v, int d, int i, int nd)
    {
        double theta;
        int dm1 = graph.getNode(v).getParent();
        int dm2 = graph.getNode(dm1).getParent();
        double thetadm1 = thetaMap.get(dm1);
        double thetadm2 = thetaMap.get(dm2);

        int m = d-2;

        if (nd == 1) theta = thetadm1;
        else if (thetadm1 < thetadm2)
        {
            int fJProd = prodFk(m);
            theta = thetadm1 + 2*(i-1)*PI/((nd-1)*fJProd);
        }
        else if (thetadm1 > thetadm2)
        {
            int fJProd = prodFk(m);
            theta = thetadm1 - 2*(i-1)*PI/((nd-1)*fJProd);
        }
        else
        {
            int fJProd = prodFk(m);
            theta = thetadm1 - PI/fJProd + 2*(i-1)*PI/((nd-1)*fJProd);
        }
        return theta;
    }

    /**
     * Number of child nodes of the jth layer parent node
     * @param j layer id
     * @return positive integer
     */
    private int fJ(int j)
    {
        List<Integer> pLayer = GraphRoutines.getLayerNodes(graph, j, ROOT_ID);
        AtomicInteger count = new AtomicInteger();
        pLayer.forEach((p) -> {
            count.addAndGet(graph.getNode(p).getChildren().size());
        });
        return count.get();
    }

    private int prodFk(int m)
    {
        int prod = 1;
        for (int j = 0; j < m; j++)
        {
            prod *= fJ(j);
        }
        return prod;
    }

    private void PLANET(int r)
    {
        // Initialization
        // graph.getNode(r).setPos(new Vector2D(0, 0));
        int d = 0;
        int r0 = 50;

        iGNode rN = graph.getNode(r);
        List<Integer> Q = new ArrayList<>(rN.getChildren());

        //Iteration: calculate the coordinate of nodes on dth levels
        while (!Q.isEmpty())
        {
            d++;
            int rd = r0 + XI *d;
            if (d == 1)
            {
                int N = Q.size();
                List<Integer> QCopy = new ArrayList<>(Q);
                for (int i = 0; i < N; i++)
                {
                    int v = Q.get(i);
                    // getNumSiblings(graph, v)
                    double theta = calculateTheta1(getChildIndex(graph, v), rN.getChildren().size());
                    thetaMap.put(v, theta);
                    // Convert v's polar coordinates to absolute Cartesian coordinates
                    iGNode nV = graph.getNode(v);
                    nV.setPos(rN.getPos().add(new Vector2D(rd*cos(theta), rd*sin(theta))));
                    List<Integer> m = nV.getChildren();
                    Q.addAll(m);
                }
                Q.removeAll(QCopy);
            }
            else if (d == 2)
            {
                int N = Q.size();
                List<Integer> QCopy = new ArrayList<>(Q);
                for (int i = 0; i < N; i++)
                {
                    int v = Q.get(i);
                    double theta = calculateTheta2(v, d, getChildIndex(graph, v), getNumSiblings(graph, v));
                    thetaMap.put(v, theta);
                    iGNode nV = graph.getNode(v);
                    Vector2D pXY = graph.getNode(graph.getNode(v).getParent()).getPos();
                    nV.setPos(pXY.add(new Vector2D(rd*cos(theta), rd*sin(theta))));
                    List<Integer> m = nV.getChildren();
                    Q.addAll(m);
                }
                Q.removeAll(QCopy);
            }
            else
            {
                int N = Q.size();
                List<Integer> QCopy = new ArrayList<>(Q);
                for (int i = 0; i < N; i++)
                {
                    int v = Q.get(i);
                    double theta = calculateThetaNd(v, d, getChildIndex(graph, v), getNumSiblings(graph, v));
                    thetaMap.put(v, theta);
                    iGNode nV = graph.getNode(v);
                    Vector2D pXY = graph.getNode(graph.getNode(v).getParent()).getPos();
                    nV.setPos(pXY.add(new Vector2D(rd*cos(theta), rd*sin(theta))));
                    List<Integer> m = nV.getChildren();
                    Q.addAll(m);
                }
                Q.removeAll(QCopy);
            }
        }
    }

    @Override
    public void applyLayout()
    {
        int r = extraPrep();
        PLANET(r);
    }
}
