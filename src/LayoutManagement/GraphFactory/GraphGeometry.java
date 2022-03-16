package LayoutManagement.GraphFactory;

import LayoutManagement.GraphDrawing.MetaGraph.Graph;
import LayoutManagement.interfaces.iGraph;

/**
 * Contains hard-coded implementations of graph geometric shapes
 * @author nic0gin
 */

public class GraphGeometry extends GraphCreator{

    public GraphGeometry () {
        //TODO: extend functionality with ENUM
        //getGraphCube();
    }

    @Override
    public iGraph createGraph() {
        return getGraphPyramid();
    }

    public iGraph getGraphCube() {
        iGraph graph = Graph.getGraphInstance();
        int A1 = graph.addNode();
        int B1 = graph.addNode();
        int C1 = graph.addNode();
        int D1 = graph.addNode();

        int A2 = graph.addNode();
        int B2 = graph.addNode();
        int C2 = graph.addNode();
        int D2 = graph.addNode();

        graph.addEdge(A1, B1);
        graph.addEdge(B1, C1);
        graph.addEdge(C1, D1);
        graph.addEdge(A1, D1);

        graph.addEdge(A2, B2);
        graph.addEdge(B2, C2);
        graph.addEdge(C2, D2);
        graph.addEdge(A2, D2);

        graph.addEdge(A1, A2);
        graph.addEdge(B1, B2);
        graph.addEdge(C1, C2);
        graph.addEdge(D1, D2);

        return graph;
    }

    public iGraph getGraphTesseract() {
        iGraph graph = Graph.getGraphInstance();
        int A1 = graph.addNode();
        int B1 = graph.addNode();
        int C1 = graph.addNode();
        int D1 = graph.addNode();

        int A2 = graph.addNode();
        int B2 = graph.addNode();
        int C2 = graph.addNode();
        int D2 = graph.addNode();

        graph.addEdge(A1, B1);
        graph.addEdge(B1, C1);
        graph.addEdge(C1, D1);
        graph.addEdge(A1, D1);

        graph.addEdge(A2, B2);
        graph.addEdge(B2, C2);
        graph.addEdge(C2, D2);
        graph.addEdge(A2, D2);

        graph.addEdge(A1, A2);
        graph.addEdge(B1, B2);
        graph.addEdge(C1, C2);
        graph.addEdge(D1, D2);

        int A3 = graph.addNode();
        int B3 = graph.addNode();
        int C3 = graph.addNode();
        int D3 = graph.addNode();

        int A4 = graph.addNode();
        int B4 = graph.addNode();
        int C4 = graph.addNode();
        int D4 = graph.addNode();

        graph.addEdge(A3, B3);
        graph.addEdge(B3, C3);
        graph.addEdge(C3, D3);
        graph.addEdge(A3, D3);

        graph.addEdge(A4, B4);
        graph.addEdge(B4, C4);
        graph.addEdge(C4, D4);
        graph.addEdge(A4, D4);

        graph.addEdge(A3, A4);
        graph.addEdge(B3, B4);
        graph.addEdge(C3, C4);
        graph.addEdge(D3, D4);

        // ###

        graph.addEdge(A1, A3);
        graph.addEdge(B1, B3);
        graph.addEdge(C1, C3);
        graph.addEdge(D1, D3);

        graph.addEdge(A2, A4);
        graph.addEdge(B2, B4);
        graph.addEdge(C2, C4);
        graph.addEdge(D2, D4);



        return graph;
    }

    public iGraph getGraphPyramid() {
        iGraph graph = Graph.getGraphInstance();

        int A1 = graph.addNode();

        int B1 = graph.addNode();
        int C1 = graph.addNode();
        int D1 = graph.addNode();
        int E1 = graph.addNode();

        int A2 = graph.addNode();

        graph.addEdge(A1, B1);
        graph.addEdge(A1, C1);
        graph.addEdge(A1, D1);
        graph.addEdge(A1, E1);

        graph.addEdge(B1, C1);
        graph.addEdge(C1, D1);
        graph.addEdge(D1, E1);
        graph.addEdge(E1, B1);

        graph.addEdge(A2, B1);
        graph.addEdge(A2, C1);
        graph.addEdge(A2, D1);
        graph.addEdge(A2, E1);

        return graph;
    }

    public iGraph getTicTacToeTree() {
        iGraph graph = Graph.getGraphInstance();

        int A1 = graph.addNode("game");
        int A2 = graph.addNode("game");
        int A3 = graph.addNode("game");
        int A4 = graph.addNode("game");
        int A5 = graph.addNode("game");
        int A6 = graph.addNode("game");
        int A7 = graph.addNode("game");
        int A8 = graph.addNode("game");
        int A9 = graph.addNode("game");
        int A10 = graph.addNode("game");
        int A11 = graph.addNode("game");

        int B1 = graph.addNode("game");
        int B2 = graph.addNode("game");
        int B3 = graph.addNode("game");
        int B4 = graph.addNode("game");
        int B5 = graph.addNode("game");
        int B6 = graph.addNode("game");
        int B7 = graph.addNode("game");
        int B8 = graph.addNode("game");
        int B9 = graph.addNode("game");
        int B10 = graph.addNode("game");
        int B11 = graph.addNode("game");

        int C1 = graph.addNode("game");
        int C2 = graph.addNode("game");
        int C3 = graph.addNode("game");
        int C4 = graph.addNode("game");
        int C5 = graph.addNode("game");
        int C6 = graph.addNode("game");
        int C7 = graph.addNode("game");
        int C8 = graph.addNode("game");
        int C9 = graph.addNode("game");
        int C10 = graph.addNode("game");
        int C11 = graph.addNode("game");

        return graph;
    }


}
