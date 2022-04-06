package LayoutManagement.LayoutManager;

import LayoutManagement.Math.Vector2D;
import model.interfaces.iGNode;
import model.interfaces.iGraph;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static LayoutManagement.GraphRoutines.*;
import static java.lang.Math.*;

public class PlanetDrawing implements LayoutMethod
{

    private iGraph graph;
    private int xi;
    private int theta;

    public PlanetDrawing(iGraph graph, int xi)
    {
        this.graph = graph;
    }

    /**
     * Get trees and widths of trees
     */
    private int extraPrep()
    {
        return graph.getRoot().getId();
    }


    private double calculateTheta2(int v, int d, int i, int nd)
    {
        double theta1 = calculateTheta1(i, nd);
        if (nd == 1) return theta1;
        else
        {
            double f0 = fJ(0, d, v);
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
        double thetadm1 = calculateTheta(v, d-1, i, nd);
        double thetadm2 = calculateTheta(v, d-2, i, nd);

        int m = d-2;

        if (nd == 1) theta = thetadm1;
        else if (thetadm1 < thetadm2 && nd != 1)
        {

            int fJProd = fJ(0, d, v);
            for (int j = 1; j < m; j++)
            {
                fJProd *= fJ(j, d, v);
            }

            theta = thetadm1 + 2*(i-1)*PI/((nd-1)*fJProd);
        }
        else if (thetadm1 > thetadm2 && nd != 1)
        {
            int fJProd = fJ(0, d, v);
            for (int j = 1; j < m; j++)
            {
                fJProd *= fJ(j, d, v);
            }

            theta = thetadm1 - 2*(i-1)*PI/((nd-1)*fJProd);
        }
        else
        {
            int fJProd = fJ(0, d, v);
            for (int j = 1; j < m; j++)
            {
                fJProd *= fJ(j, d, v);
            }

            theta = thetadm1 -PI/fJProd + 2*(i-1)*PI/((nd-1)*fJProd);
        }
        return theta;
    }

    private double calculateTheta(int v, int d, int i, int nd)
    {
        switch (d)
        {
            case 1 -> { return calculateTheta1(i, nd); }
            case 2 -> { return calculateTheta2(v, d, i, nd); }
            default -> { return calculateThetaNd(v, d, i, nd); }
        }
    }

    /**
     * Node angle
     * @param i child index of node
     * @param nd number of siblings?
     * @return parameter theta
     */
    private double calculateTheta1(int i, int nd)
    {
        return(2*(i - 1)*PI) / nd;
    }

    /**
     * Number of child nodes of the jth layer parent node of
     * @param v node id
     * @param d node layer id
     * @param j parent layer id
     * @return positive integer
     */
    private int fJ(int j, int d, int v)
    {
        int pId = v;
        while (d < j)
        {
            pId = graph.getNode(pId).getParent();
            j--;
        }
        return graph.getNode(pId).getChildren().size();
    }

    private void PLANET(int r)
    {
        // Initialization
        // graph.getNode(r).setPos(new Vector2D(0, 0));
        int d = 0;
        int r0 = 1;
        List<Integer> Q = graph.getNode(r).getChildren();

        //Iteration: calculate the coordinate of nodes on dth levels
        while (!Q.isEmpty())
        {
            d++;
            int rd = r0 + xi*d;
            if (d == 1)
            {
                ListIterator<Integer> iter = Q.listIterator();
                while (iter.hasNext())
                {
                    Integer v = iter.next();

                    double theta = calculateTheta1(getChildIndex(graph, v), getNumSiblings(graph, v));
                    // Convert v's polar coordinates to absolute Cartesian coordinates
                    iGNode nV = graph.getNode(v);
                    nV.setPos(new Vector2D(rd*cos(theta), rd*sin(theta)));
                    List<Integer> m = nV.getChildren();
                    m.forEach(iter::add);
                }
            }
            else if (d == 2)
            {
                ListIterator<Integer> iter = Q.listIterator();
                while (iter.hasNext())
                {
                    Integer v = iter.next();

                    double theta = calculateTheta2(v, getNodeDepth(graph, v), getChildIndex(graph, v), getNumSiblings(graph, v));
                    iGNode nV = graph.getNode(v);
                    Vector2D pXY = graph.getNode(graph.getNode(v).getParent()).getPos();
                    nV.setPos(pXY.add(new Vector2D(rd*cos(theta), rd*sin(theta))));
                    List<Integer> m = nV.getChildren();
                    m.forEach(iter::add);
                }
            }
            else
            {
                ListIterator<Integer> iter = Q.listIterator();
                while (iter.hasNext())
                {
                    Integer v = iter.next();

                    double theta = calculateThetaNd(v, getNodeDepth(graph, v), getChildIndex(graph, v), getNumSiblings(graph, v));
                    iGNode nV = graph.getNode(v);
                    Vector2D pXY = graph.getNode(graph.getNode(v).getParent()).getPos();
                    nV.setPos(pXY.add(new Vector2D(rd*cos(theta), rd*sin(theta))));
                    List<Integer> m = nV.getChildren();
                    m.forEach(iter::add);
                }
            }
            Q.remove(0);
        }
    }



    @Override
    public void applyLayout()
    {
        int r = extraPrep();
        PLANET(r);
    }
}
