package handler;

import model.DescriptionGraph;
import model.LudemeNode;
import model.grammar.Constructor;

public class Handler {
    public static void addNode(DescriptionGraph graph, LudemeNode node){
        graph.addNode(node);
    }
    public static void removeNode(DescriptionGraph graph, LudemeNode node){
        graph.removeNode(node);
    }
    public static void updateInput(DescriptionGraph graph, LudemeNode node, int index, Object input){
        if(index < node.getProvidedInputs().length) {
            node.setProvidedInput(index, input);
        }
    }
    public static void updatePosition(DescriptionGraph graph, LudemeNode node, int x, int y){
        node.setPos(x, y);
    }
    public static void updateCurrentConstructor(DescriptionGraph graph, LudemeNode node, Constructor c){
        node.setCurrentConstructor(c);
    }
}
