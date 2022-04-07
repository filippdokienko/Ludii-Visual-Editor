package model.interfaces;

import model.grammar.Ludeme;
import model.LudemeNode;

import java.util.List;

/**
 * Interface for a graph of LudemeNode objects
 * @author Filipp Dokienko
 */

public interface iLudemeGraph {
    // get root LudemeNode
    LudemeNode getRoot();
    void setRoot(LudemeNode root);

    // get LudemeNode by id
    LudemeNode getNode(int id);

    // get all Nodes
    List<LudemeNode> getNodes();
    // get List of LudemeNode by Ludeme (name)
    List<LudemeNode> getNodes(Ludeme ludeme);
    List<LudemeNode> getNodes(String ludemeName);

    // add LudemeNode
    void add(LudemeNode ludemeNode);
    //void add(Ludeme ludeme, int x, int y);
    // remove LudemeNode
    void remove(LudemeNode ludemeNode);

    void addEdge(int from, int to, int field);


    // convert graph to a .lud equivalent String
    String toLud();
}


